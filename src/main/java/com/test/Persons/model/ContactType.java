package com.test.Persons.model;

import javax.persistence.*;

@Entity
@Table(name = "contacttype")
public class ContactType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO, generator="contacttype_id_seq")
    @SequenceGenerator(name="contacttype_id_seq", sequenceName="contacttype_id_seq", allocationSize=1)
    private Long Id;
    private String title;
    private String desc;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ContactType(Long id) {
        this.Id = id;
    }

    protected ContactType() { }
}
