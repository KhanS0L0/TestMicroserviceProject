package com.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private String username;
    private String email;
    private String password;
    private List<RoleDTO> roles;
}
