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
import com.agendio_api.agendamento.infrastructure.security.UserPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class ListarConsultasGraphqlUseCaseImpl implements ListarConsultasGraphqlUseCase {

    private final ConsultaGateway consultaGateway;
    private final IConsultaMapper consultaMapper;

    public ListarConsultasGraphqlUseCaseImpl(ConsultaGateway consultaGateway, IConsultaMapper consultaMapper) {
        this.consultaGateway = consultaGateway;
        this.consultaMapper = consultaMapper;
    }

    @Override
    public List<ConsultaResponseGraphqlDTO> executar(ListarConsultaGraphqlDTO filtro, UserPrincipal usuarioLogado) {
        aplicarEscopoUsuario(filtro, usuarioLogado);

        List<Consulta> consulta = consultaGateway.listagemConsultaGraphql(filtro);

        return consulta
                .stream()
                .map(consultaMapper::toConsultaGraphqlDTO)
                .toList();
    }

    private void aplicarEscopoUsuario(ListarConsultaGraphqlDTO filtro, UserPrincipal usuario) {

        var roleMedico = new SimpleGrantedAuthority("ROLE_MEDICO");
        var roleEnfermeiro = new SimpleGrantedAuthority("ROLE_ENFERMEIRO");
        var rolePaciente = new SimpleGrantedAuthority("ROLE_PACIENTE");
        var authorities = usuario.getAuthorities();

        if (authorities.contains(roleMedico)) {
            filtro.setMedicoId(usuario.getId());
        } else if (authorities.contains(roleEnfermeiro)) {
            filtro.setEnfermeiroId(usuario.getId());
        } else if (authorities.contains(rolePaciente)) {
            filtro.setPacienteId(usuario.getId());
        } else {
            throw new AccessDeniedException("Usuário não autorizado a consultar agendamentos");
        }
    }
}
