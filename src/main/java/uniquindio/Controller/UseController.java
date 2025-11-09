package uniquindio.Controller;

import uniquindio.Errors.ControllException;
import uniquindio.Facade.UserFacade;
import uniquindio.Model.DTO.UserLoginDTO;
import uniquindio.Model.Client;


import java.util.List;

public class UseController {
    private final UserFacade userFacade = UserFacade.getInstance();


    public List<Client> GetUserAll() {
        try {
            return userFacade.getAllUsers();
        } catch (ControllException.UserNotFound e) {
            throw new RuntimeException(e);
        }

    }

    public Client AddUser(Client newClient) throws ControllException.UserCreate{
        try{
            return userFacade.addUser(newClient);
        }catch (Exception e){
            throw  new ControllException.UserCreate("Usuarios no registrado");
        }
    }

    public boolean UpdateUser(Client updateClient) throws ControllException.UserUpdate {
        try{
            return userFacade.updateUser(updateClient);
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

    public Client GetUser(String name) throws ControllException.UserNotFound{
        try{
            return userFacade.getUser(name);
        }catch (Exception e){
            throw new ControllException.UserNotFound("Usuario no encontrado");
        }
    }

    public Client GetUserById(int id) throws ControllException.UserNotFound{
        try{
            return userFacade.getUserById(id);
        }catch (Exception e){
            throw new ControllException.UserNotFound("Usuario no encontrado");
        }

    }

    public Client login(UserLoginDTO login) throws ControllException.ErrorServer {
        try {
            return userFacade.login(login);
        }catch (Exception e){
            throw new ControllException.ErrorServer("Error en el servidor"+ e.getMessage());
        }
    }
}
