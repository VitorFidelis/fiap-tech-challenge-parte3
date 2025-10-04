package com.agendio_api.agendamento.application.usecase.consulta;

import com.agendio_api.agendamento.application.port.dto.consulta.AgendaConsultaDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaResponseDTO;
import com.agendio_api.agendamento.application.port.input.consulta.usecase.AgendarConsultaUseCase;
import com.agendio_api.agendamento.application.port.mapper.consulta.IConsultaMapper;
import com.agendio_api.agendamento.application.port.output.consulta.ConsultaGateway;
import com.agendio_api.agendamento.domain.model.consulta.Consulta;
import com.agendio_api.agendamento.domain.validator.ConsultaValidator;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class AgendarConsultaUseCaseImpl implements AgendarConsultaUseCase {

    private final ConsultaGateway consultaGateway;
    private final IConsultaMapper consultaMapper;
    private final List<ConsultaValidator> consultaValidators;

    public AgendarConsultaUseCaseImpl(ConsultaGateway consultaGateway, IConsultaMapper consultaMapper, List<ConsultaValidator> consultaValidators) {
        this.consultaGateway = consultaGateway;
        this.consultaMapper = consultaMapper;
        this.consultaValidators = consultaValidators;
    }

    @Transactional
    @Override
    public ConsultaResponseDTO executar(AgendaConsultaDTO agendaConsultaDTO) {
        Consulta consulta = consultaMapper.toDomain(agendaConsultaDTO);
        validaConsulta(consulta);
       Consulta consultaSalva = consultaGateway.agendar(consulta);
        return consultaMapper.toResponseDTO(consultaSalva);
    }

    private void validaConsulta(Consulta consulta) {
        for (ConsultaValidator validator : consultaValidators) {
            validator.validar(consulta);
        }
    }
}
