package com.agendio_api.agendamento.infrastructure.bean.config.graphql;

import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphQLConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return builder -> builder
                .scalar(LocalDateTimeScalar.get())
                .scalar(ExtendedScalars.UUID);
    }
}