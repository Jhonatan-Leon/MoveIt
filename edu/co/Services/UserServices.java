package edu.co.Services;

import edu.co.Errors.ControllException;
import edu.co.Model.DTO.UserLoginDTO;
import edu.co.Model.Gestion.GestionUser;
import edu.co.Model.User;
import edu.co.Utils.LoginDTO;

import java.util.List;

public class UserServices {
    private static final GestionUser gestion = GestionUser.getInstance();

    private UserServices() {
    }
    public static List<User> listUsers() throws ControllException.UserNotFound {
        try {
            List<User> users = gestion.getUsers();
            if (users.isEmpty()) {
                throw new ControllException.UserNotFound("No hay usuarios registrados");
            }
            return users;
        } catch (Exception e) {
            throw new ControllException.UserNotFound("Error al obtener usuarios: " + e.getMessage());
        }
    }
    public static User getUser(String email) throws ControllException.UserNotFound {
        try {
            if (email == null || email.trim().isEmpty()) {
                throw new ControllException.UserNotFound("El email no puede estar vacío");
            }

            User user = gestion.getUser(email);
            if (user == null) {
                throw new ControllException.UserNotFound("Usuario no encontrado con email: " + email);
            }
            return user;
        } catch (ControllException.UserNotFound e) {
            throw e;
        } catch (Exception e) {
            throw new ControllException.UserNotFound("Error al buscar usuario: " + e.getMessage());
        }
    }
    public static User getUserById(int id) throws ControllException.UserNotFound {
        try {
            User user = gestion.getUserById(id);
            if (user == null) {
                throw new ControllException.UserNotFound("Usuario no encontrado con ID: " + id);
            }
            return user;
        } catch (ControllException.UserNotFound e) {
            throw e;
        } catch (Exception e) {
            throw new ControllException.UserNotFound("Error al buscar usuario: " + e.getMessage());
        }
    }
    public static User addUser(User newUser) throws ControllException.UserCreate {
        try {
            if (newUser == null) {
                throw new ControllException.UserCreate("El usuario no puede ser nulo");
            }

            User existingUserById = gestion.getUserById(newUser.getId());
            if (existingUserById != null) {
                throw new ControllException.UserCreate("Ya existe un usuario con el ID: " + newUser.getId());
            }

            User existingUserByEmail = gestion.getUser(newUser.getEmail());
            if (existingUserByEmail != null) {
                throw new ControllException.UserCreate("Ya existe un usuario con el email: " + newUser.getEmail());
            }

            return gestion.addUser(newUser);
        } catch (ControllException.UserCreate e) {
            throw e;
        } catch (Exception e) {
            throw new ControllException.UserCreate("Error al registrar usuario: " + e.getMessage());
        }
    }
    public static boolean updateUser(User user) throws ControllException.UserUpdate {
        try {
            if (user == null) {
                throw new ControllException.UserUpdate("El usuario no puede ser nulo");
            }

            User existingUser = gestion.getUserById(user.getId());
            if (existingUser == null) {
                throw new ControllException.UserUpdate("Usuario no encontrado con ID: " + user.getId());
            }

            User updatedUser = gestion.updateUser(user.getId(), user);
            return updatedUser != null;
        } catch (ControllException.UserUpdate e) {
            throw e;
        } catch (Exception e) {
            throw new ControllException.UserUpdate("Error al actualizar usuario: " + e.getMessage());
        }
    }
    public static boolean deleteUser(int id) throws ControllException.UserDelete {
        try {
            User existingUser = gestion.getUserById(id);
            if (existingUser == null) {
                throw new ControllException.UserDelete("Usuario no encontrado con ID: " + id);
            }

            return gestion.deleteUser(id);
        } catch (ControllException.UserDelete e) {
            throw e;
        } catch (Exception e) {
            throw new ControllException.UserDelete("Error al eliminar usuario: " + e.getMessage());
        }
    }

    public static User login(UserLoginDTO userLoginDTO) throws ControllException.ErrorServer {
        try {
            if(userLoginDTO == null) throw new ControllException.UserNotFound("Usuario no encontrado");

            User user = gestion.getUser(userLoginDTO.getEmail());
            if(user == null) throw new ControllException.UserNotFound("Usuario no encontrado");

            if(user.getPassword().equals(userLoginDTO.getPassword())) return user;
            else throw new ControllException.UserNotFound("Error en los datos proporcionados");
        }catch (Exception e){
            throw new ControllException.ErrorServer("Usuario no encontrado");
        }
    }
}