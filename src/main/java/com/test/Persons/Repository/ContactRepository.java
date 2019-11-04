package com.test.Persons.Repository;

import com.test.Persons.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "contact", path = "contact")
public interface ContactRepository extends CrudRepository<Contact, Long> {
}
