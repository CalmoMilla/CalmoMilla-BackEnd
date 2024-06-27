package com.calmomilla.domain.model;

import com.calmomilla.domain.utils.enums.Especializacoes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Psicologo extends Usuario{

    private List<Especializacoes> especializacoes;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @NotBlank
    private String numeroRegistro;

    @ManyToMany
    private List<Paciente> pacientes;

    @OneToMany(mappedBy = "autor")
    @JsonIgnore
    private List<Blog> postagem;

}
