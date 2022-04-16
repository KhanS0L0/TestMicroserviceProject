package com.example.service.interfaces;

import com.example.dto.RoleDTO;
import com.example.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO findUserByUsername(String username);

    List<RoleDTO> getAllRoles();

    UserDTO updateUsersInfo(UserDTO dto);

    UserDTO registerUser(UserDTO user);

}
