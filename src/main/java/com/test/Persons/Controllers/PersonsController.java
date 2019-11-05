package com.test.Persons.Controllers;

import com.sun.istack.NotNull;
import com.test.Persons.Repository.PersonRepository;
import com.test.Persons.model.Contact;
import com.test.Persons.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/persons")
public class PersonsController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/all")
    public Iterable<Person>  getAllPersons() {
        return personRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Person findByIds(@PathVariable @NotNull @DecimalMin("0") Long id) {
        return personRepository.findById(id).get();
    }

    @GetMapping(value = "/firstname/{firstname}")
    public List<Person> findByFirstName(@PathVariable @NotNull String firstname) {
        return personRepository.findByFirstName(firstname);
    }

    @RequestMapping(value = "/{personId}/contact", method = RequestMethod.GET)
    public Iterable<Contact> getByPersonIdContact(@PathVariable @NotNull @DecimalMin("0") Long Id) {
        Optional<Person> person = personRepository.findById(Id);
        Assert.isNull(person, "Person not find");
        return person.get().getContacts();
    }
}
