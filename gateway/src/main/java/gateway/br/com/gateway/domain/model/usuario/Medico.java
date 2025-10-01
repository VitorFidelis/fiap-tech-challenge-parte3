package gateway.br.com.gateway.domain.model.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Medico extends Usuario {

    private String crm;
    private String especialidade;

    //construtor de criação


    public Medico(String nome, String email, String senha, String crm, String especialidade) {
        super(nome, email, senha);
        this.crm = crm;
        this.especialidade = especialidade;
    }

    //construtor de reconstituição
    public Medico(UUID id, String nome, String email, String senha, String crm, String especialidade, boolean ativo, LocalDateTime criadoEm, LocalDateTime atualizadoEm) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.crm = crm;
        this.especialidade = especialidade;
        this.ativo = ativo;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
    }
}

