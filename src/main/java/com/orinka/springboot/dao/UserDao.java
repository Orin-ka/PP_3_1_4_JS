package com.orinka.springboot.dao;

import com.orinka.springboot.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserDao {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void update(User user) {
        entityManager.merge(user);
    }
}
