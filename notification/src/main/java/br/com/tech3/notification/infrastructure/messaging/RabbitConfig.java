package br.com.tech3.notification.infrastructure.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue consultasQueue(@Value("${notificacoes.queue}") String queueName) {
        return new Queue(queueName, true);
    }
}