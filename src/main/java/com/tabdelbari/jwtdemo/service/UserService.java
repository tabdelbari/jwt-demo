package com.tabdelbari.jwtdemo.service;

import com.tabdelbari.jwtdemo.entity.User;
import com.tabdelbari.jwtdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements RestService<User, String>, UserDetailsService {

    private final UserRepository repo;

    @Autowired
    public UserService(UserRepository repo) {
        this.repo = repo;
    }


    @Override
    public User get(String id) {
        return repo.findById(id).get();
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        repo.findAll().forEach(list::add);
        return list;
    }

    @Override
    public User save(User entity) {
        return repo.save(entity);
    }

    @Override
    public User update(String id, User entity) {
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

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            return repo.findUserByUsername(username).orElseThrow();
        }catch (Exception e){
            throw new UsernameNotFoundException("Invalid authentication attempt with username: " + username, e);
        }
    }
}
