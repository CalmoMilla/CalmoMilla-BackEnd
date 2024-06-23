package com.calmomilla.api.dto.input.psicologo;

import com.calmomilla.domain.model.Endereco;
import com.calmomilla.domain.utils.UserRole;
import com.calmomilla.domain.utils.enums.Especializacoes;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CadastroPsicologoInput {

    @NotBlank
    private String nome;

    @Email
    private String email;

    @NotBlank
    private String senha;

    @NotBlank
    private String genero;

    private LocalDate dataNasc;

    private String cpf;

    private Endereco endereco;

    private String telefone;

    @NotBlank
    private String foto;

    private List<Especializacoes> especializacoes;

    @NotBlank
    private String numeroRegistro;

    @NotNull
    private UserRole role;


}
