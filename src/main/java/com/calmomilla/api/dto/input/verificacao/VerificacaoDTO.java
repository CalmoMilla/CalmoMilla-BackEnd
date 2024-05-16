package com.calmomilla.api.dto.input.verificacao;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerificacaoDTO {
    @NotBlank
    private String cpf;

    @NotBlank
    private String dataNasc;

}
