package com.test.Persons.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "contacttype")
@NoArgsConstructor( access = AccessLevel.PROTECTED)
@Data
public class ContactType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO, generator="contacttype_id_seq")
    @SequenceGenerator(name="contacttype_id_seq", sequenceName="contacttype_id_seq", allocationSize=1)
    private Long Id;

    private String title;
    private String desc;

    public ContactType(Long id) {
        this.Id = id;
    }
}
