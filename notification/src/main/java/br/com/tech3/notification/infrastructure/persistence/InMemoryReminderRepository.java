package br.com.tech3.notification.infrastructure.persistence;

import br.com.tech3.notification.domain.model.Reminder;
import br.com.tech3.notification.domain.ports.ReminderRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryReminderRepository implements ReminderRepository {
    private final Map<String, List<Reminder>> store = new ConcurrentHashMap<>();

    @Override
    public void save(Reminder reminder) {
        store.computeIfAbsent(reminder.consultaId(), k -> new ArrayList<>()).add(reminder);
    }

    @Override
    public List<Reminder> findByConsultaId(String consultaId) {
        return store.getOrDefault(consultaId, List.of());
    }

    @Override
    public void updateStatus(String consultaId, String whenLabel, String newStatus, String note) {
        List<Reminder> list = store.get(consultaId);
        if (list == null) return;
        List<Reminder> updated = new ArrayList<>();
        for (Reminder r : list) {
            if (r.whenLabel().equals(whenLabel)) {
                updated.add(new Reminder(r.consultaId(), r.whenLabel(), r.scheduledAt(), newStatus, note));
            } else {
                updated.add(r);
            }
        }
        store.put(consultaId, updated);
    }
}