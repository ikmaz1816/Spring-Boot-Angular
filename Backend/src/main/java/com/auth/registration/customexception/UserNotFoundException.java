package com.auth.registration.customexception;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(String message)
    {
        super(message);
    }
}
