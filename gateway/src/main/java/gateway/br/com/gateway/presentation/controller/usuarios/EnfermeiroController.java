package gateway.br.com.gateway.presentation.controller.usuarios;

import gateway.br.com.gateway.application.dto.enfermeiro.AtualizaEnfermeiroDTO;
import gateway.br.com.gateway.application.dto.enfermeiro.CadastraEnfermeiroDTO;
import gateway.br.com.gateway.application.dto.enfermeiro.EnfermeiroResponseDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResponseDTO;
import gateway.br.com.gateway.application.input.enfermeiro.controller.EnfermeiroControllerInputPort;
import gateway.br.com.gateway.routes.RotasEnfermeiro;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(RotasEnfermeiro.ENFERMEIRO)
public class EnfermeiroController {

    private final EnfermeiroControllerInputPort enfermeiroControllerInputPort;

    public EnfermeiroController(EnfermeiroControllerInputPort enfermeiroControllerInputPort) {
        this.enfermeiroControllerInputPort = enfermeiroControllerInputPort;
    }

    @PostMapping
    public ResponseEntity<EnfermeiroResponseDTO> cadastrar(
            @RequestBody CadastraEnfermeiroDTO dto,
            UriComponentsBuilder uriBuilder
    ) {
        EnfermeiroResponseDTO enfermeiroCriado = enfermeiroControllerInputPort.cadastrar(dto);
        URI uri = uriBuilder
                .path(RotasEnfermeiro.ENFERMEIRO_COM_ID)
                .buildAndExpand(enfermeiroCriado.id())
                .toUri();
        return ResponseEntity.created(uri).body(enfermeiroCriado);
    }

    @GetMapping(RotasEnfermeiro.ID)
    public ResponseEntity<EnfermeiroResponseDTO> buscarPorId(@PathVariable UUID id) {
        EnfermeiroResponseDTO enfermeiro = enfermeiroControllerInputPort.encontrarPorId(id);
        return ResponseEntity.ok(enfermeiro);
    }

    @GetMapping(RotasEnfermeiro.TODOS)
    public ResponseEntity<PaginatedResponseDTO<EnfermeiroResponseDTO>> encontrarTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sort
    ) {
        PaginatedRequestDTO paginacao = new PaginatedRequestDTO(page, size, sort);
        PaginatedResponseDTO<EnfermeiroResponseDTO> resultado =
                enfermeiroControllerInputPort.listarTodos(paginacao);
        return ResponseEntity.ok(resultado);

    }

    @PutMapping(RotasEnfermeiro.ID)
    public ResponseEntity<EnfermeiroResponseDTO> atualizar(
            @PathVariable UUID id,
            @RequestBody AtualizaEnfermeiroDTO dto) {
        EnfermeiroResponseDTO enfermeiroAtualizado = enfermeiroControllerInputPort.atualizar(id, dto);
        return ResponseEntity.ok(enfermeiroAtualizado);
    }

    @PatchMapping(RotasEnfermeiro.ID_E_DESATIVAR)
    public ResponseEntity<Void> desativar(@PathVariable UUID id) {
        enfermeiroControllerInputPort.desativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(RotasEnfermeiro.ID_E_REATIVAR)
    public ResponseEntity<Void> reativar(@PathVariable UUID id) {
        enfermeiroControllerInputPort.reativar(id);
        return ResponseEntity.noContent().build();
    }

}
