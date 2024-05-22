package com.calmomilla.api.dto.output.tarefa;


import com.calmomilla.domain.model.Rotina;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AtualizarTarefaOutput {

    private String descricaoTarefa;

    private List<Rotina> rotinas = new ArrayList<>();

}
