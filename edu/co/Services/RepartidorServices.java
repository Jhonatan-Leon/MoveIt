package edu.co.Services;

import edu.co.Model.Gestion.GestionRepartidor;
import edu.co.Model.Repartidor;

import java.util.List;
import java.util.Map;

public class RepartidorServices {
    private static final GestionRepartidor gestion = GestionRepartidor.getInstance();

    public static List<Repartidor> getList() {
        return gestion.getlist();

    }

    public static Repartidor getRepartidor(String Id){
        return  gestion.getRepartidor(Id);
    }

    public static Repartidor updateRepartidor(String Id, Repartidor updateUser){
        Repartidor findRepartido = gestion.getRepartidor(Id);

        if(findRepartido != null){
            Repartidor updateRepartidor = gestion.updateRepartidor(Id, updateUser);
            return updateRepartidor;
        }

        return null;
    }

    public static boolean deleteRepartidor(String Id){
        return gestion.DeleteRepatidor(Id);
    }

    public static boolean AddRepartidor(Repartidor repartidor){
        return gestion.addRepartidor(repartidor);
    }
}
