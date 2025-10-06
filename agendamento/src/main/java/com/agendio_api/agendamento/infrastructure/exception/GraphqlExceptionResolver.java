package com.agendio_api.agendamento.infrastructure.exception;

import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
public class GraphqlExceptionResolver implements DataFetcherExceptionResolver {

    @Override
    public Mono<List<GraphQLError>> resolveException(Throwable exception, DataFetchingEnvironment environment) {

        if (exception instanceof AccessDeniedException) {
            AccessDeniedException ex = (AccessDeniedException) exception;

            GraphQLError businessError = GraphQLError.newError()
                    .message(ex.getMessage())
                    .path(environment.getExecutionStepInfo().getPath())
                    .errorType(ErrorClassification.errorClassification("BUSINESS_VALIDATION_ERROR"))
                    .extensions(Map.of("code", "403", "timestamp", System.currentTimeMillis()))
                    .build();

            return Mono.just(List.of(businessError));
        }

        if (exception instanceof IllegalArgumentException) {
            IllegalArgumentException ex = (IllegalArgumentException) exception;

            GraphQLError businessError = GraphQLError.newError()
                    .message(ex.getMessage())
                    .path(environment.getExecutionStepInfo().getPath())
                    .errorType(ErrorClassification.errorClassification("BUSINESS_VALIDATION_ERROR"))
                    .extensions(Map.of("code", "400", "timestamp", System.currentTimeMillis()))
                    .build();

            return Mono.just(List.of(businessError));
        }

        if (exception instanceof TokenJwtInvalidoOuExpirado) {
            TokenJwtInvalidoOuExpirado ex = (TokenJwtInvalidoOuExpirado) exception;

            GraphQLError businessError = GraphQLError.newError()
                    .message(ex.getMessage())
                    .path(environment.getExecutionStepInfo().getPath())
                    .errorType(ErrorClassification.errorClassification("SECURITY_ERROR"))
                    .extensions(Map.of("code", "403", "timestamp", System.currentTimeMillis()))
                    .build();

            return Mono.just(List.of(businessError));
        }

        if (exception instanceof RuntimeException) {

            GraphQLError internalError = GraphQLError.newError()
                    .message("Ocorreu um erro interno inesperado. Por favor, tente novamente mais tarde.")
                    .path(environment.getExecutionStepInfo().getPath())
                    .errorType(ErrorClassification.errorClassification("INTERNAL_SERVER_ERROR"))
                    .build();

            return Mono.just(List.of(internalError));
        }

        return Mono.empty();
    }
}