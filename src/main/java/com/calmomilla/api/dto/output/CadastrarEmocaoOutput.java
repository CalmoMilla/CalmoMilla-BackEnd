package com.calmomilla.api.dto.output;

import com.calmomilla.domain.utils.Emocoes;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CadastrarEmocaoOutput {

    List<Emocoes> emocoes;

    private String mensagem;

}
