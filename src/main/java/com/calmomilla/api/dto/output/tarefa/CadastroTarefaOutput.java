package com.calmomilla.api.dto.output.tarefa;


import com.calmomilla.domain.model.Dia;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastroTarefaOutput {

    private String id;
    private String descricaoTarefa;
    private Dia dia;

}
