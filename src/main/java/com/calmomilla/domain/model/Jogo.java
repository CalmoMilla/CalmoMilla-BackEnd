package com.calmomilla.domain.model;

import com.calmomilla.domain.utils.enums.Focos;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "jogos")
@AllArgsConstructor
@NoArgsConstructor
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotNull
    private List<Focos> focos = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Desempenho> desempenhos = new ArrayList<>();

    @NotBlank
    @Column(nullable = false)
    private String foto;

    private int avaliacao;

    @NotBlank
    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

}
