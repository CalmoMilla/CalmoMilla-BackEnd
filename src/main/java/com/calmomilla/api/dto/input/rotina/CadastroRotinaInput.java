package com.calmomilla.api.dto.input.rotina;

import com.calmomilla.domain.model.Paciente;
import com.calmomilla.domain.model.Tarefa;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CadastroRotinaInput {

    @NotNull
    private LocalDate diaRotina;

    @NotNull
    private boolean status;

    @NotNull
    private List<Tarefa> tarefas = new ArrayList<>();

    private Paciente paciente;

}
