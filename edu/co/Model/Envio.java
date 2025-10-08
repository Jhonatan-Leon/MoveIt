package edu.co.Model;

import edu.co.Model.ClassBuilder.EnvioBuilder;

import java.util.ArrayList;
import java.util.Date;

public class Envio {
    private int IdEnvio;
    private String tipoDireccion;
    private ArrayList<Package> listpaquete;
    private TipoEstado estado;
    private double Costo;
    private Date fechaCreación;
    private Date fechaEstimada;

    public Envio(EnvioBuilder builder){
        this.IdEnvio = builder.IdEnvio;
        this.tipoDireccion = builder.tipoDireccion;
        this.listpaquete = builder.listpaquete;
        this.estado = builder.estado;
        this.Costo = builder.Costo;
        this.fechaCreación = builder.fechaCreación;
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
        return fechaCreación;
    }

    public void setFechaCreación(Date fechaCreación) {
        this.fechaCreación = fechaCreación;
    }

    public Date getFechaEstimada() {
        return fechaEstimada;
    }

    public void setFechaEstimada(Date fechaEstimada) {
        this.fechaEstimada = fechaEstimada;
    }
}
