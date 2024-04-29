package com.calmomilla.api.dto.output.dia;

import com.calmomilla.domain.model.Tarefa;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CadastroDiaOutput {

    private String id;
    private LocalDateTime dia;
    private List<Tarefa> tarefas;
}
