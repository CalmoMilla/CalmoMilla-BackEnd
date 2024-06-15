package com.calmomilla.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Rotina {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    private LocalDate diaRotina;

    @NotNull
    private boolean status;

    @ManyToMany
    private List<Tarefa> tarefas = new ArrayList<>();

    @OneToOne
    private Paciente paciente;

}
