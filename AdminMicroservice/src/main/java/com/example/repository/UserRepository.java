package com.example.repository;

import com.example.dto.RoleDTO;
import com.example.dto.UserDTO;
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
public class UserRepository {

    private final Type listType = new TypeToken<ArrayList<UserDTO>>(){}.getType();
    private final Type dtoType = new TypeToken<UserDTO>(){}.getType();

    private final RestTemplate restTemplate;
    private final Gson gson;

    @Autowired
    public UserRepository(RestTemplate restTemplate, Gson gson) {
        this.restTemplate = restTemplate;
        this.gson = gson;
    }

    public List<UserDTO> listAllUsers(){
        String result = restTemplate.getForEntity("http://localhost:8877/api/v1/users/all", String.class).getBody();
        return gson.fromJson(result, listType);
    }

    public UserDTO getUserByUsername(String username){
        String result = restTemplate.getForEntity("http://localhost:8877/api/v1/users/" + username, String.class).getBody();
        return gson.fromJson(result, dtoType);
    }

    public UserDTO updateUser(String username, UserDTO user){
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8877/api/v1/users/update/" + username,
                    HttpMethod.PUT,
                    new HttpEntity<>(user),
                    String.class);

        if(response.getStatusCode() == HttpStatus.OK){
            String result = response.getBody();
            return gson.fromJson(result, dtoType);
        }
        return null;
    }

    public String deleteUser(String username){
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8877/api/v1/users/delete/" + username,
                HttpMethod.DELETE,
                null,
                String.class);
        if(response.getStatusCode() == HttpStatus.OK)
            return response.getBody();
        return "Something went wrong";
    }

    public UserDTO setRoleForUser(String username, RoleDTO dto){
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8877/api/v1/users/update/role/" + username,
                HttpMethod.PUT,
                new HttpEntity<>(dto),
                String.class);
        if(response.getStatusCode() == HttpStatus.OK){
            String result = response.getBody();
            return gson.fromJson(result, dtoType);
        }
        return null;
    }

    public UserDTO deleteRoleForUser(String username, RoleDTO dto){
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8877/api/v1/users/delete/role/" + username,
                HttpMethod.DELETE,
                new HttpEntity<>(dto),
                String.class);
        if(response.getStatusCode() == HttpStatus.OK){
            String result = response.getBody();
            return gson.fromJson(result, dtoType);
        }
        return null;
    }
}
