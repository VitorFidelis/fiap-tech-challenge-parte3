package gateway.br.com.gateway.presentation.controller.usuarios;

import gateway.br.com.gateway.application.usecase.usuarios.*;
import gateway.br.com.gateway.presentation.api.usuarios.UsuarioRequest;
import gateway.br.com.gateway.presentation.api.usuarios.UsuarioResponseDetalhado;
import gateway.br.com.gateway.presentation.mapper.usuarios.UsuarioApiMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final CreateUsuarioUseCase createUsuarioUseCase;
    private final FindUsuarioByIdUseCase findUsuarioByIdUseCase;
    private final UpdateUsuarioUseCase updateUsuarioUseCase;
    private final FindUsuarioAllUseCase findUsuarioAllUseCase;
    private final DeactivateUsuarioUseCase deactivateUsuarioUseCase;
    private final ReactivateUsuarioUseCase reactivateUsuarioUseCase;
    private final UsuarioApiMapper usuarioApiMapper;


    public UsuarioController(
            final CreateUsuarioUseCase createUsuarioUseCase,
            final FindUsuarioByIdUseCase findUsuarioByIdUseCase,
            final UpdateUsuarioUseCase updateUsuarioUseCase,
            final FindUsuarioAllUseCase findUsuarioAllUseCase,
            final DeactivateUsuarioUseCase deactivateUsuarioUseCase,
            final ReactivateUsuarioUseCase reactivateUsuarioUseCase1,
            final UsuarioApiMapper usuarioApiMapper
    ) {
        this.createUsuarioUseCase = createUsuarioUseCase;
        this.findUsuarioByIdUseCase = findUsuarioByIdUseCase;
        this.updateUsuarioUseCase = updateUsuarioUseCase;
        this.findUsuarioAllUseCase = findUsuarioAllUseCase;
        this.deactivateUsuarioUseCase = deactivateUsuarioUseCase;
        this.reactivateUsuarioUseCase = reactivateUsuarioUseCase1;
        this.usuarioApiMapper = usuarioApiMapper;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDetalhado> createUsuario(
            @RequestBody final UsuarioRequest usuarioRequest,
            final UriComponentsBuilder uriComponentsBuilder
    ) {
        var createUsuarioDto = this.usuarioApiMapper.fromUsuarioApplicationCreate(usuarioRequest);
        var usuarioDomain = this.createUsuarioUseCase.execute(createUsuarioDto);
        var usuarioResponseDetalhado = this.usuarioApiMapper.fromUsuarioPresentation(usuarioDomain);
        var uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuarioDomain.getId()).toUri();
        return ResponseEntity.created(uri).body(usuarioResponseDetalhado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDetalhado> findUsuarioId(
            @PathVariable("id") final UUID uuid
    ) {
        var usuarioDomain = this.findUsuarioByIdUseCase.execute(uuid);
        return ResponseEntity.ok(this.usuarioApiMapper.fromUsuarioPresentation(usuarioDomain));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDetalhado> updateUsuario(
            @PathVariable("id") final UUID uuid,
            @RequestBody final UsuarioRequest usuarioRequest
    ) {
        var updateUsuarioDto = this.usuarioApiMapper.fromUsuarioApplicationUpdate(usuarioRequest);
        var usuarioDomain = this.updateUsuarioUseCase.execute(uuid, updateUsuarioDto);
        var usuarioResponseDetalhado = this.usuarioApiMapper.fromUsuarioPresentation(usuarioDomain);
        return ResponseEntity.ok(usuarioResponseDetalhado);
    }

    @GetMapping()
    public ResponseEntity<Page<UsuarioResponseDetalhado>> findUsuarioAll(
            @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = "nome",
                    direction = Sort.Direction.ASC
            ) final Pageable pageable
    ) {
        var usuarioPage = this.findUsuarioAllUseCase.execute(pageable);
        var usuarioResponseDetalhadoPage = this.usuarioApiMapper.fromUsuarioPresentatioPage(usuarioPage);
        return ResponseEntity.ok(usuarioResponseDetalhadoPage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuarioId(@PathVariable("id") UUID uuid) {
        this.deactivateUsuarioUseCase.execute(uuid);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("reactivate/{id}")
    public ResponseEntity<Void> reactivateUsuarioId(@PathVariable("id") UUID uuid) {
        this.reactivateUsuarioUseCase.execute(uuid);
        return ResponseEntity.noContent().build();
    }
}
