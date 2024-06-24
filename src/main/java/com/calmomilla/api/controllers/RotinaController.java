package com.calmomilla.api.controllers;


import com.calmomilla.api.dto.input.rotina.AtualizarRotinaInput;
import com.calmomilla.api.dto.input.rotina.CadastroRotinaInput;
import com.calmomilla.api.dto.output.rotina.AtualizarRotinaOutput;
import com.calmomilla.api.dto.output.rotina.BuscarRotinaOutput;
import com.calmomilla.api.dto.output.rotina.CadastroRotinaOutput;
import com.calmomilla.domain.service.RotinaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rotinas")
@AllArgsConstructor
public class RotinaController {

    RotinaService rotinaService;

    @PostMapping
    public ResponseEntity<CadastroRotinaOutput> cadastrar (@RequestBody @Valid CadastroRotinaInput cadastroRotinaInput) throws NoSuchMethodException {
        return rotinaService.criar(cadastroRotinaInput);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuscarRotinaOutput> buscarPorId (@PathVariable String id) throws NoSuchMethodException {
        return rotinaService.buscarPorId(id);
    }

    @GetMapping
    public List<BuscarRotinaOutput> buscarTodos () {
        return rotinaService.buscarTodos();
    }

        @GetMapping("/pacientes/{id}")
        public ResponseEntity<?>buscarRotina(@PathVariable String id) throws NoSuchMethodException {
        return rotinaService.buscarRotinaPorPaciente(id);
    }

    @PutMapping
    public ResponseEntity<AtualizarRotinaOutput> atualizar
            (@RequestBody @Valid AtualizarRotinaInput atualizarRotinaInput) throws NoSuchMethodException{
        return rotinaService.atualizar(atualizarRotinaInput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (@PathVariable String id) throws NoSuchMethodException {
        return rotinaService.deletar(id);
    }
}
