package com.test.Persons.Controllers;

import com.test.Persons.services.ContactServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @PostMapping("{Id}/add/addr")
    public ResponseEntity<?> addContactAddr(@PathVariable Long Id, @RequestBody String value) {
        return new ContactServices().addAddr(Id, value);
    }

    @PostMapping("{Id}/add/email")
    public ResponseEntity<?> addContactEmail(@PathVariable Long Id, @RequestBody String value) {
        return new ContactServices().addEmail(Id, value);
    }

    @PostMapping("{Id}/add/phone")
    public ResponseEntity<?> addContactPhone(@PathVariable Long Id, @RequestBody String value) {
        return new ContactServices().addPhone(Id, value);
    }
}
