package com.calmomilla.api.dto.output.rotina;

import com.calmomilla.domain.model.Dia;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AtualizarTarefaOutput {
    @NotNull
    private List<Dia> dias;
}
