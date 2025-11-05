package Java.co.Facade;

import Java.co.Errors.ControllException;
import Java.co.Model.DTO.UserLoginDTO;
import Java.co.Model.Client;
import Java.co.Services.UserServices;

import java.util.List;

public class UserFacade {
    private static UserFacade instance;

    private UserFacade() {
    }
    public static UserFacade getInstance() {
        if (instance == null) {
            instance = new UserFacade();
        }
        return instance;
    }

    public List<Client> getAllUsers() throws ControllException.UserNotFound {
        return UserServices.listUsers();
    }
    public Client getUser(String email) throws ControllException.UserNotFound {
        return UserServices.getUser(email);
    }
    public Client getUserById(int id) throws ControllException.UserNotFound {
        return UserServices.getUserById(id);
    }
    public Client addUser(Client newClient) throws ControllException.UserCreate {
        return UserServices.addUser(newClient);
    }
    public boolean updateUser(Client updateClient) throws ControllException.UserUpdate {
        return UserServices.updateUser(updateClient);
    }
    public boolean deleteUser(int id) throws ControllException.UserDelete {
        return UserServices.deleteUser(id);
    }

    public Client login(UserLoginDTO login) throws ControllException.ErrorServer {
        return UserServices.login(login);
    }
}