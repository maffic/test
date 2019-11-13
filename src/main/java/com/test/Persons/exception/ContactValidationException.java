package com.test.Persons.exception;

import com.test.Persons.model.ContactType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ContactValidationException extends RuntimeException {

    @Getter
    private String value;
    @Getter
    private ContactType contactType;

    public ContactValidationException(ContactType contactType, String value) {
        this.contactType = contactType;
        this.value = value;
    }

}
