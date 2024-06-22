package com.calmomilla.domain.model;

import com.calmomilla.domain.utils.enums.Focos;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class  Paciente extends Usuario{

    @OneToOne(cascade = CascadeType.REMOVE)
    private Rotina rotina;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Emocao> emocaos = new ArrayList<>();

    private Focos foco;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Desempenho> desempenhos = new ArrayList<>();

    @OneToOne(cascade = CascadeType.REMOVE)
    private DadosPessoais dadosPessoais;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<Psicologo> psicologos = new ArrayList<>();

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + getId() + // assumindo que a classe Usuario tem um campo id
                ", foco=" + foco +
                ", rotina=" + (rotina != null ? rotina.getId() : "null") +
                ", dadosPessoais=" + (dadosPessoais != null ? dadosPessoais.getId() : "null") +
                '}';
    }

}