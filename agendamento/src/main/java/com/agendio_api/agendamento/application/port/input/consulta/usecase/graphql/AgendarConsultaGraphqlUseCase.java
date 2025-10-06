package com.agendio_api.agendamento.application.port.input.consulta.usecase.graphql;

import com.agendio_api.agendamento.application.port.dto.consulta.graphql.AgendarConsultaGraphqlDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.graphql.AtualizarConsultaGraphqlDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.graphql.ConsultaResponseGraphqlDTO;
import com.agendio_api.agendamento.infrastructure.security.UserPrincipal;

public interface AgendarConsultaGraphqlUseCase {
    ConsultaResponseGraphqlDTO executar(AgendarConsultaGraphqlDTO request, UserPrincipal usuarioLogado);
}
