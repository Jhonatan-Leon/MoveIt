package Java.co.Model.ClassBuilder;

import Java.co.Model.Client;
import Java.co.Model.Direccion;
import Java.co.Model.Envio;
import Java.co.Model.TipoDocumento;
import java.util.ArrayList;

public class UserBuilder {
    public String Id;
    public String NombreCompleto;
    public String email;
    public String Password;
    public String telefono = "";
    public TipoDocumento tipoDocumento;
    public String NumeroDocumento;
    public ArrayList<Direccion> listDireccion;
    public ArrayList<Envio> listEnvio;
    public String MetodoPagoFavorito ;
    public boolean estado = true;

    public UserBuilder(String Id, String NombreCompleto, String email, String password, TipoDocumento tipoDocumento, String NumeroDocumento) {
        this.Id = Id;
        this.NombreCompleto = NombreCompleto;
        this.email = email;
        this.Password = password;
        this.tipoDocumento = tipoDocumento;
        this.NumeroDocumento = NumeroDocumento;
        this.listEnvio = new ArrayList<>();
        this.listDireccion = new ArrayList<>();
    }

    public UserBuilder withMetodoPagoFavorito(String metodo){
        this.MetodoPagoFavorito = metodo;
        return this;
    }

    public ArrayList<Direccion> getListDireccion(){
        return listDireccion;
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

    public void withtelefono(String telefono){
        this.telefono = telefono;
    }

    public void withEstado(boolean estado){
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "UserBuilder {"
                + "Id=" + Id + ", " +
                "NombreCompleto=" + NombreCompleto +", " +
                "email=" + email + ", " +
                "telefono=" + telefono + ", " +
                "listDireccion=" + listDireccion + ", " +
                "listEnvio=" + listEnvio + ", " +
                "MetodoPagoFavorito=" + MetodoPagoFavorito + '}';
    }

    public Client build(){
        return new Client(this);
    }
}