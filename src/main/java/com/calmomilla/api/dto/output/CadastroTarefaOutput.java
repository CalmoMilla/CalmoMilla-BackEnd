package com.calmomilla.api.dto.output;


import com.calmomilla.domain.model.Dia;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CadastroTarefaOutput {

    private String id;
    private String descricaoTarefa;
    private Dia dia;

}
