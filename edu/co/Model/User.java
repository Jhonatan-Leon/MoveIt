package edu.co.Model;

import edu.co.Model.ClassBuilder.UserBuilder;

import java.util.ArrayList;

public class User {
    private int Id;
    private String NombreCompleto;
    private String email;
    private String telefono;
    private ArrayList<Direccion> listDireccion;
    private ArrayList<Envio> listEnvio;
    private String MetodoPagoFavorito;

    public User(UserBuilder builder) {
        this.Id = builder.Id;
        this.NombreCompleto = builder.NombreCompleto;
        this.email = builder.email;
        this.telefono = builder.telefono;
        this.listDireccion = builder.listDireccion;
        this.listEnvio = builder.listEnvio;
        this.MetodoPagoFavorito = builder.MetodoPagoFavorito;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombreCompleto() {
        return NombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        NombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public ArrayList<Direccion> getDireccion() {
        return listDireccion;
    }

    public void setlistDireccion(ArrayList<Direccion> listDireccion) {
        this.listDireccion = listDireccion;
    }

    public ArrayList<Envio> getEnvio(){
        return  listEnvio;
    }

    public void setlistEnvio(ArrayList<Envio> listEnvio){
        this.listEnvio = listEnvio;
    }

    public String getMetodoPagoFavorito() {
        return MetodoPagoFavorito;
    }

    public void setMetodoPagoFavorito(String metodoPagoFavorito) {
        MetodoPagoFavorito = metodoPagoFavorito;
    }
}
