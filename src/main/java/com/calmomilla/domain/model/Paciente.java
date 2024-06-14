package com.calmomilla.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class  Paciente extends Usuario{

    @OneToOne
    private Rotina rotina;

    @OneToMany
    private List<Emocao> emocaos = new ArrayList<>();

    @OneToOne(cascade = CascadeType.REMOVE)
    private DadosPessoais dadosPessoais;

}