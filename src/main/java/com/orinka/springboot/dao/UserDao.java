package com.orinka.springboot.dao;

import com.orinka.springboot.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserDao {
    @PersistenceContext
    EntityManager entityManager;

    public void update(User user) {
        entityManager.merge(user);
    }
}
