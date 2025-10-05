package com.agendio_api.agendamento.application.usecase.medico;

import com.agendio_api.agendamento.application.port.dto.medico.MedicoResponseDTO;
import com.agendio_api.agendamento.application.port.input.medico.usecase.EncontrarMedicoPorIdUseCase;
import com.agendio_api.agendamento.application.port.mapper.medico.IMedicoMapper;
import com.agendio_api.agendamento.application.port.output.usuario.medico.MedicoGateway;
import com.agendio_api.agendamento.domain.exception.UsuarioNaoEncontradoException;

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
