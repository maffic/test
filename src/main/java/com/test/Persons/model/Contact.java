package com.test.Persons.model;

import com.test.Persons.Repository.ContactRepository;

import javax.persistence.*;

@Entity
public class Contact {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO, generator="contact_id_seq")
    @SequenceGenerator(name="contact_id_seq", sequenceName="contact_id_seq", allocationSize=1)
    private Long id;

    @Column(nullable=false)
    private String value;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_type", referencedColumnName = "id")
    private ContactType type;

    @ManyToOne
    private Person person;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Contact(Person person, ContactType contactType, String value) {
        this.person = person;
        if (contactType == null) this.type = new ContactType(1L);
        this.value = value;
    }

    protected Contact() {}
}
