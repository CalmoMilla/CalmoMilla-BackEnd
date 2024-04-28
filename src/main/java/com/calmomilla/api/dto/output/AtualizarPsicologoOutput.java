package com.calmomilla.api.dto.output;

import com.calmomilla.domain.model.Endereco;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class AtualizarPsicologoOutput {

    @NotBlank
    private String nome;

    @NotBlank
    private String segundoNome;

    @Email
    private String email;

    @NotBlank
    private String senha;

    @NotBlank
    private String genero;

    private LocalDate dataNasc;

    private Endereco endereco;

    private String telefone;

    @NotBlank
    private String foto;

    @NotEmpty
    private List<String> especializacoes;

    @NotBlank
    private String numeroRegistro;

}
