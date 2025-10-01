package gateway.br.com.gateway.infrastructure.bean.config.consulta;


import gateway.br.com.gateway.application.controller.ConsultaControllerInputPortImpl;
import gateway.br.com.gateway.application.input.consulta.controller.ConsultaControllerInputPort;
import gateway.br.com.gateway.application.input.consulta.usecase.*;
import gateway.br.com.gateway.application.mapper.consulta.IConsultaMapper;
import gateway.br.com.gateway.application.output.consulta.ConsultaDataSource;
import gateway.br.com.gateway.application.output.consulta.ConsultaGateway;
import gateway.br.com.gateway.application.usecase.consulta.*;
import gateway.br.com.gateway.domain.model.validator.ConsultaValidator;
import gateway.br.com.gateway.infrastructure.datasource.jpa.consulta.JpaConsultaAdapter;
import gateway.br.com.gateway.infrastructure.datasource.jpa.consulta.JpaConsultaRepository;
import gateway.br.com.gateway.infrastructure.gateway.consulta.ConsultaGatewayImpl;
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
    ConsultaControllerInputPort registerConsultaControllerInputPort(AtualizarConsultaUseCase atualizarConsultaUseCase,
                                                                    CancelarConsultaUseCase cancelarConsultaUseCase,
                                                                    EncontrarConsultaPorIdUseCase encontrarConsultaPorIdUseCase,
                                                                    EncontrarConsultasPorEnfermeiroUseCase encontrarConsultasPorEnfermeiroUseCase,
                                                                    EncontrarConsultasPorMedicoEPeriodoUseCase encontrarConsultasPorMedicoEPeriodoUseCase,
                                                                    EncontrarConsultasPorMedicoUseCase encontrarConsultasPorMedicoUseCase,
                                                                    EncontrarConsultasPorPacienteUseCase encontrarConsultasPorPacienteUseCase,
                                                                    EncontrarTodasConsultasUseCase encontrarTodasConsultasUseCase,
                                                                    AgendarConsultaUseCase agendarConsultaUseCase) {
        return new ConsultaControllerInputPortImpl(agendarConsultaUseCase,
                atualizarConsultaUseCase,
                cancelarConsultaUseCase,
                encontrarConsultaPorIdUseCase,
                encontrarConsultasPorEnfermeiroUseCase,
                encontrarConsultasPorMedicoUseCase,
                encontrarConsultasPorMedicoEPeriodoUseCase,
                encontrarConsultasPorPacienteUseCase,
                encontrarTodasConsultasUseCase);
    }
}
