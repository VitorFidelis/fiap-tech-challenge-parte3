package com.agendio_api.agendamento.infrastructure.adapter.controller.graphql;

import com.agendio_api.agendamento.application.port.dto.consulta.graphql.AgendarConsultaGraphqlDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.graphql.AtualizarConsultaGraphqlDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.graphql.ConsultaResponseGraphqlDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.graphql.ListarConsultaGraphqlDTO;
import com.agendio_api.agendamento.application.port.input.consulta.controller.ConsultaControllerInputPort;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ConsultaGraphQLController {

    private final ConsultaControllerInputPort consultaControllerInputPort;

    public ConsultaGraphQLController(ConsultaControllerInputPort consultaControllerInputPort) {
        this.consultaControllerInputPort = consultaControllerInputPort;
    }

    @QueryMapping
    public List<ConsultaResponseGraphqlDTO> listarConsultas(@Argument("input") ListarConsultaGraphqlDTO filtros) {
        return consultaControllerInputPort.listarPorFiltros(filtros);
    }

    @MutationMapping
    public ConsultaResponseGraphqlDTO atualizarConsulta(@Argument("input") AtualizarConsultaGraphqlDTO input) {
        input.validar();
        return consultaControllerInputPort.atualizarGraphql(input);
    }

    @MutationMapping
    public ConsultaResponseGraphqlDTO agendarConsulta(@Argument("input") AgendarConsultaGraphqlDTO input) {
        input.validar();
        return consultaControllerInputPort.agendarGraphql(input);
    }
}
