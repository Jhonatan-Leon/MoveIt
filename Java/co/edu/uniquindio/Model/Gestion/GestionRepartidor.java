package Java.co.Model.Gestion;

import Java.co.Model.Repartidor;

import java.util.ArrayList;
import java.util.List;

public class GestionRepartidor {
    private static final List<Repartidor> listaRepartidor = new ArrayList<>();
    private static GestionRepartidor instance;

    public static GestionRepartidor getInstance(){
        if(instance == null){
            instance = new GestionRepartidor();
        }
        return instance;
    }

    public List<Repartidor> getlist() {
        return new ArrayList<>(listaRepartidor);
    }

    public boolean addRepartidor(Repartidor newUser) {
        listaRepartidor.add(newUser);
        return true;
    }

    public Repartidor updateRepartidor(String Id, Repartidor updateUser) {
        for(Repartidor r: listaRepartidor) {
            if(r.getId().equals(Id)){
                r.setNombreCompeto(updateUser.getNombreCompeto());
                r.setNumeroDocumento(updateUser.getNumeroDocumento());
                r.setTipoDocumento(updateUser.getTipoDocumento());
                r.setZonaCobertura(updateUser.getZonaCobertura());
                return r;
            }
        }
        return null;
    }

    public boolean DeleteRepatidor(String Id){
        for(Repartidor r: listaRepartidor){
            if(r.getId().equals(Id)){
                listaRepartidor.remove(r);
                return true;
            }
        }
        return false;
    }

    public Repartidor getRepartidor(String Id){
        for(Repartidor r: listaRepartidor) {
            if(r.getId().equals(Id)){
                return r;
            }
        }
        return null;
    }

}
