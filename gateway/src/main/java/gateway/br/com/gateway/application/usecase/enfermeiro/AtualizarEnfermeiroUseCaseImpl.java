package gateway.br.com.gateway.application.usecase.enfermeiro;

import gateway.br.com.gateway.application.dto.enfermeiro.AtualizaEnfermeiroDTO;
import gateway.br.com.gateway.application.dto.enfermeiro.EnfermeiroResponseDTO;
import gateway.br.com.gateway.application.input.enfermeiro.usecase.AtualizarEnfermeiroUseCase;
import gateway.br.com.gateway.application.mapper.enfermeiro.IEnfermeiroMapper;
import gateway.br.com.gateway.application.output.usuario.enfermeiro.EnfermeiroGateway;
import gateway.br.com.gateway.domain.exception.UsuarioNaoEncontradoException;
import gateway.br.com.gateway.domain.model.usuario.Enfermeiro;
import gateway.br.com.gateway.domain.model.validator.EnfermeiroValidator;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public class AtualizarEnfermeiroUseCaseImpl implements AtualizarEnfermeiroUseCase {

    private final EnfermeiroGateway enfermeiroGateway;
    private final IEnfermeiroMapper enfermeiroMapper;
    private final List<EnfermeiroValidator> enfermeiroValidators;

    public AtualizarEnfermeiroUseCaseImpl(EnfermeiroGateway enfermeiroGateway, IEnfermeiroMapper enfermeiroMapper, List<EnfermeiroValidator> enfermeiroValidators) {
        this.enfermeiroGateway = enfermeiroGateway;
        this.enfermeiroMapper = enfermeiroMapper;
        this.enfermeiroValidators = enfermeiroValidators;
    }

    @Transactional
    @Override
    public EnfermeiroResponseDTO execute(UUID id, AtualizaEnfermeiroDTO dto) {
        Enfermeiro enfermeiroExistente = enfermeiroGateway.buscarPorId(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Enfermeiro não encontrado."));
        Enfermeiro enfermeiroParaValidar = enfermeiroMapper.toDomain(dto, enfermeiroExistente);
        enfermeiroValidators.forEach(v -> v.validar(enfermeiroParaValidar));
        Enfermeiro enfermeiroParaSalvar = enfermeiroGateway.atualizar(enfermeiroParaValidar);
        return enfermeiroMapper.toResponseDTO(enfermeiroParaSalvar);
    }

}
