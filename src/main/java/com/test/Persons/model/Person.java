package com.test.Persons.model;

import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@ToString
@NoArgsConstructor( access = AccessLevel.PROTECTED)
public class Person {

    @Id @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="person_id_seq")
    @SequenceGenerator(name="person_id_seq", sequenceName="person_id_seq", allocationSize=1)
    @Getter
    private Long id;

    @Column(name = "first_name")
    @Getter @Setter
    private String firstName;

    @Column(name = "last_name")
    @Getter @Setter
    private String lastName;

    @Column(name = "gender", length=6)
    @Getter @Setter
    private String gender;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "person_id")
    private Set<Contact> contacts = new HashSet<Contact>();

    public Person(String firstName, String lastName, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public Set<Contact> getContacts() {
        return Collections.unmodifiableSet(contacts);
    }

    public void add(Contact contact) {
        Assert.isNull(contact, "contact is null");
        this.contacts.add(contact);
    }

    public void add(Set<Contact> contacts) {
        Assert.noNullElements(contacts, "contact is null");
        this.contacts.addAll(contacts);
    }
}
