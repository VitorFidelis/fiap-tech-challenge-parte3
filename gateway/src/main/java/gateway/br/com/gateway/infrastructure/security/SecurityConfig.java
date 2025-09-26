package gateway.br.com.gateway.infrastructure.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(
            final CustomUserDetailsService userDetailsService,
            final JwtAuthenticationFilter jwtAuthenticationFilter
    ) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    /*
    * desabilitação do CSRF e
    * habilitamos a autenticação stateless do Spring.
    * */
    @Bean
    public SecurityFilterChain securityFilterChain(
            final HttpSecurity httpSecurity
    ) throws Exception {
        return httpSecurity
                /*
                * Cfrf() -> Serve para desabilitarmos proteção contra-ataques do tipo CSRF (Cross-Site Request Forgery)
                * Estamos desabilitando esse tipo de ataque porque vamos trabalhar com autenticação via tokens.
                * Nesse cenário, o próprio token é uma proteção contra esses tipos de ataques e ficaria repetitivo.
                 * */
                .csrf(csrf -> csrf.disable())
                /**-
                * sessionManagement()
                * mostrar o gerenciamento da sessão
                * sessionCreationPolicy()
                * política de criação da sessão
                * */
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    req.requestMatchers("/swagger-ui.html","/v3/api-docs/**","/swagger-ui/**").permitAll();

                    req.requestMatchers(HttpMethod.POST, "/login").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/tipousuarios").hasRole("SUPERADMIN");
                    req.requestMatchers(HttpMethod.POST, "/usuarios").hasAnyRole("SUPERADMIN","ADMIN");

                    req.requestMatchers(HttpMethod.PUT, "/tipousuarios/").hasRole("SUPERADMIN");
                    req.requestMatchers(HttpMethod.PUT,"/usuarios/").hasAnyRole("SUPERADMIN","ADMIN");

                    req.requestMatchers(HttpMethod.DELETE,"/tipousuarios/").hasAnyRole("SUPERADMIN");
                    req.requestMatchers(HttpMethod.DELETE, "/usuarios/").hasAnyRole("SUPERADMIN","ADMIN");

                    req.requestMatchers(HttpMethod.PATCH,"/tipousuarios/").hasRole("SUPERADMIN");
                    req.requestMatchers(HttpMethod.PATCH,"/usuarios/").hasAnyRole("SUPERADMIN","ADMIN");

                    req.requestMatchers(HttpMethod.GET,"/tipousuarios/").hasAnyRole("SUPERADMIN","ADMIN");
                    req.requestMatchers(HttpMethod.GET,"/tipousuarios").hasAnyRole("SUPERADMIN","ADMIN");

                    req.requestMatchers(HttpMethod.GET,"/usuarios/").hasAnyRole("SUPERADMIN","ADMIN");
                    req.requestMatchers(HttpMethod.GET,"/usuarios").hasAnyRole("SUPERADMIN","ADMIN");

                    req.anyRequest().authenticated();
                })
                // mudando a ordem dos filtro, colocando em primeiro lugar o meu filtro customizado depois do Spring
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();// criar o objeto SecurityFilterChain
    }

    /*
    *o AuthenticationManager é o componente central que verifica as credenciais
    * de um usuário e retorna uma Authentication se a autenticação for bem-sucedida,
    * ou lança uma exceção se falhar.
    * A AuthenticationConfiguration é usada para configurar o AuthenticationManager,
    * especificando como ele deve se comportar, por exemplo, como ele deve ser composto
    * por outros componentes como DaoAuthenticationProvide para autenticação de nome de usuário/senha.
    * */
    @Bean
    public AuthenticationManager authenticationManager(
            final AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /*
    * codificador de senha.
    *
    * serve para realizar uma transformação unidirecional (sem retorno)
    * de uma senha, tornando-a segura para armazenamento.
    *
    * Essa transformação é utilizada para comparar a
    * senha armazenada com a senha fornecida pelo usuário durante o
    * processo de autenticação, garantindo que a senha do usuário não
    * seja armazenada em texto claro, o que aumentaria a segurança da aplicação.
     * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
    * O DaoAuthenticationProvider é um componente do Spring Security
    * que serve para autenticar um usuário a partir de um banco de dados,
    * usando um UserDetailsService para obter as informações do usuário (como nome de usuário, senha e permissões)
    * e um PasswordEncoder para verificar a senha do usuário.
    *
    * Ele é a implementação padrão do Spring Security para autenticação de nome de usuário e senha
    * e utiliza as credenciais recuperadas do banco para confirmar a identidade do utilizador.
     * */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
