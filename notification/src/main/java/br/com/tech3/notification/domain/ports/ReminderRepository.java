package br.com.tech3.notification.domain.ports;

import br.com.tech3.notification.domain.model.Reminder;
import java.util.List;

public interface ReminderRepository {
    void save(Reminder reminder);
    List<Reminder> findByConsultaId(String consultaId);
    void updateStatus(String consultaId, String whenLabel, String newStatus, String note);
}