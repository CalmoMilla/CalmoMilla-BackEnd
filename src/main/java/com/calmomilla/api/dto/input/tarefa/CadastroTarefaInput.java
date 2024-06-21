package com.calmomilla.api.dto.input.tarefa;


import com.calmomilla.domain.model.Rotina;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CadastroTarefaInput {

    @NotNull
    private String titulo;

    @NotBlank
    private String link;

    @NotNull
    private boolean status;

    private List<Rotina> rotinas = new ArrayList<>();

}
