package com.calmomilla.api.dto.input.tarefa;


import com.calmomilla.domain.model.Dia;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastroTarefaInput {

    @NotNull
    private String descricaoTarefa;

    @NotNull
    private Dia dia;
}
