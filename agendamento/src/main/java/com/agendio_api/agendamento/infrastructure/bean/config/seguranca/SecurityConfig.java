package com.agendio_api.agendamento.infrastructure.bean.config.seguranca;

import com.agendio_api.agendamento.infrastructure.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authz -> authz
                        // endpoints públicos
                        .requestMatchers(HttpMethod.POST, "/auth").permitAll()
                        .requestMatchers("/graphql/**", "/graphiql/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/pacientes").permitAll()
                        .requestMatchers(HttpMethod.POST, "/medicos").permitAll()
                        .requestMatchers(HttpMethod.POST, "/enfermeiros").permitAll()

                        // Use hasAuthority em vez de hasRole
                        .requestMatchers(HttpMethod.GET, "/pacientes/todos").hasAnyAuthority("ROLE_MEDICO", "ROLE_ENFERMEIRO")
                        .requestMatchers("/pacientes/**").hasAnyAuthority("ROLE_PACIENTE", "ROLE_MEDICO", "ROLE_ENFERMEIRO")
                        .requestMatchers("/medicos/**").hasAuthority("ROLE_MEDICO")
                        .requestMatchers("/enfermeiros/**").hasAuthority("ROLE_ENFERMEIRO")

                        .anyRequest().authenticated()
                );
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

}