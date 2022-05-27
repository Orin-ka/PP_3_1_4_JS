package com.orinka.springboot.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private EnumRole name;

    @ManyToMany(mappedBy = "roles")
    @Transient //не пишет в БД
    private Set<User> users = new HashSet<>();

    public Role() {}

    public Role(EnumRole role) {
        this.name = role;
    }
    public Role(String str) {
        this(EnumRole.valueOf(str));
    }

    @Override
    public String getAuthority() {
        return getName().toString();//String.valueOf(getName().ordinal());//.name();авторизация по № константы,,,
    }

    @Override
    public String toString() {
        return  name.name().substring(5);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return  (this.name == (role.name));//enum можно сравнивать по ==
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


}

