package com.example.repository;

import com.example.dto.RoleDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleRepository{

    private final Type listType = new TypeToken<ArrayList<RoleDTO>>(){}.getType();
    private final Type dtoType = new TypeToken<RoleDTO>(){}.getType();

    private final RestTemplate restTemplate;
    private final Gson gson;

    @Autowired
    public RoleRepository(RestTemplate restTemplate, Gson gson) {
        this.restTemplate = restTemplate;
        this.gson = gson;
    }

    public List<RoleDTO> listAllRoles(){
        String result = restTemplate.getForEntity("http://localhost:8877/api/v1/roles/all", String.class).getBody();
        return gson.fromJson(result, listType);
    }

    public RoleDTO getRoleByRoleName(String roleName){
        String result = restTemplate.getForEntity("http://localhost:8877/api/v1/roles/" + roleName, String.class).getBody();
        if(result != null && !result.isBlank() && !result.isEmpty())
            return gson.fromJson(result, dtoType);
        return null;
    }

    public RoleDTO saveRole(RoleDTO dto){
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8877/api/v1/roles/create", new HttpEntity<>(dto), String.class);
        if(response.getStatusCode() == HttpStatus.CREATED) {
            String result = response.getBody();
            return gson.fromJson(result, dtoType);
        }
        return null;
    }

    public RoleDTO updateRole(String roleName, RoleDTO dto){
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8877/api/v1/roles/update/" + roleName,
                HttpMethod.PUT,
                new HttpEntity<>(dto),
                String.class);

        if(response.getStatusCode() == HttpStatus.OK){
            String result = response.getBody();
            return gson.fromJson(result, dtoType);
        }
        return null;
    }

    public String deleteRole(String roleName){
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8877/api/v1/roles/delete/" + roleName,
                HttpMethod.DELETE,
                null,
                String.class);
        if(response.getStatusCode() == HttpStatus.OK)
            return response.getBody();
        return "Something went wrong";
    }
}