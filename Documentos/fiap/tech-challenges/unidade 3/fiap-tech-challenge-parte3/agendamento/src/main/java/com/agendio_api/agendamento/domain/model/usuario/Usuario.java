package com.agendio_api.agendamento.domain.model.usuario;

import com.agendio_api.agendamento.domain.common.BaseEntity;
import com.agendio_api.agendamento.domain.model.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Usuario extends BaseEntity {

    protected String nome;
    protected String email;
    protected String senha;
    protected Role role;

    protected Usuario() {
        super();
    }

    protected Usuario(String nome, String email, String senha, Role role) {
        super();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

    public void reativar() {
        if (!this.ativo) {
            this.ativo = true;
            touch();
        }
    }
}