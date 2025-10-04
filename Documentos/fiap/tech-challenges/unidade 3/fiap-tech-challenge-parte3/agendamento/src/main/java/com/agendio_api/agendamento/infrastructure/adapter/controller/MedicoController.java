package com.agendio_api.agendamento.infrastructure.adapter.controller;

import com.agendio_api.agendamento.api.routes.RotasMedico;
import com.agendio_api.agendamento.application.port.dto.medico.*;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResponseDTO;
import com.agendio_api.agendamento.application.port.input.medico.controller.MedicoControllerInputPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(RotasMedico.MEDICO)
public class MedicoController {
    private final MedicoControllerInputPort medicoControllerInputPort;

    public MedicoController(MedicoControllerInputPort medicoControllerInputPort) {
        this.medicoControllerInputPort = medicoControllerInputPort;
    }

    @PostMapping
    public ResponseEntity<MedicoResponseDTO> cadastrar(
            @RequestBody CadastraMedicoDTO dto,
            UriComponentsBuilder uriBuilder
    ) {
        MedicoResponseDTO medicoCriado = medicoControllerInputPort.cadastrar(dto);
        URI uri = uriBuilder
                .path(RotasMedico.MEDICO_COM_ID)
                .buildAndExpand(medicoCriado.id())
                .toUri();
        return ResponseEntity.created(uri).body(medicoCriado);
    }

    @GetMapping(RotasMedico.ID)
    public ResponseEntity<MedicoResponseDTO> buscarPorId(
            @PathVariable UUID id
    ) {
        MedicoResponseDTO medicoResponseDTO = medicoControllerInputPort.encontrarPorId(id);
        return ResponseEntity.ok(medicoResponseDTO);
    }

    @GetMapping(RotasMedico.TODOS)
    public ResponseEntity<PaginatedResponseDTO<MedicoResponseDTO>> buscarTodos(
            @ModelAttribute MedicoRequestDTO filtro,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sort) {

        PaginatedRequestDTO paginacao = new PaginatedRequestDTO(page, size, sort);
        PaginatedResponseDTO<MedicoResponseDTO> resultado =
                medicoControllerInputPort.listarTodos(paginacao);
        return ResponseEntity.ok(resultado);

    }

    @PutMapping(RotasMedico.ID)
    public ResponseEntity<MedicoResponseDTO> atualizar(
            @PathVariable UUID id,
            @RequestBody AtualizaMedicoDTO dto) {
        MedicoResponseDTO medicoAtualizado = medicoControllerInputPort.atualizar(id, dto);
        return ResponseEntity.ok(medicoAtualizado);
    }

    @PatchMapping(RotasMedico.ID_E_DESATIVAR)
    public ResponseEntity<Void> desativar(@PathVariable UUID id) {
        medicoControllerInputPort.desativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(RotasMedico.ID_E_REATIVAR)
    public ResponseEntity<Void> reativar(@PathVariable UUID id) {
        medicoControllerInputPort.reativar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(RotasMedico.CRM)
    public ResponseEntity<MedicoResponseDTO> buscarPorCrm(@PathVariable String crm) {
        MedicoResponseDTO medicoResponseDTO = medicoControllerInputPort.encontrarPorCRM(crm);
        return ResponseEntity.ok(medicoResponseDTO);
    }

    @GetMapping(RotasMedico.ESPECIALIDADE)
    public ResponseEntity<PaginatedResponseDTO<MedicoResponseDTO>> buscarPorEspecialidade(
            @PathVariable String especialidade,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sort) {

        PaginatedRequestDTO paginacao = new PaginatedRequestDTO(page, size, sort);
        EncontraMedicosPorEspecialidadeRequestDTO request = new EncontraMedicosPorEspecialidadeRequestDTO(especialidade, paginacao);
        PaginatedResponseDTO<MedicoResponseDTO> resultado =
                medicoControllerInputPort.listarPorEspecialidade(request);
        return ResponseEntity.ok(resultado);

    }


}
