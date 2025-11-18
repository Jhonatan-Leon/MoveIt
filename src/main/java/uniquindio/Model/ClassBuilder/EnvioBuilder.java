package uniquindio.Model.ClassBuilder;

import uniquindio.Model.Direccion;
import uniquindio.Model.Package;
import uniquindio.Model.TipoEstado;

import java.util.ArrayList;
import java.util.Date;

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
    public double Costo;
    public Date fechaCreación;
    public Date fechaEstimada;

    public EnvioBuilder (int IdEnvio, String tipoDireccion, ArrayList<Package> listpaquete, TipoEstado estado){
        this.IdEnvio = IdEnvio;
        this.tipoDireccion = tipoDireccion;
        this.estado = estado;
        if (listpaquete != null) {
            this.listpaquete = new ArrayList<>(listpaquete);
        } else {
            this.listpaquete = new ArrayList<>();
        }
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

    public EnvioBuilder withfechaCreacion(Date fechaCreacion){
        this.fechaCreación = fechaCreacion;
        return this;
    }

    public EnvioBuilder withfechaEstimada(Date fechaEstimada){
        this.fechaEstimada = fechaEstimada;
        return this;
    }


}
