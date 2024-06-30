package com.calmomilla.domain.model;

import com.calmomilla.domain.utils.enums.CategoriaRelaxamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Relaxamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    private String titulo;

    @NotNull
    private CategoriaRelaxamento categoria;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @NotBlank
    private String link;


}
