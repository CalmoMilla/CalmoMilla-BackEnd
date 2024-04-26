package com.calmomilla.domain.model;

import jakarta.persistence.Entity;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Psicologo extends Usuario{

    private List<String> especializacoes;

    private String numeroRegistro;



}
