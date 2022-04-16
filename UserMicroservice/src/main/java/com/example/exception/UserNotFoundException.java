package com.example.exception;

import javax.persistence.EntityNotFoundException;
import java.util.function.Supplier;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
