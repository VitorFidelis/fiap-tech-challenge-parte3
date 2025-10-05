package com.agendio_api.agendamento.infrastructure.bean.config.graphql;

import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateTimeScalar {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private static final Coercing<LocalDateTime, String> COERCING = new Coercing<LocalDateTime, String>() {

        @Override
        public String serialize(Object dataFetcherResult) throws IllegalArgumentException {
            if (dataFetcherResult instanceof LocalDateTime) {
                return FORMATTER.format((LocalDateTime) dataFetcherResult);
            }
            throw new IllegalArgumentException("Expected object of type LocalDateTime but was " + dataFetcherResult.getClass().getName());
        }

        @Override
        public LocalDateTime parseValue(Object input) throws IllegalArgumentException {
            if (input instanceof String) {
                try {
                    return LocalDateTime.parse((String) input, FORMATTER);
                } catch (DateTimeParseException e) {
                    throw new IllegalArgumentException("Invalid LocalDateTime string: " + input, e);
                }
            }
            throw new IllegalArgumentException("Expected input of type String but was " + input.getClass().getName());
        }

        @Override
        public LocalDateTime parseLiteral(Object input) throws IllegalArgumentException {
            return parseValue(input);
        }
    };

    private static final GraphQLScalarType LOCAL_DATE_TIME_SCALAR = GraphQLScalarType.newScalar()
            .name("LocalDateTime")
            .description("Scalar customizado para java.time.LocalDateTime")
            .coercing(COERCING)
            .build();

    public static GraphQLScalarType get() {
        return LOCAL_DATE_TIME_SCALAR;
    }
}