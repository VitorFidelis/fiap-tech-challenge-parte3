package gateway.br.com.gateway.infrastructure.bean.config.enfermeiro;

import gateway.br.com.gateway.application.controller.EnfermeiroControllerInputPortImpl;
import gateway.br.com.gateway.application.input.enfermeiro.controller.EnfermeiroControllerInputPort;
import gateway.br.com.gateway.application.input.enfermeiro.usecase.*;
import gateway.br.com.gateway.application.mapper.enfermeiro.IEnfermeiroMapper;
import gateway.br.com.gateway.application.output.usuario.enfermeiro.EnfermeiroDataSource;
import gateway.br.com.gateway.application.output.usuario.enfermeiro.EnfermeiroGateway;
import gateway.br.com.gateway.application.usecase.enfermeiro.*;
import gateway.br.com.gateway.domain.model.validator.EnfermeiroValidator;
import gateway.br.com.gateway.infrastructure.datasource.jpa.enfermeiro.JpaEnfermeiroAdapter;
import gateway.br.com.gateway.infrastructure.datasource.jpa.enfermeiro.JpaEnfermeiroRepository;
import gateway.br.com.gateway.infrastructure.gateway.usuario.enfermeiro.EnfermeiroGatewayImpl;
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
