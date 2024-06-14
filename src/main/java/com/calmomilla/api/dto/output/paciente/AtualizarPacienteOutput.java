package com.calmomilla.api.dto.output.paciente;

import com.calmomilla.domain.model.DadosPessoais;
import com.calmomilla.domain.model.Rotina;
import com.calmomilla.domain.service.DadosPessoaisService;
import com.calmomilla.domain.utils.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AtualizarPacienteOutput {

    private String id;

    private String nome;

    private String email;

    private UserRole role;

    private String genero;

    private LocalDate dataNasc;

    private String foto;

    private Rotina rotina;

    private DadosPessoais dadosPessoais;

}
