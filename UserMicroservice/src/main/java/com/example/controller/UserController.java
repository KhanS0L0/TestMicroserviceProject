package com.example.controller;

import com.example.dto.RoleDTO;
import com.example.dto.UserDTO;
import com.example.entity.User;
import com.example.exception.UserAlreadyExistException;
import com.example.mapper.RoleMapper;
import com.example.mapper.UserMapper;
import com.example.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper, RoleMapper roleMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<User> users = userService.listAllUsers();
        List<UserDTO> dtoList = new ArrayList<>();
        users.forEach(user -> dtoList.add(userMapper.entityToDto(user)));
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping(path = "/{username}", produces = "application/json")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("username") String username){
        UserDTO dto = userMapper.entityToDto(userService.getUserByUsername(username));
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(path = "/{username}/roles", produces = "application/json")
    public ResponseEntity<List<RoleDTO>> getUserRoles(@PathVariable("username") String username){
        User user = userService.getUserByUsername(username);
        List<RoleDTO> roles = new ArrayList<>();
        user.getRoles().forEach(role -> roles.add(roleMapper.entityToDto(role)));
        return ResponseEntity.ok().body(roles);
    }

    @PostMapping(path = "/create", produces = "application/json")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO dto) throws UserAlreadyExistException {
        User user = userService.saveUser(userMapper.dtoToEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.entityToDto(user));
    }

    @PutMapping(path = "/update/{username}", produces = "application/json")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("username") String username, @RequestBody UserDTO dto) {
        User user = userService.updateUser(username, userMapper.dtoToEntity(dto));
        return ResponseEntity.ok().body(userMapper.entityToDto(user));
    }

    @PutMapping(path = "/update/role/{username}", produces = "application/json")
    public ResponseEntity<UserDTO> updateUserRoles(@PathVariable("username") String username, @RequestBody RoleDTO dto){
        User user = userService.setRoleForUser(username, dto.getName());
        return ResponseEntity.ok().body(userMapper.entityToDto(user));
    }

    @DeleteMapping(path = "/delete/{username}", produces = "application/json")
    public ResponseEntity<String> deleteUser(@PathVariable("username") String username){
        userService.deleteUser(username);
        return ResponseEntity.ok().body("User successfully deleted");
    }

    @DeleteMapping(path = "/delete/role/{username}", produces = "application/json")
    public ResponseEntity<UserDTO> deleteUserRole(@PathVariable("username") String username, @RequestBody RoleDTO dto){
        User user = userService.deleteRoleForUser(username, dto.getName());
        return ResponseEntity.ok().body(userMapper.entityToDto(user));
    }
}
