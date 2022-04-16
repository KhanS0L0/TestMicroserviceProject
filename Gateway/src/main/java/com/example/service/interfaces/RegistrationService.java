package com.example.service.interfaces;

import com.example.dto.RegistrationDTO;
import com.example.dto.UserDTO;

public interface RegistrationService {
    UserDTO signUp(RegistrationDTO registrationDTO);
}
