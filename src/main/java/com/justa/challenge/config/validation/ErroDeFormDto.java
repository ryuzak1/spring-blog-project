package com.justa.challenge.config.validation;

public class ErroDeFormDto {

    private String field;
    private String error;

    public ErroDeFormDto(String field, String error) {
        this.field = field;
        this.error = error;
    }

    public String getField() {
        return field;
    }

    public String getError() {
        return error;
    }
}
