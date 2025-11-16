package uniquindio.Services;

import uniquindio.Errors.ControllException;
import uniquindio.Model.DTO.ClientSesionDTO;
import uniquindio.Model.DTO.UserLoginDTO;
import uniquindio.Model.DTO.UserPostLoginDTO;
import uniquindio.Model.Envio;
import uniquindio.Model.Gestion.GestionRepartidor;
import uniquindio.Model.Gestion.GestionUser;
import uniquindio.Model.Client;
import uniquindio.Model.Repartidor;
import uniquindio.Model.User;

import java.util.List;

public class UserServices {
    private static final GestionUser gestion = GestionUser.getInstance();
    private static final GestionRepartidor gestionRepartidor = GestionRepartidor.getInstance();

    private UserServices() {
    }
    public static List<Client> listUsers() throws ControllException.UserNotFound {
        try {
            List<Client> clients = gestion.getUsers();
            if (clients.isEmpty()) {
                throw new ControllException.UserNotFound("No hay usuarios registrados");
            }
            return clients;
        } catch (Exception e) {
            throw new ControllException.UserNotFound("Error al obtener usuarios: " + e.getMessage());
        }
    }
    public static Client getUser(String email) throws ControllException.UserNotFound {
        try {
            if (email == null || email.trim().isEmpty()) {
                throw new ControllException.UserNotFound("El email no puede estar vacío");
            }

            Client client = gestion.getUser(email);
            if (client == null) {
                throw new ControllException.UserNotFound("Usuario no encontrado con email: " + email);
            }
            return client;
        } catch (ControllException.UserNotFound e) {
            throw e;
        } catch (Exception e) {
            throw new ControllException.UserNotFound("Error al buscar usuario: " + e.getMessage());
        }
    }
    public static Client getUserById(int id) throws ControllException.UserNotFound {
        try {
            Client client = gestion.getUserById(String.valueOf(id));
            if (client == null) {
                throw new ControllException.UserNotFound("Usuario no encontrado con ID: " + id);
            }
            return client;
        } catch (ControllException.UserNotFound e) {
            throw e;
        } catch (Exception e) {
            throw new ControllException.UserNotFound("Error al buscar usuario: " + e.getMessage());
        }
    }
    public static Client addUser(Client newClient) throws ControllException.UserCreate {
        try {
            if (newClient == null) {
                throw new ControllException.UserCreate("El usuario no puede ser nulo");
            }

            Client existingClientById = gestion.getUserById(newClient.getId());
            if (existingClientById != null) {
                throw new ControllException.UserCreate("Ya existe un usuario con el ID: " + newClient.getId());
            }

            Client existingClientByEmail = gestion.getUser(newClient.getEmail());
            if (existingClientByEmail != null) {
                throw new ControllException.UserCreate("Ya existe un usuario con el email: " + newClient.getEmail());
            }

            return gestion.addUser(newClient);
        } catch (ControllException.UserCreate e) {
            throw e;
        } catch (Exception e) {
            throw new ControllException.UserCreate("Error al registrar usuario: " + e.getMessage());
        }
    }
    public static boolean updateUser(Client client) throws ControllException.UserUpdate {
        try {
            if (client == null) {
                throw new ControllException.UserUpdate("El usuario no puede ser nulo");
            }

            Client existingClient = gestion.getUserById(client.getId());
            if (existingClient == null) {
                throw new ControllException.UserUpdate("Usuario no encontrado con ID: " + client.getId());
            }

            Client updatedClient = gestion.updateUser(client.getId(), client);
            return updatedClient != null;
        } catch (ControllException.UserUpdate e) {
            throw e;
        } catch (Exception e) {
            throw new ControllException.UserUpdate("Error al actualizar usuario: " + e.getMessage());
        }
    }
    public static boolean deleteUser(int id) throws ControllException.UserDelete {
        try {
            Client existingClient = gestion.getUserById(String.valueOf(id));
            if (existingClient == null) {
                throw new ControllException.UserDelete("Usuario no encontrado con ID: " + id);
            }

            return gestion.deleteUser(String.valueOf(id));
        } catch (ControllException.UserDelete e) {
            throw e;
        } catch (Exception e) {
            throw new ControllException.UserDelete("Error al eliminar usuario: " + e.getMessage());
        }
    }

    public static User login(UserLoginDTO userLoginDTO) throws ControllException.UserNotFound ,ControllException.ErrorServer {
        try {

            if (userLoginDTO == null)
                throw new ControllException.UserNotFound("Datos inválidos");

            // 1. Buscar en Clientes
            Client client = gestion.getUserById(userLoginDTO.getId());
            if (client != null) {
                if (client.getPassword().equals(userLoginDTO.getPassword())) {
                    return client;
                }
                throw new ControllException.UserNotFound("Contraseña incorrecta");
            }

            // 2. Buscar en Repartidores
            Repartidor repartidor = gestionRepartidor.getRepartidor(userLoginDTO.getId());
            if (repartidor != null) {
                if (repartidor.getPassword().equals(userLoginDTO.getPassword())) {
                    return repartidor;
                }
                throw new ControllException.UserNotFound("Contraseña incorrecta");
            }

            // 3. Buscar en Admin PENDIENTE

            // Si no se encontró en ninguna lista:
            throw new ControllException.UserNotFound("Usuario no encontrado");

        } catch (ControllException.UserNotFound e) {
            throw e; 
        } catch (Exception e) {
            throw new ControllException.ErrorServer("Error en el servidor");
        }
    }

//    public static String obtenerMensajeRastreo (ClientSesionDTO user) {
//        String mensaje = "";
//        Envio envio = EnvioServices.getEnvio()
//        if () {
//        }
//    }

}