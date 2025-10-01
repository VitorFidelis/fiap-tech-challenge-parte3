package gateway.br.com.gateway.application.usecase.enfermeiro;

import gateway.br.com.gateway.application.dto.enfermeiro.CadastraEnfermeiroDTO;
import gateway.br.com.gateway.application.dto.enfermeiro.EnfermeiroResponseDTO;
import gateway.br.com.gateway.application.input.enfermeiro.usecase.CadastrarEnfermeiroUseCase;
import gateway.br.com.gateway.application.mapper.enfermeiro.IEnfermeiroMapper;
import gateway.br.com.gateway.application.output.usuario.enfermeiro.EnfermeiroGateway;
import gateway.br.com.gateway.domain.model.usuario.Enfermeiro;
import gateway.br.com.gateway.domain.model.validator.EnfermeiroValidator;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CadastrarEnfermeiroUseCaseImpl implements CadastrarEnfermeiroUseCase {
    private final EnfermeiroGateway enfermeiroGateway;
    private final IEnfermeiroMapper enfermeiroMapper;
    private final List<EnfermeiroValidator> enfermeiroValidators;

    public CadastrarEnfermeiroUseCaseImpl(EnfermeiroGateway enfermeiroGateway, IEnfermeiroMapper enfermeiroMapper, List<EnfermeiroValidator> enfermeiroValidators) {
        this.enfermeiroGateway = enfermeiroGateway;
        this.enfermeiroMapper = enfermeiroMapper;
        this.enfermeiroValidators = enfermeiroValidators;
    }

    @Transactional
    @Override
    public EnfermeiroResponseDTO execute(CadastraEnfermeiroDTO cadastraEnfermeiroDTO) {
        Enfermeiro enfermeiro = enfermeiroMapper.toDomain(cadastraEnfermeiroDTO);
        validaEnfermeiro(enfermeiro);
        enfermeiro = enfermeiroGateway.cadastrar(enfermeiro);
        return enfermeiroMapper.toResponseDTO(enfermeiro);
    }

    private void validaEnfermeiro(Enfermeiro enfermeiro) {
        for (EnfermeiroValidator validator : enfermeiroValidators) {
            validator.validar(enfermeiro);
        }
    }
}
