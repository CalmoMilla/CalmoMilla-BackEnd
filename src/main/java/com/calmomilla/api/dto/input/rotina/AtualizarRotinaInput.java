package com.calmomilla.api.dto.input.rotina;

import com.calmomilla.domain.model.Dia;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AtualizarRotinaInput {
    @NotNull
    private String id;

    @NotNull
    private List<Dia> dias;
}
