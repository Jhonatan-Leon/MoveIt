package uniquindio.Model.Gestion;

import uniquindio.Helper.JsonLoader;
import uniquindio.Model.Client;

import java.util.ArrayList;
import java.util.List;

public class GestionUser {
    private static List<Client> LIST_CLIENT = new ArrayList<>();
    private static GestionUser instance;

    private GestionUser() {
        LIST_CLIENT = JsonLoader.CargarProductos("/DatosQuemados/Cliente.json",  Client.class);
    }
    public static GestionUser getInstance() {
        if (instance == null) {
            instance = new GestionUser();
        }
        return instance;
    }

    public Client addUser(Client usuario) {
        LIST_CLIENT.add(usuario);
        return usuario;
    }

    public List<Client> getUsers() {
        return new ArrayList<>(LIST_CLIENT);
    }

    public Client getUser(String email) {
        for (Client u : LIST_CLIENT) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }

    public Client getUserById(String id) {
        for (Client u : LIST_CLIENT) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }

    public Client updateUser(String id, Client client) {
        for (Client u : LIST_CLIENT) {
            if (u.getId().equals(id)) {
                u.setNombreCompleto(client.getNombreCompleto());
                u.setEmail(client.getEmail());
                u.setTelefono(client.getTelefono());
                u.setListDireccion(client.getListDireccion());
                u.setListEnvio(client.getListEnvio());
                u.setMetodoPagoFavorito(client.getMetodoPagoFavorito());
                u.setEstado(client.isEstado());
                return u;
            }
        }
        return null;
    }

    public boolean deleteUser(String id) {
        for (Client u : LIST_CLIENT) {
            if (u.getId().equals(id)) {
                LIST_CLIENT.remove(u);
                return true;
            }
        }
        return false;
    }
}
