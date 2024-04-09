package com.calmomilla.api.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class AuthDTO {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String senha;

}
