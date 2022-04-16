package com.example.service.interfaces;

import com.example.dto.RoleDTO;
import com.example.dto.UserDTO;

public interface AdminService {

    UserDTO updateUserProps(String username, UserDTO dto);

    String deleteUser(String username);

    UserDTO setUserRole(String username, RoleDTO dto);

    UserDTO deleteUserRole(String username, RoleDTO dto);

    RoleDTO createRole(RoleDTO dto);

    RoleDTO updateRole(String roleName, RoleDTO dto);

    String deleteRole(String roleName);

}