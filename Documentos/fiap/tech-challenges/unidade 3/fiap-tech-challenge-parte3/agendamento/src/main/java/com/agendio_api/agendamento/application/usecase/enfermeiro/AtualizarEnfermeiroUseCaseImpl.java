package com.agendio_api.agendamento.application.usecase.enfermeiro;

import com.agendio_api.agendamento.application.port.dto.enfermeiro.AtualizaEnfermeiroDTO;
import com.agendio_api.agendamento.application.port.dto.enfermeiro.EnfermeiroResponseDTO;
import com.agendio_api.agendamento.application.port.input.enfermeiro.usecase.AtualizarEnfermeiroUseCase;
import com.agendio_api.agendamento.application.port.mapper.enfermeiro.IEnfermeiroMapper;
import com.agendio_api.agendamento.application.port.output.usuario.enfermeiro.EnfermeiroGateway;
import com.agendio_api.agendamento.domain.exception.UsuarioNaoEncontradoException;
import com.agendio_api.agendamento.domain.model.usuario.Enfermeiro;
import com.agendio_api.agendamento.domain.validator.EnfermeiroValidator;
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
