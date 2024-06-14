package com.calmomilla.api.dto.input.emocao;

import com.calmomilla.domain.model.Paciente;
import com.calmomilla.domain.utils.enums.Emocoes;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CadastrarEmocaoInput {

    private String descricao;

    @NotNull
    private List<Emocoes> emocoes = new ArrayList<>();

    private LocalDate dataRegistro;

    @ManyToOne
    private Paciente paciente;

}
