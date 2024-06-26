package dev.jaffer.userservice.exceptions;

public class UserDoesNotExist extends Exception{

    public UserDoesNotExist(String message){
        super(message);
    }
}
