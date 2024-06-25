package com.calmomilla.api.dto.output.materia;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MateriaOutput {

    private String titulo;
    private String texto;
    private String imagem;
}
