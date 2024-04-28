package com.calmomilla.api.dto.input;

import com.calmomilla.domain.model.Endereco;
import com.calmomilla.domain.utils.UserRole;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private String genero;

//    private LocalDateTime dataNasc;
//
//    @OneToOne
//    private Endereco endereco;
    @NotBlank
    private String senha;
    @NotBlank
    private String foto;
    @NotNull
    private UserRole role;


}
