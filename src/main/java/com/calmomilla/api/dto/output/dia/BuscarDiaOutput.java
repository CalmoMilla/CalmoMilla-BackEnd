package com.calmomilla.api.dto.output.dia;


import com.calmomilla.domain.model.Tarefa;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BuscarDiaOutput {
    @NotNull
    private String id;

    @NotNull
    private LocalDateTime dia;

    @NotNull
    private List<Tarefa> tarefas;

}
