package edu.co.Facade;

import edu.co.Errors.ControllException;
import edu.co.Model.User;
import edu.co.Services.UserServices;

import java.util.List;

public class UserFacade {
    private static UserFacade instance;

    public static UserFacade getInstance(){
        if(instance == null){
            instance = new UserFacade();
        }
        return instance;
    }

    public List<User> getAllUsers() throws ControllException.UserNotFound{
        return UserServices.ListUser();
    }

    public User getUser(String name) throws ControllException.UserNotFound{
        return UserServices.GetUser(name);
    }

    public User addUser(User newUser) throws ControllException.UserCreate{
        return UserServices.AddUser(newUser);
    }

    public boolean updateUser(User updateUser) throws ControllException.UserUpdate{
        return UserServices.UpdateUser(updateUser);
    }

    public boolean deleteUser(int Id) throws ControllException.UserDelete{
        return  UserServices.DeleteUser(Id);
    }


}
