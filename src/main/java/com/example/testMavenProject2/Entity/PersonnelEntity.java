package com.example.testMavenProject2.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "personnel")
public class PersonnelEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String prenom;

    @Column
    private String description;

    public PersonnelEntity(Long id, String name, String prenom, String description) {
        this.id = id;
        this.name = name;
        this.prenom = prenom;
        this.description = description;
    }

    public PersonnelEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PersonnelEntity [id=" + id + ", name=" + name + ", prenom=" + prenom + ", description=" + description
                + "]";
    }

}
