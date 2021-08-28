package com.tabdelbari.jwtdemo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends BaseEntity implements UserDetails {

    /**
     * Entity id (the id column in DB)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * username it could be: email, nickname ...
     */
    private String username;

    /**
     * password for authentication
     */
    private String password;

    //====================== ATTRIBUTES FOR SPRING SECURITY===============================
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;


    /**
     * list of roles for user (one user can have many roles)
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> roles = new ArrayList<>();


    @Override
    @JsonIgnore
    public Collection<Role> getAuthorities() {
        return roles;
    }
}
