package com.agendio_api.agendamento.infrastructure.security;

import com.agendio_api.agendamento.domain.model.enums.Role;
import com.agendio_api.agendamento.infrastructure.datasource.jpa.enfermeiro.JpaEnfermeiroRepository;
import com.agendio_api.agendamento.infrastructure.datasource.jpa.medico.JpaMedicoRepository;
import com.agendio_api.agendamento.infrastructure.datasource.jpa.paciente.JpaPacienteRepository;
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

    public CustomUserDetailsService(JpaPacienteRepository pacienteRepo, JpaMedicoRepository medicoRepo, JpaEnfermeiroRepository enfermeiroRepo) {
        this.pacienteRepo = pacienteRepo;
        this.medicoRepo = medicoRepo;
        this.enfermeiroRepo = enfermeiroRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return pacienteRepo.findByEmail(email)
                .map(p -> buildUser(p.getEmail(), p.getSenha(), p.getRole()))
                .orElseGet(() -> medicoRepo.findByEmail(email)
                        .map(m -> buildUser(m.getEmail(), m.getSenha(), m.getRole()))
                        .orElseGet(() -> enfermeiroRepo.findByEmail(email)
                                .map(e -> buildUser(e.getEmail(), e.getSenha(), e.getRole()))
                                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email))));
    }

    private UserDetails buildUser(String email, String senha, Role role) {
        return User.builder()
                .username(email)
                .password(senha)
                .roles(role.name())
                .build();
    }
}