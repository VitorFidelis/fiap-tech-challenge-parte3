package com.agendio_api.agendamento.application.usecase.medico;

import com.agendio_api.agendamento.application.port.dto.medico.AtualizaMedicoDTO;
import com.agendio_api.agendamento.application.port.dto.medico.MedicoResponseDTO;
import com.agendio_api.agendamento.application.port.input.medico.usecase.AtualizarMedicoUseCase;
import com.agendio_api.agendamento.application.port.mapper.medico.IMedicoMapper;
import com.agendio_api.agendamento.application.port.output.usuario.medico.MedicoGateway;
import com.agendio_api.agendamento.domain.exception.UsuarioNaoEncontradoException;
import com.agendio_api.agendamento.domain.model.usuario.Medico;
import com.agendio_api.agendamento.domain.validator.MedicoValidator;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public class AtualizarMedicoUseCaseImpl implements AtualizarMedicoUseCase {
    private final MedicoGateway medicoGateway;
    private final IMedicoMapper medicoMapper;
    private final List<MedicoValidator> medicoValidators;

    public AtualizarMedicoUseCaseImpl(MedicoGateway medicoGateway, IMedicoMapper medicoMapper, List<MedicoValidator> medicoValidators) {
        this.medicoGateway = medicoGateway;
        this.medicoMapper = medicoMapper;
        this.medicoValidators = medicoValidators;
    }

    @Transactional
    @Override
    public MedicoResponseDTO executar(UUID id, AtualizaMedicoDTO atualizaMedicoDTO) {
        Medico medicoExistente = medicoGateway.buscarPorId(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Medico não encontrado."));
        Medico medicoParaValidar = medicoMapper.toDomain(atualizaMedicoDTO, medicoExistente);
        medicoValidators.forEach(medicoValidator -> medicoValidator.validar(medicoParaValidar));
        Medico medicoParaSalvar = medicoGateway.atualizar(medicoParaValidar);
        return medicoMapper.toResponseDTO(medicoParaSalvar);
    }
}
