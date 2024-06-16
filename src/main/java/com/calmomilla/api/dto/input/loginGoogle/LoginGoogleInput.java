package com.calmomilla.api.dto.input.loginGoogle;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginGoogleInput {

    @Email
    private String email;

}
