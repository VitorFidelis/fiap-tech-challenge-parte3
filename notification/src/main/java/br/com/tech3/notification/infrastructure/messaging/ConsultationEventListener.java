package br.com.tech3.notification.infrastructure.messaging;

import br.com.tech3.notification.application.usecase.RegisterRemindersUseCase;
import br.com.tech3.notification.config.RetryBackoffProperties;
import br.com.tech3.notification.domain.model.ConsultationEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class ConsultationEventListener {
    private static final Logger log = LoggerFactory.getLogger(ConsultationEventListener.class);
    private final RegisterRemindersUseCase useCase;
    private final RetryBackoffProperties retryProps;
    private final ObjectMapper objectMapper;

    public ConsultationEventListener(RegisterRemindersUseCase useCase, RetryBackoffProperties retryProps) {
        this.useCase = useCase;
        this.retryProps = retryProps;
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @RabbitListener(queues = "#{consultasQueue.name}")
    public void onMessage(@Payload byte[] message) throws Exception {
        int attempt = 0;
        Exception lastEx = null;
        while (attempt < retryProps.getMaxAttempts()) {
            try {
                Map<String, Object> raw = objectMapper.readValue(message, new TypeReference<Map<String, Object>>() {});
                String tipo = (String) raw.getOrDefault("tipo", "CONSULTA_CRIADA");
                String consultaId = (String) raw.get("consultaId");
                String pacienteContato = (String) raw.getOrDefault("pacienteContato", "desconhecido");
                String dataHora = (String) raw.get("dataHoraConsulta");
                java.time.OffsetDateTime dataHoraConsulta = dataHora != null && !dataHora.isBlank()
                        ? java.time.OffsetDateTime.parse(dataHora)
                        : java.time.OffsetDateTime.now().plusHours(26);
                ConsultationEvent event = new ConsultationEvent(
                        tipo, consultaId, dataHoraConsulta, pacienteContato, raw);
                log.info("Evento recebido: tipo={} consultaId={} dataHora={}", tipo, consultaId, dataHoraConsulta);
                useCase.register(event);
                return;
            } catch (Exception ex) {
                lastEx = ex;
                int delaySec = retryProps.getDelays().get(Math.min(attempt, retryProps.getDelays().size()-1));
                log.warn("Falha ao processar mensagem (tentativa {}/{}). Aguardando {}s. Erro: {}",
                        attempt+1, retryProps.getMaxAttempts(), delaySec, ex.getMessage());
                try { Thread.sleep(delaySec * 1000L); } catch (InterruptedException ie) { Thread.currentThread().interrupt(); }
                attempt++;
            }
        }
        log.error("Falha definitiva ao processar mensagem após {} tentativas: {}",
                retryProps.getMaxAttempts(), lastEx != null ? lastEx.getMessage() : "erro desconhecido");
    }
}