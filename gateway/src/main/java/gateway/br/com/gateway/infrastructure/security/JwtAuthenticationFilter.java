package gateway.br.com.gateway.infrastructure.security;

import gateway.br.com.gateway.domain.exception.TokenJwtInvalidoOuExpirado;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    public JwtAuthenticationFilter(final JwtService jwtService, CustomUserDetailsService customUserDetailsService) {
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    /**
     * <h1>Metodo doFilterInternal()</h1>
     *
     * <p>doFilterInternal serve para implementar a lógica principal de um filtro
     * personalizado em aplicações Spring, como o Spring Security, para interceptar e
     * processar solicitações HTTP antes que elas cheguem aos seus controladores.</p>
     *
     * <p>É aqui que se realiza a autenticação, a autorização, o logging ou qualquer
     * outra manipulação da requisição e resposta.</p>
     *
     * @param request -> Recebimento da requisição HTTP.;
     * @param response -> Retorno da resposta HTTP;
     * @param filterChain -> Filtro antes de chegar no controlador,
     *                    permitindo processar a requisição antes que ela chegue ao endpoint final;
     * */
    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            // tenta extrair o token
            var recuperarTokenHeader = extractToken(request);
            // valida se o token é diferente de nulo
            if (recuperarTokenHeader != null) {
                // Esta vindo token, valida o token
                var verifierToken = jwtService.verifierToken(recuperarTokenHeader);
                // valida se este token esta correto, vai no banco pega o email e ve se existe, e cria o objeto
                var userDetails = customUserDetailsService.loadUserByUsername(verifierToken);
                // se estiver tudo certo pega
                var authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,null, userDetails.getAuthorities()
                );
                System.out.println("Authorities: " + userDetails.getAuthorities());
                // Força a autenticação
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            //invoca o próximo filtro na cadeia, permitindo que a requisição continue seu fluxo normal.
            filterChain.doFilter(request, response);

        } catch (TokenJwtInvalidoOuExpirado ex) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(ex.getLocalizedMessage());
        }
    }

    /**
     * <h1>Metodo extractToken()</h1>
     *
     * <p>Responsavel por fazer a extração do token enviado
     * no cabeçalho e validar se não e nulo.</p>
     *
     * @param request -> Acessa o cabeçalho Authorization;
     * @return -> retorna o token extraido do cabeçalho(Authorization);
     * */
    private String extractToken(HttpServletRequest request) {
        //Acesso ao cabeçalho "Authorization": No servlet ou controller, obtem o valor do cabeçalho Authorization da requisição HTTP.
        var authorizationHeader = request.getHeader("Authorization");
        //Verifique se o cabeçalho existe: Verifique se o valor retornado é diferente de nulo.
        if (authorizationHeader != null) {
            // Substitui "Bearer " por nada
            return authorizationHeader.replace("Bearer ", "").trim();
        }
        return null;
    }
}
