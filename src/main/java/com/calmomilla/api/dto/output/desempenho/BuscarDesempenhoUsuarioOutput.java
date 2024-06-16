package com.calmomilla.api.dto.output.desempenho;

import com.calmomilla.domain.model.Jogo;
import lombok.Data;

@Data
public class BuscarDesempenhoUsuarioOutput {


    private String id;

    private int nivel;

    private double pontuacao;

    private Jogo jogos;

}
