package com.calmomilla.domain.model;

import com.calmomilla.domain.utils.enums.Focos;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "jogos")
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

    private String link;
    @NotBlank
    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

}
