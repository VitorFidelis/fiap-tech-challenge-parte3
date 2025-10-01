package gateway.br.com.gateway.application.usecase.consulta;

import gateway.br.com.gateway.application.dto.consulta.AgendaConsultaDTO;
import gateway.br.com.gateway.application.dto.consulta.ConsultaResponseDTO;
import gateway.br.com.gateway.application.input.consulta.usecase.AgendarConsultaUseCase;
import gateway.br.com.gateway.application.mapper.consulta.IConsultaMapper;
import gateway.br.com.gateway.application.output.consulta.ConsultaGateway;
import gateway.br.com.gateway.domain.model.consulta.Consulta;
import gateway.br.com.gateway.domain.model.validator.ConsultaValidator;
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
