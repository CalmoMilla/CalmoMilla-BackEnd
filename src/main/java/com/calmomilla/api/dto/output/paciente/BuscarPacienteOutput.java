package com.calmomilla.api.dto.output.paciente;

import com.calmomilla.domain.model.*;
import com.calmomilla.domain.utils.UserRole;
import com.calmomilla.domain.utils.enums.Focos;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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

    private Focos focos;

    private List<Desempenho> desempenhos;

    private DadosPessoais dadosPessoais;

    private List<Psicologo> psicologos;

    private List<Rotina> rotinas;


}
