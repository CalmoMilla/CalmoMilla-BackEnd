package com.calmomilla.api.dto.input;

import com.calmomilla.domain.utils.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AtualizarPsicologoInput {

    @NotBlank
    private String id;

    @NotBlank
    private String nome;

    @NotBlank
    private String segundoNome;

    @Email
    private String email;

    @NotBlank
    private String senha;

    @NotBlank
    private String foto;

    @NotEmpty
    private List<String> especializacoes;

    @NotBlank
    private String numeroRegistro;

    @NotNull
    private UserRole role;

}
