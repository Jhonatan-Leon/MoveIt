package edu.co.Services;
import edu.co.Errors.ControllException;
import edu.co.Model.Gestion.GestionUser;
import edu.co.Model.User;

import java.util.List;
import java.util.Objects;

public class UserServices {

    //Instanciar clase para gestionar usuarios
    private static final GestionUser gestion = GestionUser.getInstance();

    //Logica para Usuarios;
    public static List<User> ListUser() throws ControllException.UserNotFound {
         try {
             return GestionUser.GetUsers();
         }catch(Exception e){
             throw new ControllException.UserNotFound("Usuarios no encontrados");
         }
    }

    public static User AddUser(User newUser) throws ControllException.UserCreate {
         try{
             List<User> findUser = GestionUser.GetUsers();

             for(User s: findUser){
                 if(s.getId() == newUser.getId() && s.getEmail().equals(newUser.getEmail())){
                     return null;
                 }
             }

             return gestion.AddUser(newUser);
         }catch (Exception e){
             throw  new ControllException.UserCreate ("Usuarios no registrado");
         }
     }
     public static boolean UpdateUser(User usuarios) throws ControllException.UserUpdate {
         try{
             List<User> users = GestionUser.GetUsers();
             for(User s: users){
                 if(s.getId() == usuarios.getId()) {
                     User updateUser = GestionUser.UpdateUser(usuarios.getId(), usuarios);
                     if(updateUser == null) {
                         return true;
                     }
                 }
             }

             return false;
         }catch (Exception e){
             throw new ControllException.UserUpdate("Error actualizar usuario");
         }
     }

    public static boolean DeleteUser(int Id) throws ControllException.UserDelete {
         try{
             return GestionUser.DeleteUser(Id);
         }catch (Exception e){
             throw  new ControllException.UserDelete("Error actualizar usuario");
         }
     }

    public static User GetUser(String nombre) throws ControllException.UserNotFound {
         try{
             return GestionUser.GetUser(nombre);
         }catch (Exception e){
             throw new ControllException.UserNotFound("Usuario no encontrado");
         }
     }
}
