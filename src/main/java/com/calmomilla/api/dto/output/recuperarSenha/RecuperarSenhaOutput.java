package com.calmomilla.api.dto.output.recuperarSenha;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecuperarSenhaOutput {

    private String email;
    private String resposta;

}
