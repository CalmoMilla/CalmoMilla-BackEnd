package com.calmomilla.api.dto.input.tarefa;


import com.calmomilla.domain.model.Rotina;
import com.calmomilla.domain.utils.enums.Focos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AtualizarTarefaInput {

    @NotBlank
    private String id;

    @NotNull
    private String titulo;

    @NotBlank
    private String link;

    @NotNull
    private Focos focos;


    @NotNull
    private boolean status;

}
