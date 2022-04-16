package com.example.controller;

import com.example.dto.RoleDTO;
import com.example.entity.Role;
import com.example.exception.RoleAlreadyExistException;
import com.example.mapper.RoleMapper;
import com.example.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/roles")
public class RoleController {

    private final RoleService roleService;
    private final RoleMapper mapper;

    @Autowired
    public RoleController(RoleService roleService, RoleMapper mapper) {
        this.roleService = roleService;
        this.mapper = mapper;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity<List<RoleDTO>> getAllRoles(){
        List<Role> roles = roleService.getRoleList();
        List<RoleDTO> dtoList = new ArrayList<>();
        roles.forEach(role -> dtoList.add(mapper.entityToDto(role)));
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping(path = "/{rolename}", produces = "application/json")
    public ResponseEntity<RoleDTO> getRoleByName(@PathVariable("rolename") String roleName){
        RoleDTO dto = mapper.entityToDto(roleService.getRoleByName(roleName));
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping(path = "/create", produces = "application/json")
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO dto) throws RoleAlreadyExistException {
        Role role = roleService.saveRole(mapper.dtoToEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(role));
    }

    @PutMapping(path = "/update/{rolename}", produces = "application/json")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable("rolename") String rolename, @RequestBody RoleDTO dto){
        Role role = roleService.updateRole(rolename, mapper.dtoToEntity(dto));
        return ResponseEntity.ok().body(mapper.entityToDto(role));
    }

    @DeleteMapping(path = "/delete/{rolename}", produces = "application/json")
    public ResponseEntity<String> deleteRole(@PathVariable("rolename") String rolename){
        roleService.deleteRole(rolename);
        return ResponseEntity.ok().body("Role with name: " + rolename + " successfully deleted");
    }
}
