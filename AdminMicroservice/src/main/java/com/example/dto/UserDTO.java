package com.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private String username;
    private String email;
    private String password;
    private List<RoleDTO> roles;

    public boolean containTabooRole(List<RoleDTO> tabooRoles){
        return roles.stream().anyMatch(tabooRoles::contains);
    }

}
