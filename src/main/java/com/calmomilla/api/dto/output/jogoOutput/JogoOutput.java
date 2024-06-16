package com.calmomilla.api.dto.output.jogoOutput;

import com.calmomilla.domain.model.Desempenho;
import com.calmomilla.domain.utils.enums.Focos;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class JogoOutput {

    private String id;

    private String nome;

    private String descricao;

    private String foto;

    private List<Focos>focos;

    private List<Desempenho> desempenhos;

}
