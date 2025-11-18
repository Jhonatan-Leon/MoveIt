package uniquindio.Services;

import uniquindio.Errors.ControllException;
import uniquindio.Model.Gestion.GestionRepartidor;
import uniquindio.Model.Repartidor;

import java.util.List;

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

    public static Repartidor actualizarDisponibilidad(String id, boolean disponible) throws ControllException.UserNotFound {
        Repartidor repartidor = gestion.actualizarDisponibilidad(id, disponible);
        if(repartidor == null){
            throw new ControllException.UserNotFound("Repartidor no encontrado con ID: " + id);
        }
        return repartidor;
    }

    public static boolean deleteRepartidor(String Id){
        return gestion.DeleteRepatidor(Id);
    }

    public static boolean AddRepartidor(Repartidor repartidor){
        return gestion.addRepartidor(repartidor);
    }
}
