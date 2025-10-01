package gateway.br.com.gateway.presentation.controller.usuarios;

import gateway.br.com.gateway.application.dto.paciente.*;
import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResponseDTO;
import gateway.br.com.gateway.application.input.paciente.controller.PacienteControllerInputPort;
import gateway.br.com.gateway.routes.RotasPaciente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(RotasPaciente.PACIENTE)
public class PacienteController {

    private final PacienteControllerInputPort pacienteControllerInputPort;

    public PacienteController(PacienteControllerInputPort pacienteControllerInputPort) {
        this.pacienteControllerInputPort = pacienteControllerInputPort;
    }

    @PostMapping
    public ResponseEntity<PacienteResponseDTO> cadastrar(
            @RequestBody CadastraPacienteDTO dto,
            UriComponentsBuilder uriBuilder
    ) {
        PacienteResponseDTO pacienteCriado = pacienteControllerInputPort.cadastrar(dto);
        URI uri = uriBuilder
                .path(RotasPaciente.PACIENTE_COM_ID)
                .buildAndExpand(pacienteCriado.id())
                .toUri();
        return ResponseEntity.created(uri).body(pacienteCriado);
    }

    @GetMapping(RotasPaciente.ID)
    public ResponseEntity<PacienteResponseDTO> encontrarPorId(@PathVariable UUID id) {
        PacienteResponseDTO paciente = pacienteControllerInputPort.encontrarPorId(id);
        return ResponseEntity.ok(paciente);
    }

    @GetMapping(RotasPaciente.TODOS)
    public ResponseEntity<PaginatedResponseDTO<PacienteResponseDTO>> encontrarTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sort) {

        PaginatedRequestDTO paginacao = new PaginatedRequestDTO(page, size, sort);
        PaginatedResponseDTO<PacienteResponseDTO> resultado =
                pacienteControllerInputPort. encontrarTodos(paginacao);
        return ResponseEntity.ok(resultado);

    }

    @PutMapping(RotasPaciente.ID)
    public ResponseEntity<PacienteResponseDTO> atualizar(
            @PathVariable UUID id,
            @RequestBody AtualizaPacienteDTO dto) {
        PacienteResponseDTO pacienteAtualizado = pacienteControllerInputPort.atualizar(id, dto);
        return ResponseEntity.ok(pacienteAtualizado);
    }

    @PatchMapping(RotasPaciente.ID_E_DESATIVAR)
    public ResponseEntity<Void> desativar(@PathVariable UUID id) {
        pacienteControllerInputPort.desativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(RotasPaciente.ID_E_REATIVAR)
    public ResponseEntity<Void> reativar(@PathVariable UUID id) {
        pacienteControllerInputPort.reativar(id);
        return ResponseEntity.noContent().build();
    }
}
