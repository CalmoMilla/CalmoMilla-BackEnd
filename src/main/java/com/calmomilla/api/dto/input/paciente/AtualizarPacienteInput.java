package com.calmomilla.api.dto.input.paciente;

import com.calmomilla.domain.model.Endereco;
import com.calmomilla.domain.model.Rotina;
import com.calmomilla.domain.utils.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AtualizarPacienteInput {

    @NotBlank
    private String id;

    @NotBlank
    private String nome;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String genero;

    private LocalDate dataNasc;

    private Endereco endereco;

    private String telefone;

    @NotBlank
    private String senha;

    @NotBlank
    private String foto;

    @NotNull
    private UserRole role;

    private Rotina rotina;

}
