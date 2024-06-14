package com.calmomilla.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Psicologo extends Usuario{

    @NotNull
    private List<String> especializacoes;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @NotBlank
    private String numeroRegistro;

    @ManyToMany
    private List<Paciente> pacientes;

}
