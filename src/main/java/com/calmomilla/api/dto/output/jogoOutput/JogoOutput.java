package com.calmomilla.api.dto.output.jogoOutput;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Data
public class JogoOutput {

    private String nome;

    private String descricao;

    private String foto;

}
