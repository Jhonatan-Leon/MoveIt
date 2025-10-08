package edu.co.Controller;

import edu.co.Errors.ControllException;
import edu.co.Model.User;
import edu.co.Services.UserServices;


import java.util.List;

public class UseController {
    public List<User> GetUserAll() {
        List<User> Users = null;
        try {
           return UserServices.ListUser();
        } catch (ControllException.UserNotFound e) {
            throw new RuntimeException(e);
        }

    }

    public User AddUser(User newUser) throws ControllException.UserCreate{
        try{
            return UserServices.AddUser(newUser);
        }catch (Exception e){
            throw  new ControllException.UserCreate("Usuarios no registrado");
        }
    }

    public boolean UpdateUser(User updateUser) throws ControllException.UserUpdate {
        try{
            return UserServices.UpdateUser(updateUser);
        }catch (Exception e){
            throw  new ControllException.UserUpdate("Error actualizar usuario");
        }
    }

    public boolean DeleteUser(String Id){
        try{
            return UserServices.DeleteUser(Id);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public User GetUser(String name) throws ControllException.UserNotFound{
        try{
            return UserServices.GetUser(name);
        }catch (Exception e){
            throw new ControllException.UserNotFound("Usuario no encontrado");
        }
    }
}
