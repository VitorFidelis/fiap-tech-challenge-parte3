package gateway.br.com.gateway.domain.model.usuario;

import gateway.br.com.gateway.domain.model.tipousuarios.TipoUsuario;

import java.util.UUID;

public class Usuario {
    private UUID id;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private Boolean ativo = true;
    private TipoUsuario tipoUsuario;

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
}
