package com.calmomilla.api.dto.input.rotina;

import com.calmomilla.domain.model.Tarefa;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
public class AtualizarRotinaInput {

    @NotNull
    private String id;

    @NotNull
    private LocalDate diaRotina;

    @NotNull
    private boolean status;

    @NotNull
    private List<Tarefa> tarefas = new ArrayList<>();
}
