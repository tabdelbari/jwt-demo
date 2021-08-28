package com.tabdelbari.jwtdemo.service;

import com.tabdelbari.jwtdemo.entity.Role;
import com.tabdelbari.jwtdemo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RoleService implements RestService<Role, String> {

    private final RoleRepository repo;

    @Autowired
    public RoleService(RoleRepository repo) {
        this.repo = repo;
    }


    @Override
    public Role get(String id) throws NoSuchElementException {
        return repo.findById(id).get();
    }

    @Override
    public List<Role> getAll() {
        List<Role> list = new ArrayList<>();
        repo.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Role save(Role entity) {
        return repo.save(entity);
    }

    @Override
    public Role update(String id, Role entity) {
        entity.setId(id);
        return repo.save(entity);
    }

    @Override
    public void delete(String id) {
        repo.findById(id);
    }

    @Override
    public void deleteAll() {
        repo.deleteAll();
    }
}
