package edu.co.Facade;

import edu.co.Errors.ControllException;
import edu.co.Model.DTO.UserDTO;
import edu.co.Model.User;
import edu.co.Services.UserServices;

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

    public List<User> getAllUsers() throws ControllException.UserNotFound {
        return UserServices.listUsers();
    }
    public User getUser(String email) throws ControllException.UserNotFound {
        return UserServices.getUser(email);
    }
    public User getUserById(int id) throws ControllException.UserNotFound {
        return UserServices.getUserById(id);
    }
    public User addUser(User newUser) throws ControllException.UserCreate {
        return UserServices.addUser(newUser);
    }
    public boolean updateUser(User updateUser) throws ControllException.UserUpdate {
        return UserServices.updateUser(updateUser);
    }
    public boolean deleteUser(int id) throws ControllException.UserDelete {
        return UserServices.deleteUser(id);
    }
}