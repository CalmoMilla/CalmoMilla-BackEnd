package com.calmomilla.api.dto.input.paciente;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FavoritarPsicologoInput {
    @NotBlank
    private String idPaciente;

    @NotBlank
    private String idPsicologo;
}
