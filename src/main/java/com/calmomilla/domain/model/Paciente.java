package com.calmomilla.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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

}