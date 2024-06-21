package com.calmomilla.api.dto.output.tarefa;


import com.calmomilla.domain.model.Rotina;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CadastroTarefaOutput {

    private String id;

    private String titulo;

    private String link;

    private boolean status;

    private List<Rotina> rotinas = new ArrayList<>();
}
