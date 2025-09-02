package gateway.br.com.gateway.infrastructure.persistence.entity;

import gateway.br.com.gateway.domain.model.TipoUsuarioEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "tipo_usuarios")
public class TipoUsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuarioEnum nome;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Boolean ativo;

    public TipoUsuarioEntity() {

    }

    public TipoUsuarioEntity(Long id, TipoUsuarioEnum nome, String descricao, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public TipoUsuarioEnum getNome() {
        return nome;
    }

    public void setNome(final TipoUsuarioEnum nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(final Boolean ativo) {
        this.ativo = ativo;
    }
}
