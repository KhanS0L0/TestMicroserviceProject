package com.example.exception;

public class RoleAlreadyExistException extends Exception{

    public RoleAlreadyExistException() {
    }

    public RoleAlreadyExistException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
