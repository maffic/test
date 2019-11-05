package com.test.Persons.Controllers;

import com.sun.istack.NotNull;
import com.test.Persons.Repository.PersonRepository;
import com.test.Persons.model.Contact;
import com.test.Persons.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.DecimalMin;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/persons")
public class PersonsController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("all")
    public Iterable<Person>  getAllPersons() {
        return personRepository.findAll();
    }

    @GetMapping("{id}")
    public Person findByIds(@PathVariable @NotNull @DecimalMin("0") Long id) {
        // можно вставит ьпроверку что объект существует прежде чем его просить
        return personRepository.findById(id).get();
    }

    @GetMapping("/firstname/{firstname}")
    public List<Person> findByFirstName(@PathVariable @NotNull String firstname) {
        return personRepository.findByFirstName(firstname);
    }

    @RequestMapping(value = "/{personId}/contact", method = RequestMethod.GET)
    public Set<Contact> getByPersonIdContact(@PathVariable @NotNull @DecimalMin("0") Long Id) {
        if (!personRepository.existsById(Id)) return null;
        Person person = personRepository.findById(Id).get();
        return person.getContacts();
    }

    @DeleteMapping("{Id}")
    public ResponseEntity<Object> deletePerson(@PathVariable Long Id){
        if (!personRepository.existsById(Id)) {
            return ResponseEntity.notFound().build();
        }
        personRepository.deleteById(Id);
        return  ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addPerson(@RequestBody Person person) {
        Person newPerson = personRepository.save( new Person(person.getFirstName(), person.getLastName(), person.getGender()));
        if (newPerson == null) {
            return ResponseEntity.created(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(person.getId()).toUri()).build();
        }
        return ResponseEntity.noContent().build();
    }
}
