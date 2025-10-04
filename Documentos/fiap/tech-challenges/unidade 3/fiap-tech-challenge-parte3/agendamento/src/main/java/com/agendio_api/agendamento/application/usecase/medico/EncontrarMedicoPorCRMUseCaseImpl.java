package com.agendio_api.agendamento.application.usecase.medico;

import com.agendio_api.agendamento.application.port.dto.medico.MedicoResponseDTO;
import com.agendio_api.agendamento.application.port.input.medico.usecase.EncontrarMedicoPorCRMUseCase;
import com.agendio_api.agendamento.application.port.mapper.medico.IMedicoMapper;
import com.agendio_api.agendamento.application.port.output.usuario.medico.MedicoGateway;
import com.agendio_api.agendamento.domain.exception.UsuarioNaoEncontradoException;

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
