package br.com.tech3.notification.application.usecase;

import br.com.tech3.notification.domain.model.Reminder;
import br.com.tech3.notification.domain.model.ConsultationEvent;
import br.com.tech3.notification.domain.ports.ReminderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import jakarta.annotation.PreDestroy;

@Service
public class RegisterRemindersUseCase {
    private static final Logger log = LoggerFactory.getLogger(RegisterRemindersUseCase.class);
    private final ReminderRepository repository;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    public RegisterRemindersUseCase(ReminderRepository repository) {
        this.repository = repository;
    }

    public void register(ConsultationEvent event) {
        OffsetDateTime consultaTime = event.dataHoraConsulta();
        scheduleReminder(event.consultaId(), "T-24h", consultaTime.minusHours(24));
        scheduleReminder(event.consultaId(), "T-2h", consultaTime.minusHours(2));
    }

    private void scheduleReminder(String consultaId, String label, OffsetDateTime when) {
        repository.save(new Reminder(consultaId, label, when, "SCHEDULED", "Aguardando envio"));
        long delayMillis = Math.max(0, Duration.between(OffsetDateTime.now(), when).toMillis());
        scheduler.schedule(() -> {
            try {
                log.info("[LEMBRETE] consultaId={} label={} scheduledAt={} -> Enviando lembrete ao paciente...",
                        consultaId, label, when);
                repository.updateStatus(consultaId, label, "SENT", "Enviado (simulado)");
            } catch (Exception ex) {
                log.error("Falha ao enviar lembrete consultaId={} label={}: {}", consultaId, label, ex.getMessage());
                repository.updateStatus(consultaId, label, "FAILED", "Erro: " + ex.getMessage());
            }
        }, delayMillis, TimeUnit.MILLISECONDS);
    }

    @PreDestroy
    public void shutdown() {
        scheduler.shutdownNow();
    }
}