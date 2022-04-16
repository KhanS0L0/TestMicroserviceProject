package com.example.exception;

import javax.persistence.EntityNotFoundException;

public class RoleNotFoundException extends EntityNotFoundException {
    public RoleNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
