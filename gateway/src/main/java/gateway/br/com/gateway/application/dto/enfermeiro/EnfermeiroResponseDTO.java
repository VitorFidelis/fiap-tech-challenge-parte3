package gateway.br.com.gateway.application.dto.enfermeiro;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import gateway.br.com.gateway.domain.model.valueobject.Coren;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO para representar a resposta de um Enfermeiro na API.
 * Segue o princípio de responsabilidade única (SRP) atuando apenas como portador de dados.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record EnfermeiroResponseDTO(
        UUID id,
        String nome,
        String email,
        Coren coren,
        boolean ativo,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime criadoEm,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime atualizadoEm
) {

}
