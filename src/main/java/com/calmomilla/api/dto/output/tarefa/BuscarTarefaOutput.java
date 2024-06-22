package com.calmomilla.api.dto.output.tarefa;


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
public class BuscarTarefaOutput {

    private String id;

    private String titulo;

    private String link;

    private boolean status;


    private Focos focos;


}
