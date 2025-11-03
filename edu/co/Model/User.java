
package edu.co.Model;

import edu.co.Model.ClassBuilder.UserBuilder;

import java.util.ArrayList;

public class User {
    private int id;
    private String nombreCompleto;
    private String email;
    private String password;
    private String telefono;
    private ArrayList<Direccion> listDireccion;
    private ArrayList<Envio> listEnvio;
    private String metodoPagoFavorito;
    private boolean estado;

    public User(UserBuilder builder) {
        this.id = builder.Id;
        this.nombreCompleto = builder.NombreCompleto;
        this.email = builder.email;
        this.password = builder.password;
        this.telefono = builder.telefono;
        this.listDireccion = builder.listDireccion;
        this.listEnvio = builder.listEnvio;
        this.metodoPagoFavorito = builder.MetodoPagoFavorito;
        this.estado = builder.estado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Direccion> getListDireccion() {
        return listDireccion;
    }

    public void setListDireccion(ArrayList<Direccion> listDireccion) {
        this.listDireccion = listDireccion;
    }

    public ArrayList<Envio> getListEnvio() {
        return listEnvio;
    }

    public void setListEnvio(ArrayList<Envio> listEnvio) {
        this.listEnvio = listEnvio;
    }

    public String getMetodoPagoFavorito() {
        return metodoPagoFavorito;
    }

    public void setMetodoPagoFavorito(String metodoPagoFavorito) {
        this.metodoPagoFavorito = metodoPagoFavorito;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
