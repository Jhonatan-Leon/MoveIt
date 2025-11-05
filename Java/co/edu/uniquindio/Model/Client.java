
package Java.co.Model;

import Java.co.Model.ClassBuilder.UserBuilder;

import java.util.ArrayList;


public class Client extends User {
    private ArrayList<Direccion> listDireccion;
    private ArrayList<Envio> listEnvio;
    private String metodoPagoFavorito;

    public Client(UserBuilder builder) {
        super(builder.Id, builder.NombreCompleto, builder.email, builder.Password,
                builder.telefono, builder.tipoDocumento, builder.NumeroDocumento, builder.estado);
        this.listDireccion = builder.listDireccion;
        this.listEnvio = builder.listEnvio;
        this.metodoPagoFavorito = builder.MetodoPagoFavorito;
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
}