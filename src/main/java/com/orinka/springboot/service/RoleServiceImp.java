package com.orinka.springboot.service;

import com.orinka.springboot.entity.EnumRole;
import com.orinka.springboot.entity.Role;
import com.orinka.springboot.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleByName(String name)  {
        return new Role(EnumRole.valueOf(name));
    }
}
