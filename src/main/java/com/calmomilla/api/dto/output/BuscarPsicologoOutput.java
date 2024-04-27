package com.calmomilla.api.dto.output;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BuscarPsicologoOutput {

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


}
