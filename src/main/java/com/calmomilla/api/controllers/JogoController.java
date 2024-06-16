package com.calmomilla.api.controllers;

import com.calmomilla.api.dto.output.jogoOutput.JogoOutput;
import com.calmomilla.domain.service.JogoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jogos")
@AllArgsConstructor
public class JogoController {

    private final JogoService jogoService;

    @GetMapping
    public List<JogoOutput> buscarJogos(){
        return jogoService.buscarTodos();
    }



}
