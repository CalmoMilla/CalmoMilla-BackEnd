package com.calmomilla.api.dto.output.rotina;

import com.calmomilla.domain.model.Tarefa;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BuscarRotinaOutput {

    private String id;

    private LocalDate diaRotina;

    private boolean status;

    private List<Tarefa> tarefas = new ArrayList<>();
}
