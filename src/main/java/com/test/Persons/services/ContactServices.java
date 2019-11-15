package com.test.Persons.services;

import com.test.Persons.Repository.ContactRepository;
import com.test.Persons.Repository.PersonRepository;
import com.test.Persons.exception.ContactValidationException;
import com.test.Persons.exception.PersonNotfoundException;
import com.test.Persons.model.Contact;
import com.test.Persons.model.ContactType;
import com.test.Persons.model.Person;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

public class ContactServices {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    ContactRepository contactRepository;

    public ResponseEntity<?> addAddr(Long id, String value)
    {
        return new newAddrContact(value).Post(id);
    }

    public ResponseEntity<?> addEmail(Long id, String value) {
        return new newEmailContact(value).Post(id);
    }

    public ResponseEntity<?> addPhone(Long id, String value)
    {
        return new newPhoneContact(value).Post(id);
    }

    //---Templayte metod----
    @Data
    private abstract class tmpNewContact {
        ContactType contactType;
        String value;

        abstract boolean validate();

        public ResponseEntity<?> Post(Long id) {
            Optional<Person> person = personRepository.findById(id);
            if (person.isPresent()) {
                if (!validate()) throw new ContactValidationException(contactType, value);
                Contact contact = new Contact(person.get(), contactType, value);
                contactRepository.save(contact);
                return ResponseEntity.ok(ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}/contact")
                        .buildAndExpand(person.get().getId()).toUri());
            }
            throw new PersonNotfoundException(id);
        }
    }

    private class newPhoneContact extends tmpNewContact
    {
        public newPhoneContact(String value)
        {
            this.contactType = new ContactType(2L);;
            this.value = value;
        }

        @Override
        public boolean validate() {
            return value.matches("\\+\\d([-, (, )]\\d{3}){2}-\\d{2}-\\d{2}");
        }
    }

    private class newAddrContact extends tmpNewContact
    {
        public newAddrContact(String value)
        {
            this.contactType = new ContactType(3L);;
            this.value = value;
        }

        @Override
        boolean validate() {
            return true;
        }
    }

    private class newEmailContact extends tmpNewContact
    {
        public newEmailContact(String value)
        {
            this.contactType = new ContactType( 1L);
            this.value = value;
        }

        @Override
        boolean validate() {
            return value.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
        }
    }
}
