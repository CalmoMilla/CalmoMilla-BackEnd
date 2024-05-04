package com.calmomilla.api.controllers;


import com.calmomilla.api.dto.input.paciente.AtualizarPacienteInput;
import com.calmomilla.api.dto.input.psicologo.AtualizarPsicologoInput;
import com.calmomilla.api.dto.output.paciente.AtualizarPacienteOutput;
import com.calmomilla.api.dto.output.paciente.BuscarPacienteOutput;
import com.calmomilla.api.dto.output.psicologo.AtualizarPsicologoOutput;
import com.calmomilla.api.dto.output.psicologo.BuscarPsicologoOutput;
import com.calmomilla.domain.model.Paciente;
import com.calmomilla.domain.service.PacienteService;
import com.calmomilla.domain.service.PsicologoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public ResponseEntity<AtualizarPacienteOutput>atualizar(@RequestBody @Valid AtualizarPacienteInput pacienteInput) throws NoSuchMethodException {
        return pacienteService.atualizar(pacienteInput);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deletar(@PathVariable String id) throws NoSuchMethodException {
        return pacienteService.deletar(id);
    }

}
