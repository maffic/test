package com.test.Persons.Repository;

import com.test.Persons.model.ContactType;
import org.springframework.data.repository.CrudRepository;

public interface ContactTypeRepository extends CrudRepository<ContactType, Long> {
}
