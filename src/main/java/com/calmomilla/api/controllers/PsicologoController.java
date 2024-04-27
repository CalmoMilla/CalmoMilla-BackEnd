package com.calmomilla.api.controllers;


import com.calmomilla.api.dto.input.AtualizarPsicologoInput;
import com.calmomilla.api.dto.output.AtualizarPsicologoOutput;
import com.calmomilla.domain.model.Psicologo;
import com.calmomilla.domain.service.PsicologoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("psicologo")
@AllArgsConstructor
public class PsicologoController {

    PsicologoService psicologoService;
    @GetMapping
    public List<Psicologo> buscarTodos(){
        return psicologoService.buscarTodos();
    }

    @PutMapping
    public ResponseEntity<AtualizarPsicologoOutput>atualizar(@RequestBody @Valid AtualizarPsicologoInput psicologoInput){

        return psicologoService.atualizar(psicologoInput);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deletar(@PathVariable String id){
        return psicologoService.deletar(id);
    }

}
