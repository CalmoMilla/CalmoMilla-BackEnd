package com.calmomilla.api.exceptionHandler;

import com.calmomilla.domain.exception.DataNotFoundException;
import com.calmomilla.domain.exception.NegocioException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.stream.Collectors;

@RestControllerAdvice
@AllArgsConstructor
public class ApinExeptionHandler  extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle("Um ou mais campos estão invalidos!");
        problemDetail.setType(URI.create("https://calmomilla.com/erros/campos-invalidos"));

        var fields = ex.getBindingResult().getAllErrors().stream()
                .collect(Collectors.toMap(error -> ((FieldError) error).getField(),
                        error -> messageSource.getMessage(error, LocaleContextHolder.getLocale())));

        problemDetail.setProperty("fields",fields);
        return super.handleExceptionInternal(ex, problemDetail,headers, status, request);
    }


    @ExceptionHandler(DataNotFoundException.class)
    public ProblemDetail handleDataDataNotFound(DataNotFoundException e){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NO_CONTENT);
        problemDetail.setTitle("Recurso está em uso");
        problemDetail.setType(URI.create("https://calmomilla.com/erros/recurso-em-uso"));

        return problemDetail;
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIndegityViolation(DataIntegrityViolationException e){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle("Recurso está em uso");
        problemDetail.setType(URI.create("https://calmomilla.com/erros/recurso-em-uso"));

        return problemDetail;
    }

    @ExceptionHandler(NoSuchMethodException.class)
    public ProblemDetail NoSuchMethodException(NoSuchMethodException e){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle(e.getMessage());
        problemDetail.setType(URI.create("https://calmomilla.com/erros/recurso-nao-encontrado"));

        return problemDetail;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail BadCredentialsException(BadCredentialsException e){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(e.getMessage());
        problemDetail.setType(URI.create("https://calmomilla.com/erros/usuario-nao-encontrado"));

        return problemDetail;
    }
    @ExceptionHandler(NegocioException.class)
    public ProblemDetail handleNegocio(NegocioException e){
        ProblemDetail problemDetail;
        if (e.getMessage().equals(HttpStatus.INTERNAL_SERVER_ERROR.toString())){
            problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            problemDetail.setTitle("Ocorreu um erro no servidor!");
            problemDetail.setType(URI.create("https://calmomilla.com/erros/server-error"));
        }else {
            problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
            problemDetail.setTitle(e.getMessage());
            problemDetail.setType(URI.create("https://calmomilla.com/erros/regra-de-negocio"));
        }

        return problemDetail;
    }


}

