package com.calmomilla.domain.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Psicologo extends Usuario{
    @NotNull
    private List<String> especializacoes;
    @NotBlank
    private String numeroRegistro;



}
