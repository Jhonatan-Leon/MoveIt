package uniquindio.Model.Gestion;

import uniquindio.Model.Envio;

import java.util.ArrayList;
import java.util.List;

public class GestionEnvios {
    private static GestionEnvios instance;
    private static List<Envio> listaEnvio = new ArrayList<>();

    public static GestionEnvios getInstance() {
        if(instance == null) {
            instance = new GestionEnvios();
        }
        return instance;
    }

    public Envio getEnvio(int IdEnvio){
        Envio envio = null;
        for(Envio e: listaEnvio){
            if(e.getIdEnvio() == IdEnvio){
                envio = e;
                return  envio;
            }
        }
        return  null;
    }

    public boolean AddEnvio(Envio envio){
        return listaEnvio.add(envio);
    }

    public Envio updateEnvio(Envio updateEnvio){
        for(Envio e : listaEnvio) {
            if(e.getIdEnvio() == updateEnvio.getIdEnvio()){
                e.setFechaCreación(updateEnvio.getFechaCreación());
                e.setEstado(updateEnvio.getEstado());
                e.setListpaquete(updateEnvio.getListpaquete());
                e.setFechaEstimada(updateEnvio.getFechaEstimada());
                e.setTipoDireccion(updateEnvio.getTipoDireccion());
                e.setCosto(updateEnvio.getCosto());
                return e;
            }
        }
        return null;
    }

    public boolean DeleteEnvio(int IdEnvio){
        for(Envio e : listaEnvio){
            if(e.getIdEnvio() == IdEnvio){
                listaEnvio.remove(e);
                return  true;
            }
        }
        return false;
    }

    public static List<Envio> Envios(){
        return  new ArrayList<>(listaEnvio);
    }
}
