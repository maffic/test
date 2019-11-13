package com.test.Persons.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor( access = AccessLevel.PROTECTED)
public class Contact {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO, generator="contact_id_seq")
    @SequenceGenerator(name="contact_id_seq", sequenceName="contact_id_seq", allocationSize=1)
    @Getter
    private Long id;

    @Column(nullable=false)
    @Getter @Setter
    private String value;

    @OneToOne @JoinColumn(name = "contact_type", referencedColumnName = "id")
    @Getter
    private ContactType type;

    @ManyToOne
    private Person person;

    public Contact(Person person, ContactType contactType, String value) {
        this.person = person;
        if (contactType == null) this.type = new ContactType(1L);
        this.value = value;
    }
}
