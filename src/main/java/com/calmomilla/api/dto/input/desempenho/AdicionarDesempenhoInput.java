package com.calmomilla.api.dto.input.desempenho;

import com.calmomilla.domain.model.Jogo;
import com.calmomilla.domain.model.Paciente;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdicionarDesempenhoInput {

    @NotNull
    private int nivel;

    @NotNull
    private double pontuacao;

    private Jogo jogos;

    private Paciente usuario;

    private Jogo jogo;

}
