package gateway.br.com.gateway.domain.model.usuario;

import gateway.br.com.gateway.domain.common.BaseEntity;
import gateway.br.com.gateway.domain.model.tipousuarios.TipoUsuario;

import java.util.UUID;

public class Usuario extends BaseEntity {
    protected UUID id;
    protected String nome;
    protected String sobrenome;
    protected String email;
    protected String senha;
    protected Boolean ativo = true;
    protected TipoUsuario tipoUsuario;

    public Usuario() {

    }

    public Usuario(UUID id, String nome, String sobrenome, String email, String senha, Boolean ativo, TipoUsuario tipoUsuario) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.ativo = true;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario(String nome, String email, String senha) {
        super();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void reativar() {
        if (!this.ativo) {
            this.ativo = true;
            touch();
        }
    }
}
