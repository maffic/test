package com.test.Persons.Controllers;

import com.test.Persons.Repository.ContactRepository;
import com.test.Persons.Repository.PersonRepository;
import com.test.Persons.model.Contact;
import com.test.Persons.model.ContactType;
import com.test.Persons.model.Person;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
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

    static boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    static boolean isValidPhone(String phone) {
        String regex = "\\+\\d([-, (, )]\\d{3}){2}-\\d{2}-\\d{2}";
        return phone.matches(regex);
    }

    @PostMapping("{Id}/contact/addr")
    public ResponseEntity<?> addContactAddr(@PathVariable Long Id, @PathVariable String type, @RequestBody String value) {
        return new newAddrContact(value).Post(Id);
    }

    @PostMapping("{Id}/contact/email")
    public ResponseEntity<?> addContactEmail(@PathVariable Long Id, @PathVariable String type, @RequestBody String value) {
        return new newAddrContact(value).Post(Id);
    }

    @PostMapping("{Id}/contact/phone")
    public ResponseEntity<?> addContactPhone(@PathVariable Long Id, @PathVariable String type, @RequestBody String value) {
        return new newAddrContact(value).Post(Id);
    }

    //---Templayte metod----
    @Data
    private abstract class tmpNewContact {
        ContactType contactType;
        String value;

        abstract boolean validate();

        public ResponseEntity<?> Post(Long Id) {
            Optional<Person> person = personRepository.findById(Id);
            if (person.isPresent() && validate()) {
                Contact contact = new Contact(person.get(), contactType, value);
                contactRepository.save(contact);
                return ResponseEntity.ok(ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}/contact")
                        .buildAndExpand(person.get().getId()).toUri());
            }
            return ResponseEntity.notFound().build();
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
