package com.calmomilla.api.dto.output.desempenho;

import com.calmomilla.domain.model.Desempenho;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuscarEstatisticasOutput {


    List<Desempenho> desempenhoJogoMemoria = new ArrayList<>();
    List<Desempenho>desempenhoSudoku = new ArrayList<>();
    List<Desempenho>desempenhoQuiz = new ArrayList<>();

    double mediaJogoMemoria = 0;
    double mediaSudoku = 0;
    double mediaQuiz = 0;


}
