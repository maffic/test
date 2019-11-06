package com.test.Persons.Repository;

import com.test.Persons.model.Person;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(collectionResourceRel = "person", path = "person")
public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByLastName(String last_name);

    @Cacheable("byPersonFirstName")
    List<Person> findByFirstName(String first_name);

    @Override
    @CacheEvict(value = "byPersonFirstName", key = "#p0.firstName")
    <S extends Person> S save(S entity);
}