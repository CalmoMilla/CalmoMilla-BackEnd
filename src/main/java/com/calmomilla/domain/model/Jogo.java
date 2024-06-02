package com.calmomilla.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "jogos")
@AllArgsConstructor
@NoArgsConstructor
public class Jogo {

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false)
    private String foto;

    @Column(nullable = false)
    @NotNull
    private double pontuacao;

    @Column(nullable = false)
    @NotNull
    private int nivel;

    private int avaliacao;

    @Column(nullable = false)
    @NotBlank
    private String descricao;


    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String id;
    
    @ManyToMany
    private List<Paciente> jogador;

}
