package gateway.br.com.gateway.application.usecase.medico;

import gateway.br.com.gateway.application.dto.medico.MedicoResponseDTO;
import gateway.br.com.gateway.application.input.medico.usecase.EncontrarMedicoPorCRMUseCase;
import gateway.br.com.gateway.application.mapper.medico.IMedicoMapper;
import gateway.br.com.gateway.application.output.usuario.medico.MedicoGateway;
import gateway.br.com.gateway.domain.exception.UsuarioNaoEncontradoException;

public class EncontrarMedicoPorCRMUseCaseImpl implements EncontrarMedicoPorCRMUseCase {
    private final MedicoGateway medicoGateway;
    private final IMedicoMapper medicoMapper;

    public EncontrarMedicoPorCRMUseCaseImpl(MedicoGateway medicoGateway, IMedicoMapper medicoMapper) {
        this.medicoGateway = medicoGateway;
        this.medicoMapper = medicoMapper;
    }

    @Override
    public MedicoResponseDTO executar(String crm) {
        return medicoMapper.toResponseDTO(
                medicoGateway.buscarPorCrm(crm)
                        .orElseThrow(() -> new UsuarioNaoEncontradoException("Medico não encontrado."))
        );

    }
}
