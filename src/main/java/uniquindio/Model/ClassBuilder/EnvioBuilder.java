package uniquindio.Model.ClassBuilder;

import uniquindio.Model.Direccion;
import uniquindio.Model.Package;
import uniquindio.Model.Repartidor;
import uniquindio.Model.TipoEstado;
import uniquindio.Model.TipoPrioridad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * PATRÓN: Builder (Creacional)
 * 
 * Permite construir objetos Envio complejos paso a paso.
 * Separa la construcción de un objeto complejo de su representación,
 * permitiendo que el mismo proceso de construcción cree diferentes representaciones.
 * 
 * Ventajas:
 * - Permite construir objetos con muchos parámetros opcionales
 * - Código más legible y mantenible
 * - Permite validar parámetros antes de construir el objeto
 * 
 * Principio SOLID aplicado: Single Responsibility Principle (SRP)
 * - Responsabilidad única: construir objetos Envio
 */
public class EnvioBuilder {
    public int IdEnvio;
    public String tipoDireccion;
    public Direccion direccionOrigen;
    public Direccion direccionDestino;
    public ArrayList<Package> listpaquete;
    public TipoEstado estado;
    public Repartidor repartidor;
    public double Costo;
    public Date fechaCreación;
    public Date fechaEstimada;
    public Date fechaEntregaReal;
    public TipoPrioridad prioridad;
    public List<String> serviciosAdicionales;
    public double distanciaKm;
    public int tiempoDemoradoMin;
    public String ultimaIncidencia;

    public EnvioBuilder (int IdEnvio, String tipoDireccion, ArrayList<Package> listpaquete, TipoEstado estado){
        this.IdEnvio = IdEnvio;
        this.tipoDireccion = tipoDireccion;
        this.estado = estado;
        if (listpaquete != null) {
            this.listpaquete = new ArrayList<>(listpaquete);
        } else {
            this.listpaquete = new ArrayList<>();
        }
        this.serviciosAdicionales = Collections.emptyList();
    }
    
    public EnvioBuilder withDirecciones(Direccion origen, Direccion destino) {
        this.direccionOrigen = origen;
        this.direccionDestino = destino;
        return this;
    }

    public EnvioBuilder withCosto(double Costo){
        this.Costo = Costo;
        return this;
    }

    public ArrayList<Package> getListpaquete(){
        return listpaquete;
    }

    public void AgregarPaquete(Package paquete){
        listpaquete.add(paquete);
    }

    public EnvioBuilder withRepartidor(Repartidor repartidor){
        this.repartidor = repartidor;
        return this;
    }

    public EnvioBuilder withPrioridad(TipoPrioridad prioridad){
        this.prioridad = prioridad;
        return this;
    }

    public EnvioBuilder withServiciosAdicionales(List<String> servicios){
        if (servicios == null) {
            this.serviciosAdicionales = Collections.emptyList();
        } else {
            this.serviciosAdicionales = new ArrayList<>(servicios);
        }
        return this;
    }

    public EnvioBuilder withDistancia(double distanciaKm){
        this.distanciaKm = distanciaKm;
        return this;
    }

    public EnvioBuilder withTiempoDemora(int minutos){
        this.tiempoDemoradoMin = minutos;
        return this;
    }

    public EnvioBuilder withFechaEntregaReal(Date fechaEntrega){
        this.fechaEntregaReal = fechaEntrega;
        return this;
    }

    public EnvioBuilder withIncidencia(String incidencia){
        this.ultimaIncidencia = incidencia;
        return this;
    }

    public EnvioBuilder withfechaCreacion(Date fechaCreacion){
        this.fechaCreación = fechaCreacion;
        return this;
    }

    public EnvioBuilder withfechaEstimada(Date fechaEstimada){
        this.fechaEstimada = fechaEstimada;
        return this;
    }


}
