package com.calmomilla.domain.model;

import com.calmomilla.domain.utils.Emocoes;
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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emocao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    private String descricao;
    @NotNull
    private List<Emocoes> emocoes = new ArrayList<>();
    @NotNull
    private LocalDate dataRegistro;

    @ManyToOne
    @JsonIgnore
    private Paciente paciente;
}
