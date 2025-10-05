package com.agendio_api.agendamento.infrastructure.security;

import com.agendio_api.agendamento.domain.model.usuario.Enfermeiro;
import com.agendio_api.agendamento.domain.model.usuario.Medico;
import com.agendio_api.agendamento.domain.model.usuario.Paciente;
import com.agendio_api.agendamento.domain.model.usuario.Usuario;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class UserPrincipal implements UserDetails {
    @Getter
    private UUID id;
    private String email;
    private String senha;
    @Getter
    private String nome;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(UUID id, String email, String senha, String nome,
                         Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.authorities = authorities;
    }

    public static UserPrincipal create(Usuario usuario) {
        Collection<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + usuario.getRole())
        );

        return new UserPrincipal(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getNome(),
                authorities
        );
    }

    public static UserPrincipal createFromPaciente(Paciente p) {
        return new UserPrincipal(
                p.getId(),
                p.getEmail(),
                p.getSenha(),
                p.getNome(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + p.getRole()))
        );
    }

    public static UserPrincipal createFromMedico(Medico m) {
        return new UserPrincipal(
                m.getId(),
                m.getEmail(),
                m.getSenha(),
                m.getNome(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + m.getRole()))
        );
    }

    public static UserPrincipal createFromEnfermeiro(Enfermeiro e) {
        return new UserPrincipal(
                e.getId(),
                e.getEmail(),
                e.getSenha(),
                e.getNome(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + e.getRole()))
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}