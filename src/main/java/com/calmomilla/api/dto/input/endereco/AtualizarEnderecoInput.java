package com.calmomilla.api.dto.input.endereco;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarEnderecoInput {

    private String id;
    private String cep;
    private String bairro;
    private String localidade;
    private String logradouro;
    private String latitude ;
    private String longitude;
    private String uf;

}
