package dev.jaffer.userservice.exceptions;

public class PasswordNotMatchedException extends Exception{

    public PasswordNotMatchedException(String message){
        super(message);
    }
}
