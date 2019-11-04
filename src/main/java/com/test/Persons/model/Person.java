package com.test.Persons.model;

import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="contact_id_seq")
    @SequenceGenerator(name="contact_id_seq", sequenceName="contact_id_seq", allocationSize=1)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender", length=6)
    private String gender;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contact_id")
    private Set<Contact> contacts = new HashSet<Contact>();

    protected Person() {}

    public Person(String firstName, String lastName, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return String.format(
                "Person[id=%d, firstName='%s', lastName='%s', gender='%s']",
                id, firstName, lastName, gender);
    }

    public Set<Contact> getContacts() {
        return Collections.unmodifiableSet(contacts);
    }

    public void add(Contact contact) {
        Assert.isNull(contact, "contact is null");
        this.contacts.add(contact);
    }
}
