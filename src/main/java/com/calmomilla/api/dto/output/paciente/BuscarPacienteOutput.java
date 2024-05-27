package com.calmomilla.api.dto.output.paciente;

import com.calmomilla.domain.model.Endereco;
import com.calmomilla.domain.model.Rotina;
import com.calmomilla.domain.utils.UserRole;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
public class BuscarPacienteOutput {


    private String id;

    private String nome;

    private String email;

    private String senha;

    private String cpf;

    private UserRole role;

    private Endereco endereco;

    private String genero;

    private String telefone;

    private LocalDate dataNasc;

    private String foto;

    private Rotina rotina;

}
