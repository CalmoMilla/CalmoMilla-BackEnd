package com.calmomilla.domain.model;

import com.calmomilla.domain.utils.enums.Focos;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class  Paciente extends Usuario{

    @OneToOne(cascade = CascadeType.REMOVE)
    private Rotina rotina;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Emocao> emocaos = new ArrayList<>();

    private Focos Foco;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Desempenho> desempenhos = new ArrayList<>();

    @OneToOne(cascade = CascadeType.REMOVE)
    private DadosPessoais dadosPessoais;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<Psicologo> psicologosSalvos = new ArrayList<>();

}