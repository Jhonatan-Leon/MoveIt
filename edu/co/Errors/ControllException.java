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

    public static class PackageNotFound extends AplicationException {
        public PackageNotFound(String message, Throwable cause){
            super(message);
        }
    }

    public static class PackageUpdate extends AplicationException {
        public PackageUpdate(String message){
            super(message);
        }
    }

    public static class PackageDelete extends AplicationException {
        public PackageDelete(String message){
            super(message);
        }
    }
    public static class PackageCreate extends AplicationException {
        public PackageCreate(String message){
            super(message);
        }
    }

}
