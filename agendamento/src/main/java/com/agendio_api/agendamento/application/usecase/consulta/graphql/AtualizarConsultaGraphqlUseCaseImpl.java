package com.agendio_api.agendamento.application.usecase.consulta.graphql;

import com.agendio_api.agendamento.application.port.dto.consulta.graphql.AtualizarConsultaGraphqlDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.graphql.ConsultaResponseGraphqlDTO;
import com.agendio_api.agendamento.application.port.input.consulta.usecase.graphql.AtualizarConsultaGraphqlUseCase;
import com.agendio_api.agendamento.application.port.mapper.consulta.IConsultaMapper;
import com.agendio_api.agendamento.application.port.output.consulta.ConsultaGateway;
import com.agendio_api.agendamento.domain.exception.ConsultaNaoEncontradaException;
import com.agendio_api.agendamento.domain.model.consulta.Consulta;
import com.agendio_api.agendamento.domain.model.usuario.Enfermeiro;
import com.agendio_api.agendamento.domain.model.usuario.Medico;
import com.agendio_api.agendamento.domain.model.usuario.Usuario;
import com.agendio_api.agendamento.infrastructure.exception.AccessDeniedException;
import com.agendio_api.agendamento.infrastructure.security.UserPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class AtualizarConsultaGraphqlUseCaseImpl implements AtualizarConsultaGraphqlUseCase {

    private final ConsultaGateway consultaGateway;
    private final IConsultaMapper consultaMapper;

    public AtualizarConsultaGraphqlUseCaseImpl(ConsultaGateway consultaGateway, IConsultaMapper consultaMapper) {
        this.consultaGateway = consultaGateway;
        this.consultaMapper = consultaMapper;
    }

    @Override
    public ConsultaResponseGraphqlDTO executar(AtualizarConsultaGraphqlDTO request, UserPrincipal usuarioLogado) {
        Consulta consultaExistente = consultaGateway.buscarPorId(request.id())
                .orElseThrow(() -> new ConsultaNaoEncontradaException("Consulta com ID " + request.id() + " nao encontrada."));

        var roleMedico = new SimpleGrantedAuthority("ROLE_MEDICO");
        var roleEnfermeiro = new SimpleGrantedAuthority("ROLE_ENFERMEIRO");
        var authorities = usuarioLogado.getAuthorities();

        if (authorities.contains(roleMedico)) {
            if (!consultaExistente.getMedicoId().equals(usuarioLogado.getId())) {
                throw new AccessDeniedException("Médico não autorizado para esta consulta");
            }
        } else if (authorities.contains(roleEnfermeiro)) {
            if (!consultaExistente.getEnfermeiroId().equals(usuarioLogado.getId())) {
                throw new AccessDeniedException("Enfermeiro não autorizado para esta consulta");
            }
        } else {
            throw new AccessDeniedException("Usuário não autorizado a atualizar consultas");
        }

        Consulta consultaParaValidar = consultaMapper.toDomain(request, consultaExistente);
        Consulta consultaParaSalvar = consultaGateway.atualizar(consultaParaValidar);
        return consultaMapper.toConsultaGraphqlDTO(consultaParaSalvar);
    }
}
