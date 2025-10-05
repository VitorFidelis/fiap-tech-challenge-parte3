package com.agendio_api.agendamento.application.usecase.consulta.graphql;

import com.agendio_api.agendamento.application.port.dto.consulta.graphql.ConsultaResponseGraphqlDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.graphql.ListarConsultaGraphqlDTO;
import com.agendio_api.agendamento.application.port.input.consulta.usecase.graphql.ListarConsultasGraphqlUseCase;
import com.agendio_api.agendamento.application.port.mapper.consulta.IConsultaMapper;
import com.agendio_api.agendamento.application.port.output.consulta.ConsultaGateway;
import com.agendio_api.agendamento.domain.model.consulta.Consulta;
import com.agendio_api.agendamento.domain.model.usuario.Enfermeiro;
import com.agendio_api.agendamento.domain.model.usuario.Medico;
import com.agendio_api.agendamento.domain.model.usuario.Paciente;
import com.agendio_api.agendamento.domain.model.usuario.Usuario;
import com.agendio_api.agendamento.infrastructure.exception.AccessDeniedException;

import java.util.List;

public class ListarConsultasGraphqlUseCaseImpl implements ListarConsultasGraphqlUseCase {

    private final ConsultaGateway consultaGateway;
    private final IConsultaMapper consultaMapper;

    public ListarConsultasGraphqlUseCaseImpl(ConsultaGateway consultaGateway, IConsultaMapper consultaMapper) {
        this.consultaGateway = consultaGateway;
        this.consultaMapper = consultaMapper;
    }

    @Override
    public List<ConsultaResponseGraphqlDTO> executar(ListarConsultaGraphqlDTO filtro, Usuario usuarioLogado) {
        aplicarEscopoUsuario(filtro, usuarioLogado);

        List<Consulta> consulta = consultaGateway.listagemConsultaGraphql(filtro);

        return consulta
                .stream()
                .map(consultaMapper::toConsultaGraphqlDTO)
                .toList();
    }

    private void aplicarEscopoUsuario(ListarConsultaGraphqlDTO filtro, Usuario usuario) {
        if (usuario instanceof Medico medico) {
            filtro.setMedicoId(medico.getId());
        } else if (usuario instanceof Enfermeiro enfermeiro) {
            filtro.setEnfermeiroId(enfermeiro.getId());
        } else if (usuario instanceof Paciente paciente) {
            filtro.setPacienteId(paciente.getId());
        } else {
            throw new AccessDeniedException("Usuário não autorizado a consultar agendamentos");
        }
    }
}
