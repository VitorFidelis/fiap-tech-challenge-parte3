package com.agendio_api.agendamento.infrastructure.security;

import com.agendio_api.agendamento.infrastructure.exception.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthenticatedUserService {

    /**
     * Retorna o UserPrincipal do contexto de segurança.
     * @return UserPrincipal do usuário logado.
     * @throws IllegalStateException se não houver usuário autenticado (não deveria acontecer em rotas protegidas).
     */
    public UserPrincipal getCurrentUserPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            throw new AccessDeniedException("Usuário não autenticado");
        }

        return (UserPrincipal) authentication.getPrincipal();
    }

    public UUID getCurrentUserId() {
        return getCurrentUserPrincipal().getId();
    }

    public String getCurrentUserRole() {
        return getCurrentUserPrincipal().getRole();
    }
}

