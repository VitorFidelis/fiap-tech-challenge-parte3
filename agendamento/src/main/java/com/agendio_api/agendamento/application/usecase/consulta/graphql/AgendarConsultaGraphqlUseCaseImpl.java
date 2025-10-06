package com.agendio_api.agendamento.application.usecase.consulta.graphql;

import com.agendio_api.agendamento.application.port.dto.consulta.graphql.AgendarConsultaGraphqlDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.graphql.AtualizarConsultaGraphqlDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.graphql.ConsultaResponseGraphqlDTO;
import com.agendio_api.agendamento.application.port.input.consulta.usecase.graphql.AgendarConsultaGraphqlUseCase;
import com.agendio_api.agendamento.application.port.input.consulta.usecase.graphql.AtualizarConsultaGraphqlUseCase;
import com.agendio_api.agendamento.application.port.mapper.consulta.IConsultaMapper;
import com.agendio_api.agendamento.application.port.output.consulta.ConsultaGateway;
import com.agendio_api.agendamento.domain.exception.ConsultaNaoEncontradaException;
import com.agendio_api.agendamento.domain.model.consulta.Consulta;
import com.agendio_api.agendamento.infrastructure.exception.AccessDeniedException;
import com.agendio_api.agendamento.infrastructure.security.UserPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class AgendarConsultaGraphqlUseCaseImpl implements AgendarConsultaGraphqlUseCase {

    private final ConsultaGateway consultaGateway;
    private final IConsultaMapper consultaMapper;

    public AgendarConsultaGraphqlUseCaseImpl(ConsultaGateway consultaGateway, IConsultaMapper consultaMapper) {
        this.consultaGateway = consultaGateway;
        this.consultaMapper = consultaMapper;
    }

    @Override
    public ConsultaResponseGraphqlDTO executar(AgendarConsultaGraphqlDTO request, UserPrincipal usuarioLogado) {

        var roleMedico = new SimpleGrantedAuthority("ROLE_MEDICO");
        var roleEnfermeiro = new SimpleGrantedAuthority("ROLE_ENFERMEIRO");
        var authorities = usuarioLogado.getAuthorities();

        if (!authorities.contains(roleMedico) && !authorities.contains(roleEnfermeiro)) {
            throw new AccessDeniedException("Usuário não autorizado a agendar consultas");
        }

        Consulta consultaParaAgendar = consultaMapper.toDomain(request);
        Consulta consultaParaSalvar = consultaGateway.agendar(consultaParaAgendar);
        return consultaMapper.toConsultaGraphqlDTO(consultaParaSalvar);
    }

}
