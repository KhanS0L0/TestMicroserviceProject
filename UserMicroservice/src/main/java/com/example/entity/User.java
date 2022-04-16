package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME", unique = true, nullable = false)
    private String username;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "USERS_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
    )
    private List<Role> roles;

    public boolean roleContain(String roleName){
        List<Role> roles = this.getRoles();
        return roles.stream().anyMatch(role -> role.getName().equals(roleName));
    }

    public void updateProps(User user){
        if(user.getUsername() != null && !user.getUsername().isEmpty() && !user.getUsername().isBlank())
            this.setUsername(user.getUsername());
        if(user.getEmail() != null && !user.getEmail().isEmpty() && !user.getEmail().isBlank())
            this.setEmail(user.getEmail());
        if(user.getPassword() != null && !user.getPassword().isEmpty() && !user.getPassword().isBlank())
            this.setPassword(user.getPassword());
    }
}



