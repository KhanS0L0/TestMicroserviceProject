package com.example.service.implementation;

import com.example.entity.AccountRoles;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.exception.RoleNotFoundException;
import com.example.exception.UserAlreadyExistException;
import com.example.exception.UserNotFoundException;
import com.example.repository.UserRepository;
import com.example.service.interfaces.RoleService;
import com.example.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }


    @Override
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException{
        User user = userRepository.findUserByUsername(username).orElse(null);
        if(user == null)
            throw new UserNotFoundException("User with username: " + username + " not found");
        return user;
    }

    @Override
    @Transactional
    public User saveUser(User user) throws UserAlreadyExistException {
        User tmp = userRepository.findUserByUsername(user.getUsername()).orElse(null);
        if(tmp != null)
            throw new UserAlreadyExistException("User with username: " + user.getUsername() + " already exist");
        Role role = roleService.getRoleByName(AccountRoles.USER.toString());
        if(role == null)
            throw new RoleNotFoundException("Create " + AccountRoles.USER + " first");
        user.setRoles(Collections.singletonList(role));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(String username, User user) throws UserNotFoundException{
        User tmp = this.getUserByUsername(username);
        tmp.updateProps(user);
        return userRepository.save(tmp);
    }

    @Override
    @Transactional
    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    @Transactional
    public User setRoleForUser(String username, String roleName) {
        User user = this.getUserByUsername(username);
        if(user.roleContain(roleName)) return user;
        List<Role> roles = user.getRoles();
        Role role = roleService.getRoleByName(roleName);
        roles.add(role);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User deleteRoleForUser(String username, String roleName) {
        User user = this.getUserByUsername(username);
        if(user.roleContain(roleName)){
            List<Role> roles = user.getRoles();
            Role role = roleService.getRoleByName(roleName);
            roles.remove(role);
            user.setRoles(roles);
            return userRepository.save(user);
        }
        return user;
    }
}
