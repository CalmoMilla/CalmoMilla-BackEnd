package com.calmomilla.api.dto.output.tarefa;


import com.calmomilla.domain.model.Dia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastroTarefaOutput {

    @NotNull
    private String id;

    @NotNull
    private String descricaoTarefa;

    @NotNull
    private Dia dia;

}
