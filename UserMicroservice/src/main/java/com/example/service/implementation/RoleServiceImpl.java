package com.example.service.implementation;

import com.example.entity.Role;
import com.example.exception.RoleAlreadyExistException;
import com.example.exception.RoleNotFoundException;
import com.example.repository.RoleRepository;
import com.example.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getRoleList() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleByName(String name) throws RoleNotFoundException {
        Role role = roleRepository.findByName(name).orElse(null);
        if(role == null)
            throw new RoleNotFoundException("Role with name: " + name + " doesn't exist");
        return role;
    }

    @Override
    public Role saveRole(Role role) throws RoleAlreadyExistException {
        Role tmp = roleRepository.findByName(role.getName()).orElse(null);
        if(tmp != null)
            throw new RoleAlreadyExistException("Role with name: " + role.getName() + " already exist");
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(String rolename, Role role) throws RoleNotFoundException {
        Role tmp = this.getRoleByName(rolename);
        tmp.updateProps(role);
        return roleRepository.save(tmp);
    }

    @Override
    public void deleteRole(String rolename) {
        Role role = roleRepository.findByName(rolename).orElse(null);
        if(role == null)
            throw new RoleNotFoundException("Role with name: " + rolename + " doesn't exist");
        roleRepository.delete(role);
    }
}
