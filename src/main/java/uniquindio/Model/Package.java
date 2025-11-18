package uniquindio.Model;

import uniquindio.Model.ClassBuilder.PaqueteBuilder;

public class Package {
    private String IdPaquete;
    private float peso;
    private float alto;
    private float ancho;
    private float largo;

    public Package(PaqueteBuilder builder) {
        this.IdPaquete = builder.IdPaquete;
        this.peso = builder.peso;
        this.alto = builder.alto;
        this.ancho = builder.ancho;
        this.largo = builder.largo;
    }

    public String getIdPaquete() {
        return IdPaquete;
    }

    public void setIdPaquete(String idPaquete) {
        IdPaquete = idPaquete;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAlto() {
        return alto;
    }

    public void setAlto(float alto) {
        this.alto = alto;
    }

    public float getAncho() {
        return ancho;
    }

    public void setAncho(float ancho) {
        this.ancho = ancho;
    }

    public float getLargo() {
        return largo;
    }

    public void setLargo(float largo) {
        this.largo = largo;
    }


}
