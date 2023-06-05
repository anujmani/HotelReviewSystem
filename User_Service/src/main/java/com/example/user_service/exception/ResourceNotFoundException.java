package com.example.user_service.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
        super("Resource not found on server!!");

    }
    public ResourceNotFoundException(String message){
        super(message);
    }
}
