package gateway.br.com.gateway.application.usecase.enfermeiro;


import gateway.br.com.gateway.application.dto.enfermeiro.EnfermeiroResponseDTO;
import gateway.br.com.gateway.application.input.enfermeiro.usecase.EncontrarEnfermeiroUseCase;
import gateway.br.com.gateway.application.mapper.enfermeiro.IEnfermeiroMapper;
import gateway.br.com.gateway.application.output.usuario.enfermeiro.EnfermeiroGateway;
import gateway.br.com.gateway.domain.exception.UsuarioNaoEncontradoException;

import java.util.UUID;

public class EncontrarEnfermeiroUseCaseImpl implements EncontrarEnfermeiroUseCase {
    private final EnfermeiroGateway enfermeiroGateway;
    private final IEnfermeiroMapper enfermeiroMapper;

    public EncontrarEnfermeiroUseCaseImpl(EnfermeiroGateway enfermeiroGateway, IEnfermeiroMapper enfermeiroMapper) {
        this.enfermeiroGateway = enfermeiroGateway;
        this.enfermeiroMapper = enfermeiroMapper;
    }

    @Override
    public EnfermeiroResponseDTO execute(UUID id) {
        return enfermeiroMapper.toResponseDTO(
                enfermeiroGateway
                        .buscarPorId(id)
                        .orElseThrow(
                                () -> new UsuarioNaoEncontradoException("Enfermeiro não encontrado.")));
    }
}
