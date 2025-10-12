package edu.co.Model.Gestion;

import edu.co.Model.User;

import java.util.ArrayList;
import java.util.List;

public class GestionUser {
    private static final List<User> listUser = new ArrayList<>();
    private static GestionUser instance;

    public static GestionUser getInstance(){
        if(instance == null){
            instance = new GestionUser();
        }
        return instance;
    }

    public User AddUser(User usuario){
        listUser.add(usuario);
        return  usuario;
    }

    public static List<User> GetUsers(){
        return new ArrayList<>(listUser);
    }

    public static User UpdateUser(int Id, User user) {
        for(User u : listUser){
            if(u.getId() == Id){
                u.setNombreCompleto(user.getNombreCompleto());
                u.setEmail(user.getEmail());
                u.setTelefono(user.getTelefono());
                u.setlistDireccion(user.getDireccion());
                return u;
            }
        }

        return null;
    }

    public static User GetUser(String Id){
        for(User u : listUser){
            if(u.getEmail().equals(Id)){
                return u;
            }
        }
        return null;
    }

    public static boolean DeleteUser(int Id){
        for(User u : listUser){
            if(u.getId() == (Id)){
                listUser.remove(u);
                return  true;
            }
        }
        return false;
    }


}
