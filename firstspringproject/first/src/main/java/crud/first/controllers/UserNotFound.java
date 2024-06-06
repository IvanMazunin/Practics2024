package crud.first.controllers;

public class UserNotFound extends Exception{
    public UserNotFound(String message) {
        super(message);
    }
}
