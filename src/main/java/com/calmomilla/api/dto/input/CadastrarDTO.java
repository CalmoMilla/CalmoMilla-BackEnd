package com.calmomilla.api.dto.input;

import com.calmomilla.domain.utils.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastrarDTO {

    @NotBlank
    private String nome;
    private String segundoNome;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String senha;
    @NotBlank
    private String foto;
    @NotNull
    private UserRole role;


}
