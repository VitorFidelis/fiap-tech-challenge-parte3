package com.agendio_api.agendamento.infrastructure.bean.config.consulta;

import com.agendio_api.agendamento.application.controller.ConsultaControllerInputPortImpl;
import com.agendio_api.agendamento.application.port.input.consulta.controller.ConsultaControllerInputPort;
import com.agendio_api.agendamento.application.port.input.consulta.usecase.*;
import com.agendio_api.agendamento.application.port.input.consulta.usecase.graphql.AgendarConsultaGraphqlUseCase;
import com.agendio_api.agendamento.application.port.input.consulta.usecase.graphql.AtualizarConsultaGraphqlUseCase;
import com.agendio_api.agendamento.application.port.input.consulta.usecase.graphql.ListarConsultasGraphqlUseCase;
import com.agendio_api.agendamento.application.port.mapper.consulta.IConsultaMapper;
import com.agendio_api.agendamento.application.port.output.consulta.ConsultaDataSource;
import com.agendio_api.agendamento.application.port.output.consulta.ConsultaGateway;
import com.agendio_api.agendamento.application.usecase.consulta.*;
import com.agendio_api.agendamento.application.usecase.consulta.graphql.AgendarConsultaGraphqlUseCaseImpl;
import com.agendio_api.agendamento.application.usecase.consulta.graphql.AtualizarConsultaGraphqlUseCaseImpl;
import com.agendio_api.agendamento.application.usecase.consulta.graphql.ListarConsultasGraphqlUseCaseImpl;
import com.agendio_api.agendamento.domain.validator.ConsultaValidator;
import com.agendio_api.agendamento.infrastructure.adapter.gateway.consulta.ConsultaGatewayImpl;
import com.agendio_api.agendamento.infrastructure.datasource.jpa.consulta.JpaConsultaAdapter;
import com.agendio_api.agendamento.infrastructure.datasource.jpa.consulta.JpaConsultaRepository;
import com.agendio_api.agendamento.infrastructure.security.AuthenticatedUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ConsultaBeanConfig {

    @Bean
    ConsultaDataSource registerConsultaDataSource(JpaConsultaRepository repository, IConsultaMapper consultaMapper) {
        return new JpaConsultaAdapter(repository, consultaMapper);
    }

    @Bean
    ConsultaGateway registerConsultaGateway(ConsultaDataSource dataSource) {
        return new ConsultaGatewayImpl(dataSource);
    }

    @Bean
    AgendarConsultaUseCase registerAgendarConsultaUseCase(ConsultaGateway consultaGateway, IConsultaMapper consultaMapper, List<ConsultaValidator> consultaValidators) {
        return new AgendarConsultaUseCaseImpl(consultaGateway, consultaMapper, consultaValidators);
    }

    @Bean
    AtualizarConsultaUseCase registerAtualizarConsultaUseCase(ConsultaGateway consultaGateway, IConsultaMapper consultaMapper, List<ConsultaValidator> consultaValidators) {
        return new AtualizarConsultaUseCaseImpl(consultaGateway, consultaMapper, consultaValidators);
    }

    @Bean
    CancelarConsultaUseCase registerCancelarConsultaUseCase(ConsultaGateway consultaGateway) {
        return new CancelarConsultaUseCaseImpl(consultaGateway);
    }

    @Bean
    EncontrarConsultaPorIdUseCase registerEncontrarConsultaPorIdUseCase(ConsultaGateway consultaGateway, IConsultaMapper consultaMapper) {
        return new EncontrarConsultaPorIdUseCaseImpl(consultaGateway, consultaMapper);
    }

    @Bean
    EncontrarConsultasPorEnfermeiroUseCase registerEncontrarConsultasPorEnfermeiroUseCase(ConsultaGateway consultaGateway, IConsultaMapper consultaMapper) {
        return new EncontrarConsultasPorEnfermeiroUseCaseImpl(consultaGateway, consultaMapper);
    }

    @Bean
    EncontrarConsultasPorMedicoEPeriodoUseCase registerEncontrarConsultasPorMedicoEPeriodoUseCase(ConsultaGateway consultaGateway, IConsultaMapper consultaMapper) {
        return new EncontrarConsultasPorMedicoEPeriodoUseCaseImpl(consultaGateway, consultaMapper) {
        };
    }

    @Bean
    EncontrarConsultasPorMedicoUseCase registerEncontrarConsultasPorMedicoUseCase(ConsultaGateway consultaGateway, IConsultaMapper consultaMapper) {
        return new EncontrarConsultasPorMedicoUseCaseImpl(consultaGateway, consultaMapper);
    }

    @Bean
    EncontrarConsultasPorPacienteUseCase registerEncontrarConsultasPorPacienteUseCase(ConsultaGateway consultaGateway, IConsultaMapper consultaMapper) {
        return new EncontrarConsultasPorPacienteUseCaseImpl(consultaGateway, consultaMapper);
    }

    @Bean
    EncontrarTodasConsultasUseCase registerEncontrarTodasConsultasUseCase(ConsultaGateway consultaGateway, IConsultaMapper consultaMapper) {
        return new EncontrarTodasConsultasUseCaseImpl(consultaGateway, consultaMapper);
    }

    @Bean
    ListarConsultasGraphqlUseCase listarConsultasGraphqlUseCase(ConsultaGateway consultaGateway, IConsultaMapper consultaMapper) {
        return new ListarConsultasGraphqlUseCaseImpl(consultaGateway, consultaMapper);
    }

    @Bean
    AtualizarConsultaGraphqlUseCase atualizarConsultaGraphqlUseCase(ConsultaGateway consultaGateway, IConsultaMapper consultaMapper) {
        return new AtualizarConsultaGraphqlUseCaseImpl(consultaGateway, consultaMapper);
    }

    @Bean
    AgendarConsultaGraphqlUseCase agendarConsultaGraphqlUseCase(ConsultaGateway consultaGateway, IConsultaMapper consultaMapper) {
        return new AgendarConsultaGraphqlUseCaseImpl(consultaGateway, consultaMapper);
    }

    @Bean
    ConsultaControllerInputPort registerConsultaControllerInputPort(AtualizarConsultaUseCase atualizarConsultaUseCase,
                                                                    CancelarConsultaUseCase cancelarConsultaUseCase,
                                                                    EncontrarConsultaPorIdUseCase encontrarConsultaPorIdUseCase,
                                                                    EncontrarConsultasPorEnfermeiroUseCase encontrarConsultasPorEnfermeiroUseCase,
                                                                    EncontrarConsultasPorMedicoEPeriodoUseCase encontrarConsultasPorMedicoEPeriodoUseCase,
                                                                    EncontrarConsultasPorMedicoUseCase encontrarConsultasPorMedicoUseCase,
                                                                    EncontrarConsultasPorPacienteUseCase encontrarConsultasPorPacienteUseCase,
                                                                    EncontrarTodasConsultasUseCase encontrarTodasConsultasUseCase,
                                                                    AgendarConsultaUseCase agendarConsultaUseCase,
                                                                    ListarConsultasGraphqlUseCase listarConsultasGraphqlUseCase,
                                                                    AtualizarConsultaGraphqlUseCase atualizarConsultaGraphqlUseCase,
                                                                    AgendarConsultaGraphqlUseCase agendarConsultaGraphqlUseCase,
                                                                    AuthenticatedUserService authenticatedUserService) {
        return new ConsultaControllerInputPortImpl(agendarConsultaUseCase,
                atualizarConsultaUseCase,
                cancelarConsultaUseCase,
                encontrarConsultaPorIdUseCase,
                encontrarConsultasPorEnfermeiroUseCase,
                encontrarConsultasPorMedicoUseCase,
                encontrarConsultasPorMedicoEPeriodoUseCase,
                encontrarConsultasPorPacienteUseCase,
                encontrarTodasConsultasUseCase,
                listarConsultasGraphqlUseCase,
                atualizarConsultaGraphqlUseCase,
                agendarConsultaGraphqlUseCase,
                authenticatedUserService);
    }
}
