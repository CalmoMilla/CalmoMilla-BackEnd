package com.calmomilla.api.dto.output.psicologo;

import com.calmomilla.domain.utils.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuscarPsicologoEmailOutput {

    private String id;
    private String email;
    private String nome;
    private String foto;
    private UserRole role;
    private String descricao;

}
