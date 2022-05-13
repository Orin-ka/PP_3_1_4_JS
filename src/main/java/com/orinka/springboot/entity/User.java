package com.orinka.springboot.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity(name = "User")
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(name = "job", length = 30)
    private String job;



 /*   public User() {}

    public User(String firstName, String lastName, String job) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.job = job;
    }

    public User(Long id, String firstName, String lastName, String job) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.job = job;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }*/

}
