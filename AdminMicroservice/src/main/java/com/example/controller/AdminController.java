package com.example.controller;

import com.example.dto.RoleDTO;
import com.example.dto.UserDTO;
import com.example.service.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(path = "/all/users", produces = "application/json")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users = adminService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(path = "/user/{username}", produces = "application/json")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable("username") String username){
        UserDTO dto = adminService.getUserByUsername(username);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(path = "/all/roles", produces = "application/json")
    public ResponseEntity<List<RoleDTO>> getAllRoles(){
        List<RoleDTO> roles = adminService.getAllRoles();
        return ResponseEntity.ok().body(roles);
    }

    @PostMapping(path = "/create/role", produces = "application/json")
    public ResponseEntity<RoleDTO> createUser(@RequestBody RoleDTO dto){
        RoleDTO result = adminService.saveRole(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping(path = "/update/user/{username}", produces = "application/json")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("username") String username, @RequestBody UserDTO dto) {
        UserDTO user = adminService.updateUserProps(username, dto);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping(path = "/update/user/role/{username}", produces = "application/json")
    public ResponseEntity<UserDTO> updateUserRoles(@PathVariable("username") String username, @RequestBody RoleDTO dto){
        UserDTO user = adminService.setUserRole(username, dto);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping(path = "/update/role/{rolename}", produces = "application/json")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable("rolename") String roleName, @RequestBody RoleDTO dto){
        RoleDTO role = adminService.updateRole(roleName, dto);
        return ResponseEntity.ok().body(role);
    }

    @DeleteMapping(path = "/delete/user/{username}", produces = "application/json")
    public ResponseEntity<String> deleteUser(@PathVariable("username") String username){
        String response = adminService.deleteUser(username);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(path = "/delete/user/role/{username}", produces = "application/json")
    public ResponseEntity<UserDTO> deleteUserRole(@PathVariable("username") String username, @RequestBody RoleDTO dto){
        UserDTO user = adminService.deleteUserRole(username, dto);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping(path = "/delete/role/{rolename}", produces = "application/json")
    public ResponseEntity<String> deleteRole(@PathVariable("rolename") String roleName){
        String response = adminService.deleteRole(roleName);
        return ResponseEntity.ok().body(response);
    }
}
