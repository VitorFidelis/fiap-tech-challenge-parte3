package gateway.br.com.gateway.application.usecase;

import gateway.br.com.gateway.application.dto.CreateTipoUsuarioDto;
import gateway.br.com.gateway.domain.model.TipoUsuario;
import gateway.br.com.gateway.infrastructure.persistence.adapter.TipoUsuarioRepositoryImpl;

public class CriarTipoUsuarioUseCase {
    private final TipoUsuarioRepositoryImpl tipoUsuarioRepository;

    public CriarTipoUsuarioUseCase(TipoUsuarioRepositoryImpl tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public TipoUsuario execute(CreateTipoUsuarioDto createTipoUsuarioDto) {
        TipoUsuario tipoUsuario = new TipoUsuario();

        validaNomeTipousuario(createTipoUsuarioDto);
        tipoUsuario.setDescricao(createTipoUsuarioDto.descricao());
        tipoUsuario.setAtivo(createTipoUsuarioDto.ativo());

        return this.tipoUsuarioRepository.save(tipoUsuario);
    }

    private void validaNomeTipousuario(CreateTipoUsuarioDto createTipoUsuarioDto) {
        TipoUsuario tipoUsuario = new TipoUsuario();
        var nomeString = createTipoUsuarioDto.nome();
        if (nomeString.name() == "ADMIN" ||
                nomeString.name() == "MEDICO" ||
                nomeString.name() == "ENFERMEIRO" ||
                nomeString.name() == "PACIENTE"
        ) {
            tipoUsuario.setNome(nomeString);
        }
    }
}
