package com.calmomilla.domain.model;

import com.calmomilla.api.dto.input.CadastroTarefaInput;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarefa{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    private String descricaoTarefa;

    @NotNull
    @ManyToOne
    private Dia dia;


}
