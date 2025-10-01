package gateway.br.com.gateway.application.usecase.medico;


import gateway.br.com.gateway.application.dto.medico.AtualizaMedicoDTO;
import gateway.br.com.gateway.application.dto.medico.MedicoResponseDTO;
import gateway.br.com.gateway.application.input.medico.usecase.AtualizarMedicoUseCase;
import gateway.br.com.gateway.application.mapper.medico.IMedicoMapper;
import gateway.br.com.gateway.application.output.usuario.medico.MedicoGateway;
import gateway.br.com.gateway.domain.exception.UsuarioNaoEncontradoException;
import gateway.br.com.gateway.domain.model.usuario.Medico;
import gateway.br.com.gateway.domain.model.validator.MedicoValidator;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public class AtualizarMedicoUseCaseImpl implements AtualizarMedicoUseCase {
    private final MedicoGateway medicoGateway;
    private final IMedicoMapper medicoMapper;
    private final List<MedicoValidator> medicoValidators;

    public AtualizarMedicoUseCaseImpl(MedicoGateway medicoGateway, IMedicoMapper medicoMapper, List<MedicoValidator> medicoValidators) {
        this.medicoGateway = medicoGateway;
        this.medicoMapper = medicoMapper;
        this.medicoValidators = medicoValidators;
    }

    @Transactional
    @Override
    public MedicoResponseDTO executar(UUID id, AtualizaMedicoDTO atualizaMedicoDTO) {
        Medico medicoExistente = medicoGateway.buscarPorId(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Medico não encontrado."));
        Medico medicoParaValidar = medicoMapper.toDomain(atualizaMedicoDTO, medicoExistente);
        medicoValidators.forEach(medicoValidator -> medicoValidator.validar(medicoParaValidar));
        Medico medicoParaSalvar = medicoGateway.atualizar(medicoParaValidar);
        return medicoMapper.toResponseDTO(medicoParaSalvar);
    }
}
