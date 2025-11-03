package edu.co.Model.Gestion;

import edu.co.Model.User;

import java.util.ArrayList;
import java.util.List;

public class GestionUser {
    private static final List<User> listUser = new ArrayList<>();
    private static GestionUser instance;

    private GestionUser() {
    }
    public static GestionUser getInstance() {
        if (instance == null) {
            instance = new GestionUser();
        }
        return instance;
    }

    public User addUser(User usuario) {
        listUser.add(usuario);
        return usuario;
    }

    public List<User> getUsers() {
        return new ArrayList<>(listUser);
    }

    public User getUser(String email) {
        for (User u : listUser) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }

    public User getUserById(int id) {
        for (User u : listUser) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    public User updateUser(int id, User user) {
        for (User u : listUser) {
            if (u.getId() == id) {
                u.setNombreCompleto(user.getNombreCompleto());
                u.setEmail(user.getEmail());
                u.setTelefono(user.getTelefono());
                u.setListDireccion(user.getListDireccion());
                u.setListEnvio(user.getListEnvio());
                u.setMetodoPagoFavorito(user.getMetodoPagoFavorito());
                u.setEstado(user.isEstado());
                return u;
            }
        }
        return null;
    }

    public boolean deleteUser(int id) {
        for (User u : listUser) {
            if (u.getId() == id) {
                listUser.remove(u);
                return true;
            }
        }
        return false;
    }
}
