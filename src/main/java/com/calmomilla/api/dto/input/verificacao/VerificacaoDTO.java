package com.calmomilla.api.dto.input.verificacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificacaoDTO {
    @NotBlank
    private String cpf;

   @NotNull
    private LocalDate dataNasc;

//    public VerificacaoDTO(String cpf, LocalDate dataNasc) {
//    }
}
