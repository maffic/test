package com.test.Persons.exception;

import lombok.Getter;

public class PersonNotfoundException extends RuntimeException {

    @Getter
    private Long id;

    public PersonNotfoundException(Long id) {
        this.id = id;
    }
}
