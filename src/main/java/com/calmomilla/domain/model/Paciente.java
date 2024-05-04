package com.calmomilla.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class  Paciente extends Usuario{

    @OneToOne
    private Rotina rotina;

}