package com.calmomilla.api.dto.output.paciente;

import com.calmomilla.domain.model.Psicologo;
import com.calmomilla.domain.utils.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BuscarPacienteEmailOutput {

    private String id;
    private String email;
    private String nome;
    private String foto;
    private UserRole role;
    private List<Psicologo> psicologos;

}
