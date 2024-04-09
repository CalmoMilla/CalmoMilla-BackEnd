package com.calmomilla.api.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginOutput {

    private String token;

    public LoginOutput(String token) {
        this.token = token;
    }
}
