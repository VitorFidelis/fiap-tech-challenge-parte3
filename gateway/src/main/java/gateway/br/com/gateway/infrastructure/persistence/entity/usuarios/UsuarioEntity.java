package gateway.br.com.gateway.infrastructure.persistence.entity.usuarios;

import gateway.br.com.gateway.infrastructure.persistence.entity.tipousuarios.TipoUsuarioEntity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100)
    private String sobrenome;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String senha;

    @Column(nullable = false)
    private Boolean ativo = true;

    @ManyToOne
    @JoinColumn(name = "tipousuario_id", referencedColumnName = "id", nullable = false)
    private TipoUsuarioEntity tipoUsuario;

    public UsuarioEntity() {

    }

    public UsuarioEntity(
            UUID id,
            String nome,
            String sobrenome,
            String email,
            String senha,
            Boolean ativo,
            TipoUsuarioEntity tipoUsuario
    ) {
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

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(final String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(final String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(final Boolean ativo) {
        this.ativo = ativo;
    }

    public TipoUsuarioEntity getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(final TipoUsuarioEntity tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
