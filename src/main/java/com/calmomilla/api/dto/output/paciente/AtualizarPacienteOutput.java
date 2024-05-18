package com.calmomilla.api.dto.output.paciente;

import com.calmomilla.domain.model.Rotina;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarPacienteOutput {

    private String id;

    private String nome;

    private String email;

    private String cpf;

    private String senha;

    private String foto;

    private Rotina rotina;

}
