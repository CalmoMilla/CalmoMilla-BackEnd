package com.calmomilla.api.dto.input.recuperarSenhaRedefinir;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RecuperarSenhaRedefinirInput {

    @NotEmpty
    private String email;
    @NotEmpty
    private String senha;
}
