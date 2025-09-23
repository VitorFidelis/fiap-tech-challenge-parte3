package gateway.br.com.gateway.infrastructure.security;

import gateway.br.com.gateway.infrastructure.persistence.adapter.usuarios.UsuarioRepositoryImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepositoryImpl usuarioRepositoryImpl;

    public CustomUserDetailsService(final UsuarioRepositoryImpl usuarioRepositoryImpl) {
        this.usuarioRepositoryImpl = usuarioRepositoryImpl;
    }

    @Override
    public UserDetails loadUserByUsername(
            final String email
    ) throws UsernameNotFoundException {
        var usuario = this.usuarioRepositoryImpl.findByEmail(email);
        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado com email: " + email);
        }
        return new UserDetailsImpl(usuario);
    }
}
