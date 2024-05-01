package com.calmomilla.api.dto.output.endereco;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastroEnderecoOutput {
    @NotBlank
    private String cep;

    @NotBlank
    private String bairro;

    @NotBlank
    private String localidade;

    @NotBlank
    private String logradouro;

    @NotBlank
    private String latitude ;

    @NotBlank
    private String longitude;

    @NotBlank
    private String uf;
}
