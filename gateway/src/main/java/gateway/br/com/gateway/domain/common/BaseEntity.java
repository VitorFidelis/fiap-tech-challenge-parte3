package gateway.br.com.gateway.domain.common;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public abstract class BaseEntity {

    protected UUID id;
    protected LocalDateTime criadoEm = LocalDateTime.now();
    protected LocalDateTime atualizadoEm = LocalDateTime.now();
    protected boolean ativo = true;

    protected BaseEntity(UUID id, LocalDateTime criadoEm, LocalDateTime atualizadoEm) {
        this.id = id;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
        this.ativo = true;
    }

    protected BaseEntity() {
        // construtor vazio exigido por JPA
    }

    /**
     * Cria uma nova entidade
     */
    protected void inicializar() {
        this.id = UUID.randomUUID();
        this.criadoEm = LocalDateTime.now();
        this.atualizadoEm = LocalDateTime.now();
        this.ativo = true;
    }

    /**
     * Atualiza a data de modificação
     */
    public void touch() {
        this.atualizadoEm = LocalDateTime.now();
    }

    public void desativar() {
        if (this.ativo) {
            this.ativo = false;
            touch();
        }
    }

}
