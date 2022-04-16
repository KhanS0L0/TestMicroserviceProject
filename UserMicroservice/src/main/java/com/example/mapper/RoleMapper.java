package com.example.mapper;

import com.example.dto.RoleDTO;
import com.example.dto.UserDTO;
import com.example.entity.Role;
import com.example.entity.User;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public Role dtoToEntity(RoleDTO roleDTO){
        Role entity = new Role();
        entity.setName(roleDTO.getName());
        return entity;
    }

    public RoleDTO entityToDto(Role role){
        RoleDTO dto = new RoleDTO();
        dto.setName(role.getName());
        return dto;
    }
}
