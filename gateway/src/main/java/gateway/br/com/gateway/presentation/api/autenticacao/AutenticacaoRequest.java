package gateway.br.com.gateway.presentation.api.autenticacao;

public record AutenticacaoRequest(
        String email,
        String senha
) {

}
