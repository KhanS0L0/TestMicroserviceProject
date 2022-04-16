package com.example.service.interfaces;

import com.example.dto.RoleDTO;
import com.example.dto.UserDTO;

import java.util.List;

public interface AdminService {

    public List<UserDTO> getAllUsers();

    public UserDTO getUserByUsername(String username);

    public UserDTO updateUserProps(String username, UserDTO dto);

    public String deleteUser(String username);

    public UserDTO setUserRole(String username, RoleDTO dto);

    public UserDTO deleteUserRole(String username, RoleDTO dto);

    public List<RoleDTO> getAllRoles();

    public RoleDTO getRoleByName(String roleName);

    public RoleDTO saveRole(RoleDTO dto);

    public RoleDTO updateRole(String roleName, RoleDTO dto);

    public String deleteRole(String roleName);
}
