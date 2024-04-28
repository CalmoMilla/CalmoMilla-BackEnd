package com.calmomilla.api.dto.input;

import com.calmomilla.domain.model.Tarefa;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CadastroDiaInput {
    @NotNull
    private LocalDateTime dia;

    @NotNull
    private List<Tarefa> tarefas;

}
