package com.calmomilla.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToMany
    @JsonIgnore
    private List<Paciente> pacientes;

    public Rotina(Rotina rotinaPadrao) {
        this.diaRotina = rotinaPadrao.getDiaRotina();
        this.status = rotinaPadrao.isStatus();
        this.tarefas = rotinaPadrao.getTarefas();
        this.pacientes = rotinaPadrao.getPacientes();
    }
}
