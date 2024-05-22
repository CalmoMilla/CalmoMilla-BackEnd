package com.calmomilla.api.dto.input.tarefa;


import com.calmomilla.domain.model.Rotina;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CadastroTarefaInput {

    @NotNull
    private String descricaoTarefa;

    private List<Rotina> rotinas = new ArrayList<>();

}
