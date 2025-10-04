package com.agendio_api.agendamento.application.port.input.consulta.usecase.graphql;

import com.agendio_api.agendamento.application.port.dto.consulta.graphql.ConsultaResponseGraphqlDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.graphql.ListarConsultaGraphqlDTO;
import com.agendio_api.agendamento.domain.model.usuario.Usuario;

import java.util.List;

public interface ListarConsultasGraphqlUseCase {
    List<ConsultaResponseGraphqlDTO> executar(ListarConsultaGraphqlDTO filtro, Usuario usuarioLogado);
}
