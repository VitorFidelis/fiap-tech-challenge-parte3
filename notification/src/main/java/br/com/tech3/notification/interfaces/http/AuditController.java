package br.com.tech3.notification.interfaces.http;

import br.com.tech3.notification.domain.model.Reminder;
import br.com.tech3.notification.domain.ports.ReminderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/lembretes")
public class AuditController {
    private final ReminderRepository repository;
    public AuditController(ReminderRepository repository) { this.repository = repository; }
    @GetMapping("/consulta/{consultaId}")
    public ResponseEntity<List<Reminder>> byConsulta(@PathVariable String consultaId) {
        return ResponseEntity.ok(repository.findByConsultaId(consultaId));
    }
}