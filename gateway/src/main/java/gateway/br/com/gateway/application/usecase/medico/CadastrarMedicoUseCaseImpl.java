package gateway.br.com.gateway.application.usecase.medico;

import gateway.br.com.gateway.application.dto.medico.CadastraMedicoDTO;
import gateway.br.com.gateway.application.dto.medico.MedicoResponseDTO;
import gateway.br.com.gateway.application.input.medico.usecase.CadastrarMedicoUseCase;
import gateway.br.com.gateway.application.mapper.medico.IMedicoMapper;
import gateway.br.com.gateway.application.output.usuario.medico.MedicoGateway;
import gateway.br.com.gateway.domain.model.usuario.Medico;
import gateway.br.com.gateway.domain.model.validator.MedicoValidator;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CadastrarMedicoUseCaseImpl implements CadastrarMedicoUseCase {
    private final MedicoGateway medicoGateway;
    private final IMedicoMapper medicoMapper;
    private final List<MedicoValidator> medicoValidators;

    public CadastrarMedicoUseCaseImpl(MedicoGateway medicoGateway, IMedicoMapper medicoMapper, List<MedicoValidator> medicoValidators) {
        this.medicoGateway = medicoGateway;
        this.medicoMapper = medicoMapper;
        this.medicoValidators = medicoValidators;
    }

    @Transactional
    @Override
    public MedicoResponseDTO executar(CadastraMedicoDTO cadastraMedicoDTO) {
        Medico medico = medicoMapper.toDomain(cadastraMedicoDTO);
        validaMedico(medico);
        medico = medicoGateway.cadastrar(medico);
        return medicoMapper.toResponseDTO(medico);
    }

    private void validaMedico(Medico medico) {
        for (MedicoValidator medicoValidator : medicoValidators) {
            medicoValidator.validar(medico);
        }
    }
}
