package com.agendio_api.agendamento.infrastructure.bean.config.enfermeiro;

import com.agendio_api.agendamento.application.controller.EnfermeiroControllerInputPortImpl;
import com.agendio_api.agendamento.application.port.input.enfermeiro.controller.EnfermeiroControllerInputPort;
import com.agendio_api.agendamento.application.port.input.enfermeiro.usecase.*;
import com.agendio_api.agendamento.application.port.mapper.enfermeiro.IEnfermeiroMapper;
import com.agendio_api.agendamento.application.port.output.usuario.enfermeiro.EnfermeiroDataSource;
import com.agendio_api.agendamento.application.port.output.usuario.enfermeiro.EnfermeiroGateway;
import com.agendio_api.agendamento.application.usecase.enfermeiro.*;
import com.agendio_api.agendamento.domain.validator.EnfermeiroValidator;
import com.agendio_api.agendamento.infrastructure.adapter.gateway.usuario.enfermeiro.EnfermeiroGatewayImpl;
import com.agendio_api.agendamento.infrastructure.datasource.jpa.enfermeiro.JpaEnfermeiroAdapter;
import com.agendio_api.agendamento.infrastructure.datasource.jpa.enfermeiro.JpaEnfermeiroRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EnfermeiroBeanConfig {

    @Bean
    EnfermeiroDataSource registerEnfermeiroDataSource(JpaEnfermeiroRepository repository, IEnfermeiroMapper enfermeiroMapper) {
        return new JpaEnfermeiroAdapter(repository, enfermeiroMapper);
    }

    @Bean
    EnfermeiroGateway registerEnfermeiroGateway(EnfermeiroDataSource dataSource) {
        return new EnfermeiroGatewayImpl(dataSource);
    }

    @Bean
    AtualizarEnfermeiroUseCase registerAtualizarEnfermeiroUseCase(EnfermeiroGateway enfermeiroGateway, IEnfermeiroMapper enfermeiroMapper, List<EnfermeiroValidator> enfermeiroValidators) {
        return new AtualizarEnfermeiroUseCaseImpl(enfermeiroGateway, enfermeiroMapper, enfermeiroValidators);
    }

    @Bean
    CadastrarEnfermeiroUseCase registerCadastrarEnfermeiroUseCase(EnfermeiroGateway enfermeiroGateway, IEnfermeiroMapper enfermeiroMapper, List<EnfermeiroValidator> enfermeiroValidators) {
        return new CadastrarEnfermeiroUseCaseImpl(enfermeiroGateway, enfermeiroMapper, enfermeiroValidators);
    }

    @Bean
    DesativarEnfermeiroUseCase registerDesativarEnfermeiroUseCase(EnfermeiroGateway enfermeiroGateway) {
        return new DesativarEnfermeiroUseCaseImpl(enfermeiroGateway);
    }

    @Bean
    EncontrarEnfermeiroUseCase registerEncontrarEnfermeiroUseCase(EnfermeiroGateway enfermeiroGateway, IEnfermeiroMapper enfermeiroMapper) {
        return new EncontrarEnfermeiroUseCaseImpl(enfermeiroGateway, enfermeiroMapper);
    }

    @Bean
    EncontrarTodosEnfermeirosUseCase registerEncontrarTodosEnfermeirosUseCase(EnfermeiroGateway enfermeiroGateway, IEnfermeiroMapper enfermeiroMapper) {
        return new EncontrarTodosEnfermeirosUseCaseImpl(enfermeiroGateway, enfermeiroMapper);
    }

    @Bean
    ReativarEnfermeiroUseCase registerReativarEnfermeiroUseCase(EnfermeiroGateway enfermeiroGateway) {
        return new ReativarEnfermeiroUseCaseImpl(enfermeiroGateway);
    }

    @Bean
    EnfermeiroControllerInputPort registerEnfermeiroControllerInputPort(EncontrarEnfermeiroUseCase encontrarEnfermeiroUseCase,
                                                                        EncontrarTodosEnfermeirosUseCase encontrarTodosEnfermeirosUseCase,
                                                                        CadastrarEnfermeiroUseCase cadastrarEnfermeiroUseCase,
                                                                        AtualizarEnfermeiroUseCase atualizarEnfermeiroUseCase,
                                                                        DesativarEnfermeiroUseCase desativarEnfermeiroUseCase,
                                                                        ReativarEnfermeiroUseCase reativarEnfermeiroUseCase) {
        return new EnfermeiroControllerInputPortImpl(atualizarEnfermeiroUseCase, cadastrarEnfermeiroUseCase, encontrarEnfermeiroUseCase, encontrarTodosEnfermeirosUseCase, reativarEnfermeiroUseCase, desativarEnfermeiroUseCase);
    }
}
