package gateway.br.com.gateway.application.usecase.medico;

import gateway.br.com.gateway.application.dto.medico.MedicoResponseDTO;
import gateway.br.com.gateway.application.input.medico.usecase.EncontrarMedicoPorIdUseCase;
import gateway.br.com.gateway.application.mapper.medico.IMedicoMapper;
import gateway.br.com.gateway.application.output.usuario.medico.MedicoGateway;
import gateway.br.com.gateway.domain.exception.UsuarioNaoEncontradoException;

import java.util.UUID;

public class EncontrarMedicoPorIdUseCaseImpl implements EncontrarMedicoPorIdUseCase {
    private final MedicoGateway medicoGateway;
    private final IMedicoMapper medicoMapper;

    public EncontrarMedicoPorIdUseCaseImpl(MedicoGateway medicoGateway, IMedicoMapper medicoMapper) {
        this.medicoGateway = medicoGateway;
        this.medicoMapper = medicoMapper;
    }

    @Override
    public MedicoResponseDTO executar(UUID id) {
        return medicoMapper
                .toResponseDTO(
                        medicoGateway
                                .buscarPorId(id)
                                .orElseThrow(() -> new UsuarioNaoEncontradoException("Medico não encontrado."))
                );

    }
}
