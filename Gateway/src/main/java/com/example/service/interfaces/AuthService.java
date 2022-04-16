package com.example.service.interfaces;

import com.example.dto.AuthenticationDTO;

import java.util.Map;

public interface AuthService {
    Map<String, String> signIn(AuthenticationDTO authDTO);
}
