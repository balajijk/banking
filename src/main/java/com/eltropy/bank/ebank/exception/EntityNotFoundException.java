package com.eltropy.bank.ebank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String entityName;
    private String fieldName;
    private Object fieldValue;

    public EntityNotFoundException(String entityName, String fieldName, Object fieldValue) {
        super(String.format("%s not found for %s : '%s'", entityName, fieldName, fieldValue));
        this.entityName = entityName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
