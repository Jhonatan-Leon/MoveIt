package edu.co.Model.DTO;

import edu.co.Model.Direccion;

public class UserDTO {
    private int Id;
    private String NombreCompleto;
    private String email;
    private String password;
    private String telefono;
    private Direccion direccion;
    private boolean estado;

    public UserDTO(int Id, String NombreCompleto, String email, String password, String telefono, Direccion direccion, boolean estado) {
        this.Id = Id;
        this.NombreCompleto = NombreCompleto;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.direccion = direccion;
        this.estado = estado;
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

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString(){
        return Id + "(" +NombreCompleto+ ") " +  "("+email+")"+ "("+password+")" +"("+telefono+")"+"("+direccion+")"+"("+tipoDireccion+")"+"("+estado+")";
    }

}
