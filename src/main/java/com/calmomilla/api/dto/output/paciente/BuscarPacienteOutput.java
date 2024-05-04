package com.calmomilla.api.dto.output.paciente;

import com.calmomilla.domain.model.Rotina;
import com.calmomilla.domain.utils.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuscarPacienteOutput {

    private String id;


    private String nome;


    private String segundoNome;

    private String email;

    private UserRole role;

    private String senha;

    private String foto;

    private Rotina rotina;

}
