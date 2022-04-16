package com.example.service.interfaces;

import com.example.entity.Role;
import com.example.exception.RoleAlreadyExistException;
import com.example.exception.RoleNotFoundException;

import java.util.List;

public interface RoleService {

    List<Role> getRoleList();

    Role getRoleByName(String name) throws RoleNotFoundException;

    Role saveRole(Role role) throws RoleAlreadyExistException;

    Role updateRole(String rolename, Role role) throws RoleNotFoundException;

    void deleteRole(String rolename);
}
