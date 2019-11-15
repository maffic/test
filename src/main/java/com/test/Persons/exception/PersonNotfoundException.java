package com.test.Persons.exception;

import lombok.Getter;

public class PersonNotfoundException extends RuntimeException {
    
    private static final long serialVersionUID = 2L;

    @Getter
    private Long id;

    public PersonNotfoundException(Long id) {
        this.id = id;
    }
}
