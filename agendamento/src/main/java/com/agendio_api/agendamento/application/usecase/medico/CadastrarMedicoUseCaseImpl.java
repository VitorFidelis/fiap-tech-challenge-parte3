package com.agendio_api.agendamento.application.usecase.medico;

import com.agendio_api.agendamento.application.port.dto.medico.CadastraMedicoDTO;
import com.agendio_api.agendamento.application.port.dto.medico.MedicoResponseDTO;
import com.agendio_api.agendamento.application.port.input.medico.usecase.CadastrarMedicoUseCase;
import com.agendio_api.agendamento.application.port.mapper.medico.IMedicoMapper;
import com.agendio_api.agendamento.application.port.output.usuario.medico.MedicoGateway;
import com.agendio_api.agendamento.domain.model.usuario.Medico;
import com.agendio_api.agendamento.domain.validator.MedicoValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CadastrarMedicoUseCaseImpl implements CadastrarMedicoUseCase {
    private final MedicoGateway medicoGateway;
    private final IMedicoMapper medicoMapper;
    private final List<MedicoValidator> medicoValidators;
    private final BCryptPasswordEncoder passwordEncoder;

    public CadastrarMedicoUseCaseImpl(MedicoGateway medicoGateway, IMedicoMapper medicoMapper, List<MedicoValidator> medicoValidators, BCryptPasswordEncoder passwordEncoder) {
        this.medicoGateway = medicoGateway;
        this.medicoMapper = medicoMapper;
        this.medicoValidators = medicoValidators;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public MedicoResponseDTO executar(CadastraMedicoDTO cadastraMedicoDTO) {
        Medico medico = medicoMapper.toDomain(cadastraMedicoDTO);
        validaMedico(medico);
        medico.setSenha(passwordEncoder.encode(cadastraMedicoDTO.senha()));
        medico = medicoGateway.cadastrar(medico);
        return medicoMapper.toResponseDTO(medico);
    }

    private void validaMedico(Medico medico) {
        for (MedicoValidator medicoValidator : medicoValidators) {
            medicoValidator.validar(medico);
        }
    }
}
