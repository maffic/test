package com.test.Persons.Controllers;

import com.sun.istack.NotNull;
import com.test.Persons.Repository.PersonRepository;
import com.test.Persons.exception.PersonNotfoundException;
import com.test.Persons.model.Contact;
import com.test.Persons.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping({"all", "/"})
    public Iterable<Person>  getAllPersons() {
        return personRepository.findAll();
    }

    @GetMapping("{id}")
    public Person findByIds(@PathVariable @NotNull @DecimalMin("0") Long id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) return personRepository.findById(id).get();
        throw new PersonNotfoundException(id);
    }

    @GetMapping("/firstname/{firstname}")
    public List<Person> findByFirstName(@PathVariable @NotNull String firstname) {
        return personRepository.findByFirstName(firstname);
    }

    @RequestMapping(value = "/{Id}/contact", method = RequestMethod.GET)
    public Set<Contact> getByPersonIdContact(@PathVariable @NotNull @DecimalMin("0") Long id) {
        Optional<Person> person = personRepository.findById(id);
        if (!person.isPresent()) throw new PersonNotfoundException(id);
        return person.get().getContacts();
    }

    @DeleteMapping("{Id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id){
        if (!personRepository.existsById(id)) {
            throw new PersonNotfoundException(id);
        }
        personRepository.deleteById(id);
        return  ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> updatePerson(@RequestBody Person person) {
        if (personRepository.existsById(person.getId()) && !person.equals(personRepository.findById(person.getId()).get())) {
            personRepository.save(person);
            return ResponseEntity.ok(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(person.getId()).toUri());
        }
        throw new PersonNotfoundException(person.getId());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addPerson(@RequestBody Person person) {
        Person newPerson = new Person(person.getFirstName(), person.getLastName(), person.getGender());
        if (!person.getContacts().isEmpty()) newPerson.add(person.getContacts());
        personRepository.save(newPerson);
        return ResponseEntity.created(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(person.getId()).toUri()).build();
    }
}
