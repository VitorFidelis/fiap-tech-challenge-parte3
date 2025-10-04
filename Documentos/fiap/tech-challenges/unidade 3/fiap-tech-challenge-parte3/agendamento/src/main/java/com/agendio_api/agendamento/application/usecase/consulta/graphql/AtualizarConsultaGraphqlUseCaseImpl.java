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

public class AtualizarConsultaGraphqlUseCaseImpl implements AtualizarConsultaGraphqlUseCase {

    private final ConsultaGateway consultaGateway;
    private final IConsultaMapper consultaMapper;

    public AtualizarConsultaGraphqlUseCaseImpl(ConsultaGateway consultaGateway, IConsultaMapper consultaMapper) {
        this.consultaGateway = consultaGateway;
        this.consultaMapper = consultaMapper;
    }

    @Override
    public ConsultaResponseGraphqlDTO executar(AtualizarConsultaGraphqlDTO request, Usuario usuarioLogado) {
        Consulta consultaExistente = consultaGateway.buscarPorId(request.id())
                .orElseThrow(() -> new ConsultaNaoEncontradaException("Consulta com ID " + request.id() + " nao encontrada."));

        if (usuarioLogado instanceof Medico medico) {
            if (!consultaExistente.getMedicoId().equals(medico.getId())) {
                throw new AccessDeniedException("Médico não autorizado para esta consulta");
            }
        } else if (usuarioLogado instanceof Enfermeiro enfermeiro) {
            if (!consultaExistente.getEnfermeiroId().equals(enfermeiro.getId())) {
                throw new AccessDeniedException("Enfermeiro não autorizado para esta consulta");
            }
        } else {
            throw new AccessDeniedException("Usuário não autorizado");
        }

        Consulta consultaParaValidar = consultaMapper.toDomain(request, consultaExistente);
        Consulta consultaParaSalvar = consultaGateway.atualizar(consultaParaValidar);
        return consultaMapper.toConsultaGraphqlDTO(consultaParaSalvar);
    }
}
