package com.calmomilla.api.controllers;


import com.calmomilla.api.dto.input.paciente.AtualizarPacienteInput;
import com.calmomilla.api.dto.output.paciente.AtualizarPacienteOutput;
import com.calmomilla.api.dto.output.paciente.BuscarPacienteEmailOutput;
import com.calmomilla.api.dto.output.paciente.BuscarPacienteOutput;
import com.calmomilla.api.dto.output.rotina.BuscarRotinaOutput;
import com.calmomilla.domain.service.PacienteService;
import com.calmomilla.domain.service.RotinaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("pacientes")
@AllArgsConstructor
public class PacienteController {

  private final PacienteService pacienteService;

    @GetMapping
    public List<BuscarPacienteOutput> buscarTodos(){
        return pacienteService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuscarPacienteOutput> buscarPorId(@PathVariable String id) throws NoSuchMethodException {
        return pacienteService.buscarPorId(id);
    }

    @GetMapping("/rotina/{id}")
    public ResponseEntity<BuscarRotinaOutput>buscarRotina(@PathVariable String id) throws NoSuchMethodException {
        return pacienteService.buscarRotina(id);
    }

    @GetMapping("/eu")
    public ResponseEntity<BuscarPacienteEmailOutput> buscarPorEmail(Principal principal) throws NoSuchMethodException {
        return pacienteService.buscarInfo(principal);
    }


    @PutMapping
    public ResponseEntity<AtualizarPacienteOutput>atualizar(@RequestBody @Valid AtualizarPacienteInput pacienteInput) throws NoSuchMethodException {
        return pacienteService.atualizar(pacienteInput);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deletar(@PathVariable String id) throws NoSuchMethodException {
        return pacienteService.deletar(id);
    }

}
