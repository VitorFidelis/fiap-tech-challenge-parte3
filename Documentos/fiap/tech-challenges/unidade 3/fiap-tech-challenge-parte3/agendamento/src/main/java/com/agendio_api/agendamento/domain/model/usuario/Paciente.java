package com.agendio_api.agendamento.domain.model.usuario;

import com.agendio_api.agendamento.domain.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paciente extends Usuario {
    private LocalDate dataNascimento;

    //construtor de criação
    public Paciente(String nome, String email, String senha, LocalDate dataNascimento) {
        super(nome, email, senha, Role.PACIENTE);
        this.dataNascimento = dataNascimento;
    }

    //construtor de reconstituição
    public Paciente(UUID id, String nome, String email, String senha, LocalDate dataNascimento, boolean ativo,
                    LocalDateTime criadoEm, LocalDateTime atualizadoEm, Role role) {
        this.id = id;
        this.nome = Objects.requireNonNull(nome, "nome nao pode ser nulo");
        this.email = Objects.requireNonNull(email, "email nao pode ser nulo");
        this.senha = Objects.requireNonNull(senha, "senha nao pode ser nulo");
        this.dataNascimento = dataNascimento;
        this.ativo = ativo;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
        this.role = role;
    }
}
