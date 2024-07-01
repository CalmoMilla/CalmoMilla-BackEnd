package com.calmomilla.domain.model;

import com.calmomilla.domain.utils.enums.Focos;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
public class Tarefa{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    private String titulo;

    @NotNull
    @ElementCollection(targetClass = Focos.class)
    @Enumerated(EnumType.STRING)
    private List<Focos> focos;

    @NotBlank
    private String link;

    @NotNull
    private boolean status;

    public Tarefa(String titulo, List<Focos> focos, String link, boolean status) {
        this.titulo = titulo;
        this.focos = focos;
        this.link = link;
        this.status = status;
    }
}
