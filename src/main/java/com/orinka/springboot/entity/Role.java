package com.orinka.springboot.entity;

import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "roles")

public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;


    public Role() {}
    public Role(String role) {
        this.name = role;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String getAuthority() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return  name.equals(role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}

