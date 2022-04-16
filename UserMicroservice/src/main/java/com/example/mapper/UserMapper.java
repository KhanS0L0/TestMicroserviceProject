package com.example.mapper;

import com.example.dto.RoleDTO;
import com.example.dto.UserDTO;
import com.example.entity.Role;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    private final RoleMapper roleMapper;

    @Autowired
    public UserMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public User dtoToEntity(UserDTO userDTO){
        User entity = new User();
        entity.setUsername(userDTO.getUsername());
        entity.setPassword(userDTO.getPassword());
        entity.setEmail(userDTO.getEmail());
        return entity;
    }

    public UserDTO entityToDto(User user){
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        this.convertRoleForUser(user, dto);
        return dto;
    }

    private void convertRoleForUser(User user, UserDTO userDTO){
        List<Role> roles = user.getRoles();
        List<RoleDTO> dtoList = new ArrayList<>();
        roles.forEach(role -> dtoList.add(roleMapper.entityToDto(role)));
        userDTO.setRoles(dtoList);
    }
}
