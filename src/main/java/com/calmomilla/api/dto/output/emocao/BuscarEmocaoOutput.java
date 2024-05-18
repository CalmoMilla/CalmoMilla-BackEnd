package com.calmomilla.api.dto.output.emocao;

import com.calmomilla.domain.model.Paciente;
import com.calmomilla.domain.utils.Emocoes;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class BuscarEmocaoOutput {

    private String id;

    private String descricao;

    private List<Emocoes> emocoes = new ArrayList<>();

    private LocalDate dataRegistro;
    @JsonIgnore
    private Paciente paciente;
}
