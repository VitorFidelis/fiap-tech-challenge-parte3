package com.agendio_api.agendamento.infrastructure.bean.config.medico;

import com.agendio_api.agendamento.application.controller.MedicoControllerInputPortImpl;
import com.agendio_api.agendamento.application.port.input.medico.controller.MedicoControllerInputPort;
import com.agendio_api.agendamento.application.port.input.medico.usecase.*;
import com.agendio_api.agendamento.application.port.mapper.medico.IMedicoMapper;
import com.agendio_api.agendamento.application.port.output.usuario.medico.MedicoDataSource;
import com.agendio_api.agendamento.application.port.output.usuario.medico.MedicoGateway;
import com.agendio_api.agendamento.application.usecase.medico.*;
import com.agendio_api.agendamento.domain.validator.MedicoValidator;
import com.agendio_api.agendamento.infrastructure.adapter.gateway.usuario.medico.MedicoGatewayImpl;
import com.agendio_api.agendamento.infrastructure.datasource.jpa.medico.JpaMedicoAdapter;
import com.agendio_api.agendamento.infrastructure.datasource.jpa.medico.JpaMedicoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MedicoBeanConfig {

    @Bean
    MedicoDataSource registerMedicoDataSource(JpaMedicoRepository repository, IMedicoMapper mapper) {
        return new JpaMedicoAdapter(repository, mapper);
    }

    @Bean
    MedicoGateway registerMedicoGateway(MedicoDataSource dataSource) {
        return new MedicoGatewayImpl(dataSource);
    }

    @Bean
    AtualizarMedicoUseCase registerAtualizarMedicoUseCase(MedicoGateway gateway, IMedicoMapper mapper, List<MedicoValidator> validators) {
        return new AtualizarMedicoUseCaseImpl(gateway, mapper, validators);
    }

    @Bean
    CadastrarMedicoUseCase registerCadastrarMedicoUseCase(MedicoGateway gateway, IMedicoMapper mapper, List<MedicoValidator> validators) {
        return new CadastrarMedicoUseCaseImpl(gateway, mapper, validators);
    }

    @Bean
    DesativarMedicoUseCase registerDesativarMedicoUseCase(MedicoGateway gateway) {
        return new DesativarMedicoUseCaseImpl(gateway);
    }

    @Bean
    EncontrarMedicoPorCRMUseCase registerEncontrarMedicoPorCRMUseCase(MedicoGateway medicoGateway, IMedicoMapper medicoMapper) {
        return new EncontrarMedicoPorCRMUseCaseImpl(medicoGateway, medicoMapper);
    }

    @Bean
    EncontrarMedicoPorIdUseCase registerEncontrarMedicoPorIdUseCase(MedicoGateway medicoGateway, IMedicoMapper medicoMapper) {
        return new EncontrarMedicoPorIdUseCaseImpl(medicoGateway, medicoMapper);
    }

    @Bean
    EncontrarMedicosPorEspecialidadeUseCase registerEncontrarMedicoPorEspecialidadeUseCase(MedicoGateway medicoGateway, IMedicoMapper medicoMapper) {
        return new EncontrarMedicosPorEspecialidadeUseCaseImpl(medicoGateway, medicoMapper);
    }

    @Bean
    EncontrarTodosMedicosUseCase registerEncontrarTodosMedicosUseCase(MedicoGateway medicoGateway, IMedicoMapper medicoMapper) {
        return new EncontrarTodosMedicosUseCaseImpl(medicoGateway, medicoMapper);
    }

    @Bean
    ReativarMedicoUseCase registerReativarMedicoUseCase(MedicoGateway medicoGateway) {
        return new ReativarMedicoUseCaseImpl(medicoGateway);
    }

    @Bean
    MedicoControllerInputPort medicoControllerInputPort(
            AtualizarMedicoUseCase atualizarMedicoUseCase,
            CadastrarMedicoUseCase cadastrarMedicoUseCase,
            DesativarMedicoUseCase desativarMedicoUseCase,
            EncontrarMedicoPorCRMUseCase encontrarMedicoPorCRMUseCase,
            EncontrarMedicoPorIdUseCase encontrarMedicoPorIdUseCase,
            EncontrarMedicosPorEspecialidadeUseCase encontrarMedicosPorEspecialidadeUseCase,
            EncontrarTodosMedicosUseCase encontrarTodosMedicosUseCase,
            ReativarMedicoUseCase reativarMedicoUseCase
    ) {
        return new MedicoControllerInputPortImpl(atualizarMedicoUseCase, cadastrarMedicoUseCase, desativarMedicoUseCase, encontrarMedicoPorCRMUseCase, encontrarMedicoPorIdUseCase, encontrarMedicosPorEspecialidadeUseCase, encontrarTodosMedicosUseCase, reativarMedicoUseCase);
    }
}
