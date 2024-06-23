package com.calmomilla.api.dto.output.paciente;

import com.calmomilla.domain.model.DadosPessoais;
import com.calmomilla.domain.model.Desempenho;
import com.calmomilla.domain.model.Psicologo;
import com.calmomilla.domain.model.Rotina;
import com.calmomilla.domain.service.DadosPessoaisService;
import com.calmomilla.domain.utils.UserRole;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Data
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

    private List<Desempenho> desempenhos;

    private List<Psicologo> psicologos;

    private List<Rotina> rotinas;

}
