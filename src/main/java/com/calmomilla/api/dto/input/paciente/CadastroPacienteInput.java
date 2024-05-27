package com.calmomilla.api.dto.input.paciente;

import com.calmomilla.domain.model.Endereco;
import com.calmomilla.domain.utils.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;


@Getter
@Setter
public class CadastroPacienteInput {

    @NotBlank
    private String nome;

    @Email
    @NotBlank
    private String email;

    private String genero;

    private String cpf;

    private LocalDate dataNasc;

    private Endereco endereco;

    private String telefone;

    @NotBlank
    private String senha;

    @NotNull
    private UserRole role;


}
