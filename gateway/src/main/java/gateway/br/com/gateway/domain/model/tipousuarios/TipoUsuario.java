package gateway.br.com.gateway.domain.model;

public class TipoUsuario {
    private Long id;
    private TipoUsuarioEnum nome;
    private String descricao;
    private Boolean ativo = true;

    public TipoUsuario() {

    }

    public TipoUsuario(Long id, TipoUsuarioEnum nome, String descricao, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoUsuarioEnum getNome() {
        return nome;
    }

    public void setNome(TipoUsuarioEnum nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
