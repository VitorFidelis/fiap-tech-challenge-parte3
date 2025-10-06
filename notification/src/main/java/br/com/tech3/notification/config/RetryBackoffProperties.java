package br.com.tech3.notification.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "notificacoes.backoff")
public class RetryBackoffProperties {
    private List<Integer> delays;
    private int maxAttempts;

    public List<Integer> getDelays() { return delays; }
    public void setDelays(List<Integer> delays) { this.delays = delays; }
    public int getMaxAttempts() { return maxAttempts; }
    public void setMaxAttempts(int maxAttempts) { this.maxAttempts = maxAttempts; }
}