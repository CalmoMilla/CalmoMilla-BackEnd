package com.calmomilla.api.controllers;


import com.calmomilla.api.dto.input.CadastroDiaInput;
import com.calmomilla.api.dto.output.CadastroDiaOutput;
import com.calmomilla.domain.service.DiaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dia")
@AllArgsConstructor
public class DiaController {

    DiaService diaService;

    @PostMapping
    public ResponseEntity<CadastroDiaOutput> cadastrar(@RequestBody @Valid CadastroDiaInput diaInput) {
        return diaService.cadastrarDia(diaInput);
    }
}
