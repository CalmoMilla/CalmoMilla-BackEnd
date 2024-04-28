package com.calmomilla.api.dto.input;


import com.calmomilla.domain.model.Dia;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CadastroTarefaInput {

    @NotNull
    private String descricaoTarefa;

    @NotNull
    private Dia dia;
}
