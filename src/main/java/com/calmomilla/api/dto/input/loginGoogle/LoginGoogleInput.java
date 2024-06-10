package com.calmomilla.api.dto.input.loginGoogle;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginGoogleInput {

    @NotBlank
    private String email;

    private String senha;

}
