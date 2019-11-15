package com.test.Persons.Controllers;

import com.test.Persons.Repository.ContactRepository;
import com.test.Persons.Repository.PersonRepository;
import com.test.Persons.exception.ContactValidationException;
import com.test.Persons.exception.PersonNotfoundException;
import com.test.Persons.model.Contact;
import com.test.Persons.model.ContactType;
import com.test.Persons.model.Person;
import com.test.Persons.services.ContactServices;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    ContactServices contactServices;

    @PostMapping("{Id}/add/addr")
    public ResponseEntity<?> addContactAddr(@PathVariable Long Id, @RequestBody String value) {
        return contactServices.addAddr(Id, value);
    }

    @PostMapping("{Id}/add/email")
    public ResponseEntity<?> addContactEmail(@PathVariable Long Id, @RequestBody String value) {
        return contactServices.addEmail(Id, value);
    }

    @PostMapping("{Id}/add/phone")
    public ResponseEntity<?> addContactPhone(@PathVariable Long Id, @RequestBody String value) {
        return contactServices.addPhone(Id, value);
    }
}
