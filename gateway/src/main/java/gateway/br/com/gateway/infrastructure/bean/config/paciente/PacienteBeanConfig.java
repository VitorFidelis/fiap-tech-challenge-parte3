package gateway.br.com.gateway.infrastructure.bean.config.paciente;

import gateway.br.com.gateway.application.controller.PacienteControllerInputPortImpl;
import gateway.br.com.gateway.application.input.paciente.controller.PacienteControllerInputPort;
import gateway.br.com.gateway.application.input.paciente.usecase.*;
import gateway.br.com.gateway.application.mapper.paciente.IPacienteMapper;
import gateway.br.com.gateway.application.output.usuario.paciente.PacienteDataSource;
import gateway.br.com.gateway.application.output.usuario.paciente.PacienteGateway;
import gateway.br.com.gateway.application.usecase.paciente.*;
import gateway.br.com.gateway.domain.model.validator.PacienteValidator;
import gateway.br.com.gateway.infrastructure.datasource.jpa.paciente.JpaPacienteAdapter;
import gateway.br.com.gateway.infrastructure.datasource.jpa.paciente.JpaPacienteRepository;
import gateway.br.com.gateway.infrastructure.gateway.usuario.paciente.PacienteGatewayImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    CadastrarPacienteUseCase registerCadastrarPacienteUseCase(PacienteGateway pacienteGateway, IPacienteMapper pacienteMapper, List<PacienteValidator> pacienteValidators) {
        return new CadastrarPacienteUseCaseImpl(pacienteGateway, pacienteMapper, pacienteValidators);
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
