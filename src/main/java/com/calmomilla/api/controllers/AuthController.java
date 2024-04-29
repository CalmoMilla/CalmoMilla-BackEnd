package com.calmomilla.api.controllers;

import com.calmomilla.api.dto.input.AuthDTO;
import com.calmomilla.api.dto.input.CadastrarDTO;
import com.calmomilla.api.dto.input.psicologo.CadastroPsicologoInput;
import com.calmomilla.api.dto.output.psicologo.CadastroPsicologoOutput;
import com.calmomilla.api.dto.output.LoginOutput;
import com.calmomilla.api.dto.output.UsuarioOutput;
import com.calmomilla.domain.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login/google")
    public ResponseEntity<?> loginGoogle(@RequestBody @Valid CadastrarDTO cadastrarDTO) {
        return authService.loginGoogle(cadastrarDTO);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginOutput> login(@RequestBody @Valid AuthDTO authDTO){

     return authService.login(authDTO);

    }

    @PostMapping("/cadastro-paciente")
    public ResponseEntity<UsuarioOutput> cadastrar(@RequestBody @Valid CadastrarDTO cadastrarDTO){

        return authService.cadastrar(cadastrarDTO);

    }

    @PostMapping("/cadastro-psicologo")
    public ResponseEntity<CadastroPsicologoOutput> cadastrar(@RequestBody @Valid CadastroPsicologoInput cadastroPsicologoInput){

        return authService.cadastrar(cadastroPsicologoInput);

    }

}
