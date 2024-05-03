package com.calmomilla.api.dto.input.dia;

import com.calmomilla.domain.model.Tarefa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class AtualizarDiaInput {
    @NotBlank
    private String id;

    @NotNull
    private LocalDateTime dia;

    private List<Tarefa> tarefas;
}
