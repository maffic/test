package com.test.Persons.Controllers;

import com.test.Persons.Repository.PersonRepository;
import com.test.Persons.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonsController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/")
    public Iterable<Person>  getAllPersons() {
        Iterable<Person> all = personRepository.findAll();
        return all;
    }
}
