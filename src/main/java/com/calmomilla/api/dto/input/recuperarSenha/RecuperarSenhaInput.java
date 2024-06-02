package com.calmomilla.api.dto.input.recuperarSenha;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RecuperarSenhaInput {

    @NotEmpty
    private String email
;
}
