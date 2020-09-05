package com.justa.challenge.config.validation;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorValidationHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroDeFormDto> handler(MethodArgumentNotValidException excepiton){
        List<ErroDeFormDto> dtos = new ArrayList<>();
        List<FieldError> fieldErrors = excepiton.getBindingResult().getFieldErrors();

        fieldErrors.forEach(e->{
            String menssage = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroDeFormDto erro = new ErroDeFormDto(e.getField(),menssage);
            dtos.add(erro);


        });
        return dtos;

    }
}
