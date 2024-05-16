package com.calmomilla.api.controllers;

import com.calmomilla.api.dto.input.verificacao.VerificacaoDTO;
import com.calmomilla.domain.service.VerificacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("verify")
@AllArgsConstructor
public class VerificacaoController {

    private final VerificacaoService verificacaoService;

    @PostMapping("/cpf")
    public ResponseEntity<?> verificarCpf(@RequestBody @Valid VerificacaoDTO verificacaoDTO) throws ParseException {
       return verificacaoService.enviarDados(verificacaoDTO);
    }

}
