package com.agendio_api.agendamento.infrastructure.bean.config.paciente;

import com.agendio_api.agendamento.application.controller.PacienteControllerInputPortImpl;
import com.agendio_api.agendamento.application.port.input.paciente.controller.PacienteControllerInputPort;
import com.agendio_api.agendamento.application.port.input.paciente.usecase.*;
import com.agendio_api.agendamento.application.port.mapper.paciente.IPacienteMapper;
import com.agendio_api.agendamento.application.port.output.usuario.paciente.PacienteDataSource;
import com.agendio_api.agendamento.application.port.output.usuario.paciente.PacienteGateway;
import com.agendio_api.agendamento.application.usecase.paciente.*;
import com.agendio_api.agendamento.domain.validator.PacienteValidator;
import com.agendio_api.agendamento.infrastructure.adapter.gateway.usuario.paciente.PacienteGatewayImpl;
import com.agendio_api.agendamento.infrastructure.datasource.jpa.paciente.JpaPacienteAdapter;
import com.agendio_api.agendamento.infrastructure.datasource.jpa.paciente.JpaPacienteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
public class PacienteBeanConfig {
    @Bean
    PacienteDataSource registerPacienteDataSource(JpaPacienteRepository repository, IPacienteMapper pacienteMapper) {
        return new JpaPacienteAdapter(repository, pacienteMapper);
    }

    @Bean
    PacienteGateway registerPacienteGateway(PacienteDataSource dataSource) {
        return new PacienteGatewayImpl(dataSource);
    }

    @Bean
    AtualizarPacienteUseCase registerAtualizarPacienteUseCase(PacienteGateway pacienteGateway, IPacienteMapper pacienteMapper, List<PacienteValidator> pacienteValidators) {
        return new AtualizarPacienteUseCaseImpl(pacienteGateway, pacienteMapper, pacienteValidators);
    }

    @Bean
    CadastrarPacienteUseCase registerCadastrarPacienteUseCase(PacienteGateway pacienteGateway, IPacienteMapper pacienteMapper, List<PacienteValidator> pacienteValidators, BCryptPasswordEncoder passwordEncoder) {
        return new CadastrarPacienteUseCaseImpl(pacienteGateway, pacienteMapper, pacienteValidators, passwordEncoder);
    }

    @Bean
    EncontrarPacienteUseCase registerEncontrarPacienteUseCase(PacienteGateway pacienteGateway, IPacienteMapper pacienteMapper) {
        return new EncontrarPacienteUseCaseImpl(pacienteGateway, pacienteMapper);
    }

    @Bean
    EncontrarTodosPacientesUseCase registerEncontrarTodosPacientesUseCase(PacienteGateway pacienteGateway, IPacienteMapper pacienteMapper) {
        return new EncontrarTodosPacientesUseCaseImpl(pacienteGateway, pacienteMapper);
    }

    @Bean
    DesativarPacienteUseCase registerDesativarPacienteUseCase(PacienteGateway gateway) {
        return new DesativarPacienteUseCaseImpl(gateway);
    }

    @Bean
    ReativarPacienteUseCase registerReativarPacienteUseCase(PacienteGateway gateway) {
        return new ReativarPacienteUseCaseImpl(gateway);
    }

    @Bean
    PacienteControllerInputPort registerPacienteControllerInputPort(AtualizarPacienteUseCase atualizarPacienteUseCase, CadastrarPacienteUseCase cadastrarPacienteUseCase, EncontrarPacienteUseCase encontrarPacienteUseCase, EncontrarTodosPacientesUseCase encontrarTodosPacientesUseCase, DesativarPacienteUseCase desativarPacienteUseCase, ReativarPacienteUseCase reativarPacienteUseCase) {
        return new PacienteControllerInputPortImpl(atualizarPacienteUseCase, cadastrarPacienteUseCase, encontrarPacienteUseCase, encontrarTodosPacientesUseCase, desativarPacienteUseCase, reativarPacienteUseCase);
    }
}
