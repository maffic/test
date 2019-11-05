package com.test.Persons.model;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    protected Contact() {}
}
