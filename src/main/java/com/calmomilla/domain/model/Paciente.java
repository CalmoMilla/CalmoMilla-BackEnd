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

    private boolean precisaPreencherQuestionario;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<Rotina> rotinas;

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



}