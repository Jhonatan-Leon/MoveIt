package Java.co.Model;

import Java.co.Model.ClassBuilder.EnvioBuilder;

import java.util.ArrayList;
import java.util.Date;

public class Envio {
    private int IdEnvio;
    private String tipoDireccion;
    private ArrayList<Package> listpaquete;
    private TipoEstado estado;
    private double Costo;
    private Date fechaCreacion;
    private Date fechaEstimada;

    public Envio(EnvioBuilder builder){
        this.IdEnvio = builder.IdEnvio;
        this.tipoDireccion = builder.tipoDireccion;
        this.listpaquete = builder.listpaquete;
        this.estado = builder.estado;
        this.Costo = builder.Costo;
        this.fechaCreacion = builder.fechaCreación;
        this.fechaEstimada = builder.fechaEstimada;
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
}
