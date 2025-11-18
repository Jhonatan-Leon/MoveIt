package uniquindio.Model.DTO;

import uniquindio.Model.Direccion;
import java.util.List;

public class CotizacionDTO {
    private double distanciaKm;
    private int tiempoDemorado;
    private String prioridad;
    private List<PaqueteDTO> paquetes;
    private List<String> serviciosAdicionales;
    private Direccion direccionOrigen;
    private Direccion direccionDestino;
    private double costoCalculado;

    public double getDistanciaKm() { return distanciaKm; }
    public void setDistanciaKm(double distanciaKm) { this.distanciaKm = distanciaKm; }

    public int getTiempoDemora() { return tiempoDemorado; }
    public void setTiempoDemoraMinutos(int tiempoDemoraMinutos) { this.tiempoDemorado = tiempoDemoraMinutos; }

    public String getPrioridad() { return prioridad; }
    public void setPrioridad(String prioridad) { this.prioridad = prioridad; }

    public List<PaqueteDTO> getPaquetes() { return paquetes; }
    public void setPaquetes(List<PaqueteDTO> paquetes) { this.paquetes = paquetes; }

    public List<String> getServiciosAdicionales() { return serviciosAdicionales; }
    public void setServiciosAdicionales(List<String> serviciosAdicionales) { this.serviciosAdicionales = serviciosAdicionales; }

    public Direccion getDireccionOrigen() { return direccionOrigen; }
    public void setDireccionOrigen(Direccion direccionOrigen) { this.direccionOrigen = direccionOrigen; }

    public Direccion getDireccionDestino() { return direccionDestino; }
    public void setDireccionDestino(Direccion direccionDestino) { this.direccionDestino = direccionDestino; }

    public double getCostoCalculado() { return costoCalculado; }
    public void setCostoCalculado(double costoCalculado) { this.costoCalculado = costoCalculado; }
}
