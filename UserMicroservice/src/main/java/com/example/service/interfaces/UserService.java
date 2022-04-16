package com.example.service.interfaces;

import com.example.entity.User;
import com.example.exception.UserAlreadyExistException;
import com.example.exception.UserNotFoundException;

import java.util.List;

public interface UserService {

    List<User> listAllUsers();

    User getUserByUsername(String username) throws UserNotFoundException;

    User saveUser(User user) throws UserAlreadyExistException;

    User updateUser(String username, User user) throws UserNotFoundException;

    void deleteUser(String username);

    User setRoleForUser(String username, String roleName);

    User deleteRoleForUser(String username, String roleName);
}