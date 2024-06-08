package com.calmomilla.api.controllers;


import com.calmomilla.api.dto.input.psicologo.AtualizarPsicologoInput;
import com.calmomilla.api.dto.output.paciente.BuscarPacienteEmailOutput;
import com.calmomilla.api.dto.output.psicologo.AtualizarPsicologoOutput;
import com.calmomilla.api.dto.output.psicologo.BuscarPsicologoEmailOutput;
import com.calmomilla.api.dto.output.psicologo.BuscarPsicologoOutput;
import com.calmomilla.domain.service.PsicologoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("psicologos")
@AllArgsConstructor
public class PsicologoController {

    PsicologoService psicologoService;
    @GetMapping
    public List<BuscarPsicologoOutput> buscarTodos(){
        return psicologoService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuscarPsicologoOutput> buscarPorId(@PathVariable String id) throws NoSuchMethodException {
        return psicologoService.buscarPorId(id);
    }

    @GetMapping("/eu")
    public ResponseEntity<BuscarPsicologoEmailOutput> buscarPorEmail(Principal principal) throws NoSuchMethodException {
        return psicologoService.buscarInfo(principal);
    }

    @PutMapping
    public ResponseEntity<AtualizarPsicologoOutput>atualizar(@RequestBody @Valid AtualizarPsicologoInput psicologoInput) throws NoSuchMethodException {

        return psicologoService.atualizar(psicologoInput);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deletar(@PathVariable String id) throws NoSuchMethodException {
        return psicologoService.deletar(id);
    }

}
