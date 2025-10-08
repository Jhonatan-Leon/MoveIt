package edu.co.Model.ClassBuilder;

import edu.co.Model.Direccion;
import edu.co.Model.Envio;

import java.util.ArrayList;

public class UserBuilder {
    public int Id;
    public String NombreCompleto;
    public String email;
    public String telefono;
    public ArrayList<Direccion> listDireccion;
    public ArrayList<Envio> listEnvio;
    public String MetodoPagoFavorito = "";

    public UserBuilder(int Id, String NombreCompleto, String email, String telefono) {
        this.Id = Id;
        this.NombreCompleto = NombreCompleto;
        this.email = email;
        this.telefono = telefono;
        this.listEnvio = new ArrayList<>();
    }

    public UserBuilder withMetodoPagoFavorito(String metodo){
        this.MetodoPagoFavorito = metodo;
        return this;
    }

    public ArrayList<Direccion> getListDireccion(Direccion direccion){
        return  listDireccion;
    }

    public void AgregarDireccion(Direccion direccion){
        listDireccion.add(direccion);
    }

    public ArrayList<Envio> getEnvio(){
        return listEnvio;
    }

    public void AgregarEnvio(Envio envio){
        listEnvio.add(envio);
    }


    @Override
    public String toString() {
        return "UserBuilder {"
                + "Id=" + Id + ", " +
                "NombreCompleto=" + NombreCompleto +", }" +
                "email=" + email + ", " +
                "telefono=" + telefono + ", " +
                "listDireccion=" + listDireccion + ", " +
                "listEnvio=" + listEnvio + ", " +
                "MetodoPagoFavorito=" + MetodoPagoFavorito + '}';
    }
}
