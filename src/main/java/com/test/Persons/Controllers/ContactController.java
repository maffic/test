package com.test.Persons.Controllers;

import com.test.Persons.Repository.ContactRepository;
import com.test.Persons.Repository.PersonRepository;
import com.test.Persons.model.Contact;
import com.test.Persons.model.ContactType;
import com.test.Persons.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    ContactRepository contactRepository;

    private ContactType email = new ContactType( 1L);
    private ContactType phone = new ContactType(2L);
    private ContactType address = new ContactType(3L);

    static boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    static boolean isValidPhone(String phone) {
        String regex = "\\+\\d([-, (, )]\\d{3}){2}-\\d{2}-\\d{2}";
        return phone.matches(regex);
    }

    @PostMapping("{Id}/{type:addr|email|phone}")
    public ResponseEntity<?> addContact(@PathVariable Long Id, @PathVariable String type, @RequestBody String value) {
        Optional<Person> person = personRepository.findById(Id);
        if (person.isPresent()) {
            ContactType contactType = email;
            switch (type) {
                case "addr":
                    contactType = address;
                    break;
                case "email":
                    if (!isValidEmail(value)) return ResponseEntity.badRequest().build();
                    break;
                case "phone":
                    if (!isValidPhone(value)) return ResponseEntity.badRequest().build();
                    contactType = phone;
                    break;
            }
            Contact contact = new Contact(person.get(), contactType, value);
            contactRepository.save(contact);
            return ResponseEntity.ok(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}/contact")
                    .buildAndExpand(person.get().getId()).toUri());
        }
        return ResponseEntity.notFound().build();
    }

}
