package com.calmomilla.api.dto.output.paciente;

import com.calmomilla.domain.model.Rotina;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastroPacienteOutput {

    private String id;

    private String nome;

    private String email;

    private String senha;

    private String foto;

    private Rotina rotina;

}
