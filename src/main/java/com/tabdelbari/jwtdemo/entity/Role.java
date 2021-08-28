package com.tabdelbari.jwtdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Role extends BaseEntity implements GrantedAuthority {


    public Role(String role) {
        this.role = role;
    }
    /**
     * Entity id (the id column in DB)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * role for user, it could be: ADMIN, MANAGER, DEFAULT...
     */
    private String role;

    /**
     *
     * @return role as String
     */
    @Override
    public String getAuthority() {
        return role;
    }
}
