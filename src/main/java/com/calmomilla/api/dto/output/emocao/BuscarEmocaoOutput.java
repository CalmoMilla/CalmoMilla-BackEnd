package com.calmomilla.api.dto.output.emocao;

import com.calmomilla.domain.model.Paciente;
import com.calmomilla.domain.utils.Emocoes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
