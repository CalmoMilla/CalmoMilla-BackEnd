package com.calmomilla.api.controllers;

import com.calmomilla.api.dto.input.dadosPessoais.AtualizarDadosPessoaisinput;
import com.calmomilla.api.dto.input.dadosPessoais.CadastroDadosPessoaisInput;
import com.calmomilla.api.dto.output.dadosPessoais.AtualizarDadosPessoaisOutput;
import com.calmomilla.api.dto.output.dadosPessoais.BuscarDadosPessoaisOutput;
import com.calmomilla.api.dto.output.dadosPessoais.CadastroDadosPessoaisOutput;
import com.calmomilla.domain.service.DadosPessoaisService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/dadosPessoais")
public class DadosPessoaisController {

    private final DadosPessoaisService dadosPessoaisService;

    @GetMapping("/{id}")
    public ResponseEntity<BuscarDadosPessoaisOutput> buscarPorId(@PathVariable String id){
        return dadosPessoaisService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<CadastroDadosPessoaisOutput> cadastrar(@RequestBody @Valid CadastroDadosPessoaisInput dadosPessoaisInput) throws NoSuchMethodException {
            return dadosPessoaisService.cadastrar(dadosPessoaisInput);
    }

    @PutMapping
    public ResponseEntity<AtualizarDadosPessoaisOutput> atualizar(@RequestBody AtualizarDadosPessoaisinput dadosPessoaisinput) {
        return dadosPessoaisService.atualizar(dadosPessoaisinput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable String id) throws NoSuchMethodException {

        return dadosPessoaisService.deletar(id);

    }

}
