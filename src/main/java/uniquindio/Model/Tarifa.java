package uniquindio.Model;

public class Tarifa {
    private float Distancia;
    private float peso;
    private float Volumen;
    private TipoPrioridad Prioridad;
    private float RecargaAdicional;

    public Tarifa(float Distancia, float peso, float Volumen, TipoPrioridad Prioridad, float RecargaAdicional) {
        this.Distancia = Distancia;
        this.peso = peso;
        this.Volumen = Volumen;
        this.Prioridad = Prioridad;
        this.RecargaAdicional = RecargaAdicional;
    }

    public float getDistancia() {
        return Distancia;
    }

    public void setDistancia(float distancia) {
        Distancia = distancia;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getVolumen() {
        return Volumen;
    }

    public void setVolumen(float volumen) {
        Volumen = volumen;
    }

    public TipoPrioridad getPrioridad() {
        return Prioridad;
    }

    public void setPrioridad(TipoPrioridad prioridad) {
        Prioridad = prioridad;
    }

    public float getRecargaAdicional() {
        return RecargaAdicional;
    }

    public void setRecargaAdicional(float recargaAdicional) {
        RecargaAdicional = recargaAdicional;
    }
}
