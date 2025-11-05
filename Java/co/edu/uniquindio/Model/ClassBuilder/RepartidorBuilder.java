package Java.co.Model.ClassBuilder;

import Java.co.Model.TipoDocumento;

public class RepartidorBuilder {
    public String id;
    public String nombreCompleto;
    public String email;
    public String password;

    public String telefono = "";
    public TipoDocumento tipoDocumento;
    public String numeroDocumento = "";
    public boolean estado = true;
    public String zonaCobertura = "";

    public RepartidorBuilder(String id, String nombreCompleto, String email, String password) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.password = password;
    }


    public RepartidorBuilder withtelefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public RepartidorBuilder withtipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
        return this;
    }

    public RepartidorBuilder withnumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
        return this;
    }

    public RepartidorBuilder withestado(boolean estado) {
        this.estado = estado;
        return this;
    }

    public RepartidorBuilder withzonaCobertura(String zonaCobertura) {
        this.zonaCobertura = zonaCobertura;
        return this;
    }

}


