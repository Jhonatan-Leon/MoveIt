package Java.co.Model.ClassBuilder;

public class PaqueteBuilder {
    public String IdPaquete;
    public float peso;
    public float alto;
    public float ancho;
    public float volumen = 0.0F;

    public PaqueteBuilder(String IdPaquete, float peso, float alto, float ancho) {
        this.IdPaquete = IdPaquete;
        this.peso = peso;
        this.alto = alto;
        this.ancho = ancho;
    }

    public PaqueteBuilder withVolumen(float volumen){
        this.volumen = volumen;
        return this;
    }

    @Override
    public String toString() {
        return "PaqueteBuilder{" + "IdPaquete=" + IdPaquete + ", peso=" + peso + ", alto=" + alto + ", ancho=" + ancho + ", volumen=" + volumen + '}';
    }
}
