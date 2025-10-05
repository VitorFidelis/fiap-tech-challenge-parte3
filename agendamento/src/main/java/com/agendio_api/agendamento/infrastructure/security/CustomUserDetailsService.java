package com.agendio_api.agendamento.infrastructure.security;

import com.agendio_api.agendamento.application.port.mapper.enfermeiro.IEnfermeiroMapper;
import com.agendio_api.agendamento.application.port.mapper.medico.IMedicoMapper;
import com.agendio_api.agendamento.application.port.mapper.paciente.IPacienteMapper;
import com.agendio_api.agendamento.domain.model.enums.Role;
import com.agendio_api.agendamento.infrastructure.datasource.jpa.enfermeiro.JpaEnfermeiroRepository;
import com.agendio_api.agendamento.infrastructure.datasource.jpa.medico.JpaMedicoRepository;
import com.agendio_api.agendamento.infrastructure.datasource.jpa.paciente.JpaPacienteRepository;
import com.agendio_api.agendamento.infrastructure.mapper.EnfermeiroMapper;
import com.agendio_api.agendamento.infrastructure.mapper.MedicoMapper;
import com.agendio_api.agendamento.infrastructure.mapper.PacienteMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final JpaPacienteRepository pacienteRepo;
    private final JpaMedicoRepository medicoRepo;
    private final JpaEnfermeiroRepository enfermeiroRepo;
    private final IPacienteMapper pacienteMapper;
    private final IMedicoMapper medicoMapper;
    private final IEnfermeiroMapper enfermeiroMapper;

    public CustomUserDetailsService(JpaPacienteRepository pacienteRepo, JpaMedicoRepository medicoRepo, JpaEnfermeiroRepository enfermeiroRepo, IPacienteMapper pacienteMapper, IMedicoMapper medicoMapper, IEnfermeiroMapper enfermeiroMapper) {
        this.pacienteRepo = pacienteRepo;
        this.medicoRepo = medicoRepo;
        this.enfermeiroRepo = enfermeiroRepo;
        this.pacienteMapper = pacienteMapper;
        this.medicoMapper = medicoMapper;
        this.enfermeiroMapper = enfermeiroMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (pacienteRepo.findByEmail(email).isPresent()) {
            var entity = pacienteRepo.findByEmail(email).get();
            return UserPrincipal.createFromPaciente(pacienteMapper.toDomain(entity));
        }

        if (medicoRepo.findByEmail(email).isPresent()) {
            var entity = medicoRepo.findByEmail(email).get();
            return UserPrincipal.createFromMedico(medicoMapper.toDomainFromJPA(entity));
        }

        if (enfermeiroRepo.findByEmail(email).isPresent()) {
            var entity = enfermeiroRepo.findByEmail(email).get();
            return UserPrincipal.createFromEnfermeiro(enfermeiroMapper.toDomain(entity));
        }

        throw new UsernameNotFoundException("Usuário não encontrado: " + email);
    }

    private UserDetails buildUser(String email, String senha, Role role) {
        return User.builder()
                .username(email)
                .password(senha)
                .roles(role.name())
                .build();
    }
}