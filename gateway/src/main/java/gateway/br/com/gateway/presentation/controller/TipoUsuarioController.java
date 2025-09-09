package gateway.br.com.gateway.presentation.controller;

import gateway.br.com.gateway.application.usecase.*;
import gateway.br.com.gateway.presentation.api.TipoUsuarioRequest;
import gateway.br.com.gateway.presentation.api.TipoUsuarioResponseDetalhado;
import gateway.br.com.gateway.presentation.mapper.TipoUsuarioApiMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/tipousuarios")
public class TipoUsuarioController {
    private final CreateTipoUsuarioUseCase createTipoUsuarioUseCase;
    private final TipoUsuarioApiMapper tipoUsuarioApiMapper;
    private final FindTipoUsuarioByIdUseCase findTipoUsuarioByIdUseCase;
    private final UpdateTipoUsuarioUseCase updateTipoUsuarioUseCase;
    private final FIndTipoUsuarioAllUseCase fIndTipoUsuarioAllUseCase;
    private final DeactivateTipoUsuarioByIdUseCase deactivateTipoUsuarioByIdUseCase;
    private final ReactivateTipoUsuarioByIdUseCase reactivateTipoUsuarioByIdUseCase;

    public TipoUsuarioController(
            final CreateTipoUsuarioUseCase createTipoUsuarioUseCase,
            final TipoUsuarioApiMapper tipoUsuarioApiMapper,
            final FindTipoUsuarioByIdUseCase findTipoUsuarioByIdUseCase,
            final UpdateTipoUsuarioUseCase updateTipoUsuarioUseCase,
            final FIndTipoUsuarioAllUseCase fIndTipoUsuarioAllUseCase,
            final DeactivateTipoUsuarioByIdUseCase deactivateTipoUsuarioByIdUseCase,
            final ReactivateTipoUsuarioByIdUseCase reactivateTipoUsuarioByIdUseCase
    ) {
        this.createTipoUsuarioUseCase = createTipoUsuarioUseCase;
        this.tipoUsuarioApiMapper = tipoUsuarioApiMapper;
        this.findTipoUsuarioByIdUseCase = findTipoUsuarioByIdUseCase;
        this.updateTipoUsuarioUseCase = updateTipoUsuarioUseCase;
        this.fIndTipoUsuarioAllUseCase = fIndTipoUsuarioAllUseCase;
        this.deactivateTipoUsuarioByIdUseCase = deactivateTipoUsuarioByIdUseCase;
        this.reactivateTipoUsuarioByIdUseCase = reactivateTipoUsuarioByIdUseCase;
    }

    @PostMapping
    public ResponseEntity<TipoUsuarioResponseDetalhado> createTipoUsuario(
            @RequestBody final TipoUsuarioRequest tipoUsuarioRequest,
            final UriComponentsBuilder uriComponentsBuilder
    ) {
        //Converte request -> DTO de aplicação
        var createTipoUsuarioDto = this.tipoUsuarioApiMapper.fromDtoApplication(tipoUsuarioRequest);
        //Passa para o caso de uso
        var tipoUsuarioDomain = this.createTipoUsuarioUseCase.execute(createTipoUsuarioDto);
        //Converte de dominio -> response (API)
        var tipoUsuarioResponseDetalhado = this.tipoUsuarioApiMapper.fromDtoPresentation(tipoUsuarioDomain);
        //Criar um Header Location
        var uri = uriComponentsBuilder.path("/tipousuarios/{id}").buildAndExpand(tipoUsuarioDomain.getId()).toUri();
        //retorna o header location e o DTO com os dados detalhados
        return ResponseEntity.created(uri).body(tipoUsuarioResponseDetalhado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuarioResponseDetalhado> findTipoUsuarioId(
            @PathVariable("id") final Long id
    ) {
        var tipoUsuario = this.findTipoUsuarioByIdUseCase.execute(id);
        var tipoUsuarioResponseDetalhado = this.tipoUsuarioApiMapper.fromDtoPresentation(tipoUsuario);
        return ResponseEntity.ok(tipoUsuarioResponseDetalhado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoUsuarioResponseDetalhado> updateTipoUsuario(
            @PathVariable("id") final Long id,
            @RequestBody final TipoUsuarioRequest tipoUsuarioRequest
    ) {
        var updateTipoUsuarioDto = this.tipoUsuarioApiMapper.fromDtoApplicationUpdate(tipoUsuarioRequest);
        var tipoUsuarioDomain = this.updateTipoUsuarioUseCase.execute(id, updateTipoUsuarioDto);
        return ResponseEntity.ok(this.tipoUsuarioApiMapper.fromDtoPresentation(tipoUsuarioDomain));
    }

    @GetMapping
    public ResponseEntity<Page<TipoUsuarioResponseDetalhado>> findTipoUsuarioAll(
            @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = "id",
                    direction = Sort.Direction.ASC
            ) final Pageable pageable
    ) {
        //GET http://localhost:8080/tipousuarios?page=1&size=10&sort=nome,asc
        var tipoUsuarioPage = this.fIndTipoUsuarioAllUseCase.execute(pageable);
        var tipoUsuarioResponseDetalhadoPage = this.tipoUsuarioApiMapper.fromDtoPagePresetation(tipoUsuarioPage);
        return ResponseEntity.ok(tipoUsuarioResponseDetalhadoPage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTipoUsuarioId(
            @PathVariable("id") final Long id
    ) {
        return ResponseEntity.ok(this.deactivateTipoUsuarioByIdUseCase.deactivate(id));
    }

    @PatchMapping("reactivate/{id}")
    public ResponseEntity<Boolean> reactivateTipoUsuarioId(
            @PathVariable("id") final Long id
    ) {
        return ResponseEntity.ok(this.reactivateTipoUsuarioByIdUseCase.execute(id));
    }
}
