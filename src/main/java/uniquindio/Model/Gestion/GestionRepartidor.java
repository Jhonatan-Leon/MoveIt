package uniquindio.Model.Gestion;

import uniquindio.Helper.JsonLoader;
import uniquindio.Model.Repartidor;

import java.util.ArrayList;
import java.util.List;

public class GestionRepartidor {
    private static List<Repartidor> listaRepartidor = new ArrayList<>();
    private static GestionRepartidor instance;

    private GestionRepartidor() {
        listaRepartidor = JsonLoader.CargarProductos("DatosQuemados/Repartidor.json",  Repartidor.class);
    }

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
                r.setNombreCompleto(updateUser.getNombreCompleto());
                r.setNumeroDocumento(updateUser.getNumeroDocumento());
                r.setTipoDocumento(updateUser.getTipoDocumento());
                r.setZonaCobertura(updateUser.getZonaCobertura());
                r.setDisponible(updateUser.isDisponible());
                return r;
            }
        }
        return null;
    }

    public Repartidor actualizarDisponibilidad(String id, boolean disponible){
        Repartidor repartidor = getRepartidor(id);
        if(repartidor != null){
            repartidor.setDisponible(disponible);
        }
        return repartidor;
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
