package com.calmomilla.domain.exception;

import org.springframework.http.HttpStatus;

public class NegocioException extends RuntimeException {

    public NegocioException(String message) {
        super(message);
    }
    public NegocioException(HttpStatus status) {
        super(String.valueOf(status));
    }
}
