package uniquindio.Model.ClassBuilder;

public class PaqueteBuilder {
    public String IdPaquete;
    public float peso;
    public float alto;
    public float ancho;
    public float largo = 0.0F;

    public PaqueteBuilder(String IdPaquete, float peso, float alto, float ancho) {
        this.IdPaquete = IdPaquete;
        this.peso = peso;
        this.alto = alto;
        this.ancho = ancho;
    }

    public PaqueteBuilder withlargo(float largo){
        this.largo = largo;
        return this;
    }

    @Override
    public String toString() {
        double volumen = (largo / 100.0) * (ancho / 100.0) * (alto / 100.0);
        return "PaqueteBuilder{" + "IdPaquete=" + IdPaquete + ", peso=" + peso + ", alto=" + alto + ", ancho=" + ancho + ", largo=" + largo + ", volumen=" + volumen + '}';
    }
}
