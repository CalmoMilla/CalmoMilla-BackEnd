package com.calmomilla.api.dto.input.paciente;

import com.calmomilla.domain.model.*;
import com.calmomilla.domain.utils.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Data
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

    private String cpf;

    private Endereco endereco;

    private String telefone;

    @NotBlank
    private String senha;

    @NotBlank
    private String foto;

    private LocalDate ultimoLogin;

    private boolean logouHoje;


    @NotNull
    private UserRole role;

    private Rotina rotina;

    private DadosPessoais dadosPessoais;

    private List<Desempenho> desempenhos;

    private List<Psicologo> psicologos;

}
