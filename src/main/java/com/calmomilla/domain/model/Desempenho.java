package com.calmomilla.domain.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Desempenho {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    @NotNull
    private int nivel;

    @Column(nullable = false)
    @NotNull
    private double pontuacao;

    @ManyToOne
    private Jogo jogos;

    @ManyToOne
    @JsonIgnore
    private Paciente usuario;


    @Override
    public String toString() {
        return "Desempenho{" +
                "id='" + id + '\'' +
                ", nivel=" + nivel +
                ", pontuacao=" + pontuacao +
                ", jogos=" + (jogos != null ? jogos.getId() : "null") +
                '}';
    }

}



