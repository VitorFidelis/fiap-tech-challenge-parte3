package com.agendio_api.agendamento.application.usecase.enfermeiro;

import com.agendio_api.agendamento.application.port.dto.enfermeiro.CadastraEnfermeiroDTO;
import com.agendio_api.agendamento.application.port.dto.enfermeiro.EnfermeiroResponseDTO;
import com.agendio_api.agendamento.application.port.input.enfermeiro.usecase.CadastrarEnfermeiroUseCase;
import com.agendio_api.agendamento.application.port.mapper.enfermeiro.IEnfermeiroMapper;
import com.agendio_api.agendamento.application.port.output.usuario.enfermeiro.EnfermeiroGateway;
import com.agendio_api.agendamento.domain.model.usuario.Enfermeiro;
import com.agendio_api.agendamento.domain.validator.EnfermeiroValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CadastrarEnfermeiroUseCaseImpl implements CadastrarEnfermeiroUseCase {

    private final EnfermeiroGateway enfermeiroGateway;
    private final IEnfermeiroMapper enfermeiroMapper;
    private final List<EnfermeiroValidator> enfermeiroValidators;
    private final BCryptPasswordEncoder passwordEncoder;

    public CadastrarEnfermeiroUseCaseImpl(EnfermeiroGateway enfermeiroGateway, IEnfermeiroMapper enfermeiroMapper, List<EnfermeiroValidator> enfermeiroValidators, BCryptPasswordEncoder passwordEncoder) {
        this.enfermeiroGateway = enfermeiroGateway;
        this.enfermeiroMapper = enfermeiroMapper;
        this.enfermeiroValidators = enfermeiroValidators;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public EnfermeiroResponseDTO execute(CadastraEnfermeiroDTO cadastraEnfermeiroDTO) {
        Enfermeiro enfermeiro = enfermeiroMapper.toDomain(cadastraEnfermeiroDTO);
        validaEnfermeiro(enfermeiro);
        enfermeiro.setSenha(passwordEncoder.encode(cadastraEnfermeiroDTO.senha()));
        enfermeiro = enfermeiroGateway.cadastrar(enfermeiro);
        return enfermeiroMapper.toResponseDTO(enfermeiro);
    }

    private void validaEnfermeiro(Enfermeiro enfermeiro) {
        for (EnfermeiroValidator validator : enfermeiroValidators) {
            validator.validar(enfermeiro);
        }
    }
}
