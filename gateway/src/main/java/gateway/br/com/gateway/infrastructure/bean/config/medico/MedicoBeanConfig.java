package gateway.br.com.gateway.infrastructure.bean.config.medico;

import gateway.br.com.gateway.application.controller.MedicoControllerInputPortImpl;
import gateway.br.com.gateway.application.input.medico.controller.MedicoControllerInputPort;
import gateway.br.com.gateway.application.input.medico.usecase.*;
import gateway.br.com.gateway.application.mapper.medico.IMedicoMapper;
import gateway.br.com.gateway.application.output.usuario.medico.MedicoDataSource;
import gateway.br.com.gateway.application.output.usuario.medico.MedicoGateway;
import gateway.br.com.gateway.application.usecase.medico.*;
import gateway.br.com.gateway.domain.model.validator.MedicoValidator;
import gateway.br.com.gateway.infrastructure.datasource.jpa.medico.JpaMedicoAdapter;
import gateway.br.com.gateway.infrastructure.datasource.jpa.medico.JpaMedicoRepository;
import gateway.br.com.gateway.infrastructure.gateway.usuario.medico.MedicoGatewayImpl;
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
