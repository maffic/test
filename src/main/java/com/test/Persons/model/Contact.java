package com.test.Persons.model;

import javax.persistence.*;

@Entity
public class Contact {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO, generator="contact_id_seq")
    @SequenceGenerator(name="contact_id_seq", sequenceName="contact_id_seq", allocationSize=1)
    private Integer id;

    @Column(nullable=false)
    private String value;

    @OneToOne
    @JoinColumn(name = "contact_type", referencedColumnName = "id")
    private ContactType type;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    protected Contact() {}
}
