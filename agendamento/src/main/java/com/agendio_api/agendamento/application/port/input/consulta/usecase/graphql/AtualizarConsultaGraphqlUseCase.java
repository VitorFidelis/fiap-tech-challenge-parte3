package com.agendio_api.agendamento.application.port.input.consulta.usecase.graphql;

import com.agendio_api.agendamento.application.port.dto.consulta.graphql.AtualizarConsultaGraphqlDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.graphql.ConsultaResponseGraphqlDTO;
import com.agendio_api.agendamento.domain.model.usuario.Usuario;
import com.agendio_api.agendamento.infrastructure.security.UserPrincipal;

import java.util.UUID;

public interface AtualizarConsultaGraphqlUseCase {
    ConsultaResponseGraphqlDTO executar(AtualizarConsultaGraphqlDTO request, UserPrincipal usuarioLogado);
}
