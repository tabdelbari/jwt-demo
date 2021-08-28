package com.tabdelbari.jwtdemo.repository;

import com.tabdelbari.jwtdemo.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {
}
