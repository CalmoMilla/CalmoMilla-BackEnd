package com.calmomilla.api.dto.input.jogo;

import com.calmomilla.domain.model.Desempenho;
import com.calmomilla.domain.utils.enums.Focos;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AtualizarJogoInput {
    private String id;

    private String nome;

    private List<Focos> focos = new ArrayList<>();


    private List<Desempenho> desempenhos = new ArrayList<>();


    private String foto;

    private int avaliacao;


    private String descricao;


}
