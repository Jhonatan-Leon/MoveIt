package edu.co.Errors;

import edu.co.Model.AplicationException;

public class ControllException {
    private ControllException(){
    }

    public static class ErrorServer extends AplicationException{
        public ErrorServer(String message){
            super(message);
        }
    }

    public static class UserNotFound extends AplicationException {
        public UserNotFound(String message){
            super(message);
        }
    }

    public static class UserUpdate extends AplicationException {
        public UserUpdate(String message){
            super(message);
        }
    }

    public static class UserDelete extends AplicationException {
        public UserDelete(String message){
            super(message);
        }
    }

    public static class UserCreate extends AplicationException {
        public UserCreate(String message){
            super(message);
        }
    }

}
