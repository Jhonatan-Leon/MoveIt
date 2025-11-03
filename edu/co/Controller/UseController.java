package edu.co.Controller;

import edu.co.Errors.ControllException;
import edu.co.Facade.UserFacade;
import edu.co.Model.DTO.UserLoginDTO;
import edu.co.Model.User;
import edu.co.Model.DTO.UserDTO;



import java.util.List;

public class UseController {
    private final UserFacade userFacade = UserFacade.getInstance();


    public List<User> GetUserAll() {
        try {
            return userFacade.getAllUsers();
        } catch (ControllException.UserNotFound e) {
            throw new RuntimeException(e);
        }

    }

    public User AddUser(User newUser) throws ControllException.UserCreate{
        try{
            return userFacade.addUser(newUser);
        }catch (Exception e){
            throw  new ControllException.UserCreate("Usuarios no registrado");
        }
    }

    public boolean UpdateUser(User updateUser) throws ControllException.UserUpdate {
        try{
            return userFacade.updateUser(updateUser);
        }catch (Exception e){
            throw  new ControllException.UserUpdate("Error actualizar usuario");
        }
    }

    public boolean DeleteUser(int Id){
        try{
            return userFacade.deleteUser(Id);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public User GetUser(String name) throws ControllException.UserNotFound{
        try{
            return userFacade.getUser(name);
        }catch (Exception e){
            throw new ControllException.UserNotFound("Usuario no encontrado");
        }
    }

    public User GetUserById(int id) throws ControllException.UserNotFound{
        try{
            return userFacade.getUserById(id);
        }catch (Exception e){
            throw new ControllException.UserNotFound("Usuario no encontrado");
        }

    }

    public User login(UserLoginDTO login) throws ControllException.ErrorServer {
        try {
            return userFacade.login(login);
        }catch (Exception e){
            throw new ControllException.ErrorServer("Error en el servidor"+ e.getMessage());
        }
    }
}
