package com.agendio_api.agendamento.infrastructure.adapter.messaging.rabbitmq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ConsultasPublisher {

    private final RabbitTemplate rabbit;
    private final String queue;
    private final ObjectMapper objectMapper;

    public ConsultasPublisher(RabbitTemplate rabbit, @Value("${notificacoes.queue}") String queue,
            ObjectMapper objectMapper) {
        this.rabbit = rabbit;
        this.queue = queue;
        this.objectMapper = objectMapper;
    }

    public void publicarConsultaCriada(String consultaId, String contatoOpcional, String dataHoraISO) {
        Map<String, Object> msg = new HashMap<>();
        msg.put("tipo", "CONSULTA_CRIADA");
        msg.put("consultaId", consultaId);
        msg.put("dataHoraConsulta", dataHoraISO);
        if (contatoOpcional != null && !contatoOpcional.isBlank()) {
            msg.put("pacienteContato", contatoOpcional);
        }
        enviar(msg, consultaId);
    }

    public void publicarConsultaEditada(String consultaId, String contatoOpcional, String dataHoraISO) {
        Map<String, Object> msg = new HashMap<>();
        msg.put("tipo", "CONSULTA_EDITADA");
        msg.put("consultaId", consultaId);
        msg.put("dataHoraConsulta", dataHoraISO);
        if (contatoOpcional != null && !contatoOpcional.isBlank()) {
            msg.put("pacienteContato", contatoOpcional);
        }
        enviar(msg, consultaId);
    }

    public void publicarConsultaCancelada(String consultaId) {
        Map<String, Object> msg = new HashMap<>();
        msg.put("tipo", "CONSULTA_CANCELADA");
        msg.put("consultaId", consultaId);
        enviar(msg, consultaId);
    }

    private void enviar(Map<String, Object> payload, String consultaId) {
        try {
            String json = objectMapper.writeValueAsString(payload); // <-- serializa para JSON

            rabbit.convertAndSend("", queue, json, m -> {
                m.getMessageProperties().setDeliveryMode(org.springframework.amqp.core.MessageDeliveryMode.PERSISTENT);
                m.getMessageProperties().setContentType("application/json"); // <-- importante
                m.getMessageProperties().setCorrelationId("consulta:" + consultaId);
                return m;
            });
        } catch (Exception e) {
            System.out.println("WARN: falha ao serializar/publicar no RabbitMQ: " + e.getMessage());
        }
    }
}
