package edu.co.Model.ClassBuilder;

import edu.co.Model.Package;
import edu.co.Model.TipoEstado;

import java.util.ArrayList;
import java.util.Date;

public class EnvioBuilder {
    public int IdEnvio;
    public String tipoDireccion;
    public ArrayList<Package> listpaquete;
    public TipoEstado estado;
    public double Costo;
    public Date fechaCreación;
    public Date fechaEstimada;

    public EnvioBuilder (int IdEnvio, String tipoDireccion, ArrayList<Package> listpaquete, TipoEstado estado){
        this.IdEnvio = IdEnvio;
        this.tipoDireccion = tipoDireccion;
        this.listpaquete = listpaquete;
        this.estado = estado;
        this.listpaquete = new ArrayList<>();
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
