package com.orinka.springboot.service;

import com.orinka.springboot.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role getRoleByName(String name);
    Role getRoleById(Long id);
}
