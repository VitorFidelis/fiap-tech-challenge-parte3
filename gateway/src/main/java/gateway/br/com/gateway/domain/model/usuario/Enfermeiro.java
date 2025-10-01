package gateway.br.com.gateway.domain.model.usuario;

import gateway.br.com.gateway.domain.model.valueobject.Coren;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
public class Enfermeiro extends Usuario {

    private Coren coren;

    //construtor de criação
    public Enfermeiro(String nome, String email, String senha, Coren coren) {
        super(nome, email, senha);
        this.coren = Objects.requireNonNull(coren, "COREN nao pode ser nulo");
    }

    //construtor de reconstituição
    public Enfermeiro(UUID id, String nome, String email, String senha, Coren coren, boolean ativo, LocalDateTime criadoEm, LocalDateTime atualizadoEm) {
        this.id = id;
        this.nome = Objects.requireNonNull(nome, "nome nao pode ser nulo");
        this.email = Objects.requireNonNull(email, "email nao pode ser nulo");
        this.senha = Objects.requireNonNull(senha, "senha nao pode ser nulo");
        this.coren = Objects.requireNonNull(coren, "COREN nao pode ser nulo");
        this.ativo = ativo;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
    }


}

