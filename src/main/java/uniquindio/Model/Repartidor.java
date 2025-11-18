package uniquindio.Model;

import uniquindio.Model.ClassBuilder.RepartidorBuilder;

public class Repartidor extends User {
    private String ZonaCobertura;

    public Repartidor(RepartidorBuilder builder) {
        super(builder.id, builder.nombreCompleto, builder.email, builder.password,
                builder.telefono, builder.tipoDocumento, builder.numeroDocumento, builder.estado);
        this.ZonaCobertura = builder.zonaCobertura;
    }

    public String getZonaCobertura() {
        return ZonaCobertura;
    }

    public void setZonaCobertura(String zonaCobertura) {
        ZonaCobertura = zonaCobertura;
    }
}