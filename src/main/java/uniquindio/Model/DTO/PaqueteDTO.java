package uniquindio.Model.DTO;

public class PaqueteDTO {
    private String descripcion;
    private double peso;
    private double largo;
    private double ancho;
    private double alto;

    public PaqueteDTO(String descripcion,double peso, double largo, double ancho, double alto){
        this.descripcion = descripcion;
        this.peso = peso;
        this.largo = largo;
        this.ancho = ancho;
        this.alto = alto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }
}
