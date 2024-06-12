package com.calmomilla.api.controllers;

import com.calmomilla.api.dto.input.emocao.CadastrarEmocaoInput;
import com.calmomilla.api.dto.output.CadastrarEmocaoOutput;
import com.calmomilla.api.dto.output.emocao.BuscarEmocaoOutput;
import com.calmomilla.domain.service.EmocaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emocoes")
@AllArgsConstructor
public class EmocaoController {

    private final EmocaoService emocaoService;

    @GetMapping
    public ResponseEntity<List<BuscarEmocaoOutput>>buscar(){
        return emocaoService.buscarEmocoes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuscarEmocaoOutput>buscarPorId(@PathVariable String id) throws NoSuchMethodException {
        return emocaoService.buscarEmocaoPorId(id);
    }

    @GetMapping("/pacientes/{id}")
    public ResponseEntity<List<BuscarEmocaoOutput>>buscarPorIdPaciente(@PathVariable String id) throws NoSuchMethodException {
        return emocaoService.buscarEmocaoPorIdPaciente(id);
    }

    @PostMapping
    public ResponseEntity<CadastrarEmocaoOutput>cadastrar(@RequestBody CadastrarEmocaoInput emocaoInput){

        return emocaoService.cadastrar(emocaoInput);

    }

}
