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
public class CadastroTarefaInput {

    @NotNull
    private String titulo;

    @NotNull
    private Focos focos;

    @NotBlank
    private String link;

    @NotNull
    private boolean status;


}
