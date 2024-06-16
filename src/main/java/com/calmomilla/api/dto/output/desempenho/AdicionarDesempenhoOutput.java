package com.calmomilla.api.dto.output.desempenho;

import com.calmomilla.domain.model.Jogo;
import com.calmomilla.domain.model.Paciente;
import lombok.Data;

@Data
public class AdicionarDesempenhoOutput {

    private int nivel;

    private double pontuacao;

    private Jogo jogos;

    private Paciente usuario;

}
