package com.agendio_api.agendamento.infrastructure.adapter.controller;

import com.agendio_api.agendamento.api.routes.RotasConsulta;
import com.agendio_api.agendamento.application.port.dto.consulta.AgendaConsultaDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.CancelarConsultaRequestDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaFiltroRequestDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResponseDTO;
import com.agendio_api.agendamento.application.port.dto.usuario.UsuarioIdFiltroPaginadoRequestDTO;
import com.agendio_api.agendamento.application.port.input.consulta.controller.ConsultaControllerInputPort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping(RotasConsulta.CONSULTA)
public class ConsultaController {

    private final ConsultaControllerInputPort consultaControllerInputPort;

    public ConsultaController(ConsultaControllerInputPort consultaControllerInputPort) {
        this.consultaControllerInputPort = consultaControllerInputPort;
    }

    @PostMapping
    public ResponseEntity<ConsultaResponseDTO> agendarConsulta(
            @RequestBody AgendaConsultaDTO dto,
            UriComponentsBuilder uriBuilder
    ) {
        ConsultaResponseDTO consultaCriada = consultaControllerInputPort.agendar(dto);
        System.out.println(consultaCriada.id());
        URI uri = uriBuilder.path(RotasConsulta.CONSULTA_COM_ID).buildAndExpand(consultaCriada.id()).toUri();
        return ResponseEntity.created(uri).body(consultaCriada);
    }

    @GetMapping(RotasConsulta.ID)
    public ResponseEntity<ConsultaResponseDTO> buscarConsulta(@PathVariable UUID id) {
        ConsultaResponseDTO consulta = consultaControllerInputPort.encontrarPorId(id);
        return ResponseEntity.ok(consulta);
    }

    @PatchMapping(RotasConsulta.CONSULTA_COM_ID_CANCELAR)
    public ResponseEntity<Void> cancelarConsulta(@PathVariable UUID id,
                                                 @RequestBody CancelarConsultaRequestDTO dto) {
        consultaControllerInputPort.cancelar(id, dto.motivoCancelamento());
        return ResponseEntity.noContent().build();
    }

    @GetMapping(RotasConsulta.TODAS)
    public ResponseEntity<PaginatedResponseDTO<ConsultaResponseDTO>> buscarTodasConsultas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "horarioSolicitado") String sort
    ) {
        PaginatedRequestDTO paginacao = new PaginatedRequestDTO(page, size, sort);
        PaginatedResponseDTO<ConsultaResponseDTO> resultado =
                consultaControllerInputPort.listarTodas(paginacao);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping(RotasConsulta.POR_PACIENTE)
    public ResponseEntity<PaginatedResponseDTO<ConsultaResponseDTO>> buscarConsultasPorPaciente(
            @PathVariable UUID pacienteId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "horarioSolicitado") String sort) {

        PaginatedRequestDTO paginacao = new PaginatedRequestDTO(page, size, sort);
        UsuarioIdFiltroPaginadoRequestDTO filtroPaginado = new UsuarioIdFiltroPaginadoRequestDTO(pacienteId, paginacao);
        PaginatedResponseDTO<ConsultaResponseDTO> resultado =
                consultaControllerInputPort.listarPorPaciente(filtroPaginado);
        return ResponseEntity.ok(resultado);

    }

    @GetMapping(RotasConsulta.POR_ENFERMEIRO)
    public ResponseEntity<PaginatedResponseDTO<ConsultaResponseDTO>> buscarConsultasPorEnfermeiro(
            @PathVariable UUID enfermeiroId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "horarioSolicitado") String sort) {

        PaginatedRequestDTO paginacao = new PaginatedRequestDTO(page, size, sort);
        UsuarioIdFiltroPaginadoRequestDTO filtroPaginado = new UsuarioIdFiltroPaginadoRequestDTO(enfermeiroId, paginacao);
        PaginatedResponseDTO<ConsultaResponseDTO> resultado =
                consultaControllerInputPort.listarPorEnfermeiro(filtroPaginado);
        return ResponseEntity.ok(resultado);

    }

    @GetMapping(RotasConsulta.POR_MEDICO_E_PERIODO)
    public ResponseEntity<PaginatedResponseDTO<ConsultaResponseDTO>> buscarConsultasPorMedicoEPeriodo(
            @PathVariable UUID medicoId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "horarioSolicitado") String sort) {

        PaginatedRequestDTO paginacao = new PaginatedRequestDTO(page, size, sort);
        ConsultaFiltroRequestDTO filtro = new ConsultaFiltroRequestDTO(medicoId, inicio, fim);
        PaginatedResponseDTO<ConsultaResponseDTO> resultado =
                consultaControllerInputPort.listarPorPeriodo(filtro, paginacao);
        return ResponseEntity.ok(resultado);

    }


    @GetMapping(RotasConsulta.POR_MEDICO)
    public ResponseEntity<PaginatedResponseDTO<ConsultaResponseDTO>> buscarConsultasPorMedico(
            @PathVariable UUID medicoId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "horarioSolicitado") String sort) {

        PaginatedRequestDTO paginacao = new PaginatedRequestDTO(page, size, sort);
        UsuarioIdFiltroPaginadoRequestDTO filtroPaginado = new UsuarioIdFiltroPaginadoRequestDTO(medicoId, paginacao);
        PaginatedResponseDTO<ConsultaResponseDTO> resultado =
                consultaControllerInputPort.listarPorMedico(filtroPaginado);
        return ResponseEntity.ok(resultado);

    }
}
