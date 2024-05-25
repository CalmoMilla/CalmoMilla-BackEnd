package com.calmomilla.api.dto.output.psicologo;

import com.calmomilla.domain.model.Endereco;
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

    private String nome;

    private String email;

    private String genero;

    private LocalDate dataNasc;

    private Endereco endereco;

    private String telefone;

    private String foto;

    private List<String> especializacoes;

    private String numeroRegistro;

}
