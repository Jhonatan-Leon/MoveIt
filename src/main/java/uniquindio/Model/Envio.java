package uniquindio.Model;

import uniquindio.Model.ClassBuilder.EnvioBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Envio {
    private int IdEnvio;
    private String tipoDireccion;
    private Direccion direccionOrigen;
    private Direccion direccionDestino;
    private ArrayList<Package> listpaquete;
    private TipoEstado estado;
    private Repartidor repartidor;
    private double Costo;
    private Date fechaCreacion;
    private Date fechaEstimada;
    private Date fechaEntregaReal;
    private TipoPrioridad prioridad;
    private List<String> serviciosAdicionales;
    private double distanciaKm;
    private int tiempoDemoradoMin;
    private String ultimaIncidencia;

    public Envio(EnvioBuilder builder){
        this.IdEnvio = builder.IdEnvio;
        this.tipoDireccion = builder.tipoDireccion;
        this.direccionOrigen = builder.direccionOrigen;
        this.direccionDestino = builder.direccionDestino;
        this.listpaquete = builder.listpaquete;
        this.estado = builder.estado;
        this.repartidor = builder.repartidor;
        this.Costo = builder.Costo;
        this.fechaCreacion = builder.fechaCreación;
        this.fechaEstimada = builder.fechaEstimada;
        this.fechaEntregaReal = builder.fechaEntregaReal;
        this.prioridad = builder.prioridad;
        this.serviciosAdicionales = builder.serviciosAdicionales != null
                ? new ArrayList<>(builder.serviciosAdicionales)
                : new ArrayList<>();
        this.distanciaKm = builder.distanciaKm;
        this.tiempoDemoradoMin = builder.tiempoDemoradoMin;
        this.ultimaIncidencia = builder.ultimaIncidencia;
    }

    public int getIdEnvio() {
        return IdEnvio;
    }

    public void setIdEnvio(int idEnvio) {
        IdEnvio = idEnvio;
    }

    public String getTipoDireccion() {
        return tipoDireccion;
    }

    public void setTipoDireccion(String tipoDireccion) {
        this.tipoDireccion = tipoDireccion;
    }

    public Direccion getDireccionOrigen() {
        return direccionOrigen;
    }

    public void setDireccionOrigen(Direccion direccionOrigen) {
        this.direccionOrigen = direccionOrigen;
    }

    public Direccion getDireccionDestino() {
        return direccionDestino;
    }

    public void setDireccionDestino(Direccion direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    public ArrayList<Package> getListpaquete() {
        return listpaquete;
    }

    public void setListpaquete(ArrayList<Package> listpaquete) {
        this.listpaquete = listpaquete;
    }

    public TipoEstado getEstado() {
        return estado;
    }

    public void setEstado(TipoEstado estado) {
        this.estado = estado;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    public TipoPrioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(TipoPrioridad prioridad) {
        this.prioridad = prioridad;
    }

    public List<String> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setServiciosAdicionales(List<String> serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales != null ? new ArrayList<>(serviciosAdicionales) : new ArrayList<>();
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public int getTiempoDemoradoMin() {
        return tiempoDemoradoMin;
    }

    public void setTiempoDemoradoMin(int tiempoDemoradoMin) {
        this.tiempoDemoradoMin = tiempoDemoradoMin;
    }

    public double getCosto() {
        return Costo;
    }

    public void setCosto(double costo) {
        Costo = costo;
    }

    public Date getFechaCreación() {
        return fechaCreacion;
    }

    public void setFechaCreación(Date fechaCreación) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaEstimada() {
        return fechaEstimada;
    }

    public void setFechaEstimada(Date fechaEstimada) {
        this.fechaEstimada = fechaEstimada;
    }

    public Date getFechaEntregaReal() {
        return fechaEntregaReal;
    }

    public void setFechaEntregaReal(Date fechaEntregaReal) {
        this.fechaEntregaReal = fechaEntregaReal;
    }

    public String getUltimaIncidencia() {
        return ultimaIncidencia;
    }

    public void setUltimaIncidencia(String ultimaIncidencia) {
        this.ultimaIncidencia = ultimaIncidencia;
    }
}
