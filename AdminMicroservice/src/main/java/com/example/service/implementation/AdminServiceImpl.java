package com.example.service.implementation;

import com.example.dto.RoleDTO;
import com.example.dto.UserDTO;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.service.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private static final List<RoleDTO> TABOO_ROLES = new ArrayList<>();

    static {
        TABOO_ROLES.add(new RoleDTO("ROLE_ADMIN"));
    }

    @Autowired
    public AdminServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.listAllUsers();
    }

    @Override
    public UserDTO getUserByUsername(String username){
        return userRepository.getUserByUsername(username);
    }

    @Override
    public UserDTO updateUserProps(String username, UserDTO dto) {
        UserDTO user = userRepository.getUserByUsername(username);
        if(!user.containTabooRole(TABOO_ROLES))
            return userRepository.updateUser(username, dto);
        return null;
    }

    @Override
    public String deleteUser(String username) {
        UserDTO user = userRepository.getUserByUsername(username);
        if(!user.containTabooRole(TABOO_ROLES))
            return userRepository.deleteUser(username);
        return "Oops, something went wrong";
    }

    @Override
    public UserDTO setUserRole(String username, RoleDTO dto) {
        UserDTO user = userRepository.getUserByUsername(username);
        if(!user.containTabooRole(TABOO_ROLES) && !TABOO_ROLES.contains(dto))
            return userRepository.setRoleForUser(username, dto);
        return null;
    }

    @Override
    public UserDTO deleteUserRole(String username, RoleDTO dto) {
        UserDTO user = userRepository.getUserByUsername(username);
        if(!user.containTabooRole(TABOO_ROLES) && !TABOO_ROLES.contains(dto))
            return userRepository.deleteRoleForUser(username, dto);
        return null;
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        return roleRepository.listAllRoles();
    }

    @Override
    public RoleDTO getRoleByName(String roleName) {
        return roleRepository.getRoleByRoleName(roleName);
    }

    @Override
    public RoleDTO saveRole(RoleDTO dto) {
        return roleRepository.saveRole(dto);
    }

    @Override
    public RoleDTO updateRole(String roleName, RoleDTO dto) {
        return roleRepository.updateRole(roleName, dto);
    }

    @Override
    public String deleteRole(String roleName) {
        if(!TABOO_ROLES.contains(new RoleDTO(roleName)))
            return roleRepository.deleteRole(roleName);
        return null;
    }
}
