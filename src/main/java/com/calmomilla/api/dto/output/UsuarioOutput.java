package com.calmomilla.api.dto.output;

import com.calmomilla.domain.utils.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioOutput {

    private String nome;
    private String segundoNome;
    private String email;
    private String senha;

}
