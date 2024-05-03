package com.calmomilla.api.controllers;


import com.calmomilla.api.dto.input.dia.AtualizarDiaInput;
import com.calmomilla.api.dto.input.dia.CadastroDiaInput;
import com.calmomilla.api.dto.output.dia.AtualizarDiaOutput;
import com.calmomilla.api.dto.output.dia.BuscarDiaOutput;
import com.calmomilla.api.dto.output.dia.CadastroDiaOutput;
import com.calmomilla.domain.service.DiaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dia")
@AllArgsConstructor
public class DiaController {

    DiaService diaService;

    @PostMapping
    public ResponseEntity<CadastroDiaOutput> cadastrar(@RequestBody @Valid CadastroDiaInput diaInput) {
        return diaService.cadastrarDia(diaInput);
    }

    @GetMapping("/{id}")
    public ResponseEntity <BuscarDiaOutput> buscarPorId(@PathVariable String id ) throws NoSuchMethodException {
        return diaService.buscarPorId(id);
    }

    @GetMapping
    public List<BuscarDiaOutput> buscarTodos () {
        return diaService.buscarTodos();
    }

    @PutMapping
    public ResponseEntity<AtualizarDiaOutput> atualizar
            (@RequestBody @Valid AtualizarDiaInput atualizarDiaInput) throws NoSuchMethodException {
        return diaService.atualizar(atualizarDiaInput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (@PathVariable String id) throws NoSuchMethodException {
        return diaService.deletar(id);
    }
}
