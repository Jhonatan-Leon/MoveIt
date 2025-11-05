package Java.co.Facade;

import Java.co.Model.Repartidor;
import Java.co.Services.RepartidorServices;

import java.util.List;

public class RepartidorFacade {
    private static RepartidorFacade instance;

    public static RepartidorFacade getInstance() {
        if(instance == null) {
            instance = new RepartidorFacade();
        }
        return instance;
    }

    public List<Repartidor> listaRepartidor(){
        return RepartidorServices.getList();
    }

    public Repartidor getRepartidor(String Id){
        return RepartidorServices.getRepartidor(Id);
    }

    public Repartidor updateRepartidor(Repartidor updateUser){
        return RepartidorServices.updateRepartidor(updateUser.getId(), updateUser);
    }

    public boolean addRepartidor(Repartidor newUser){
        return RepartidorServices.AddRepartidor(newUser);
    }

    public boolean deleteRepartidor(String Id) {
        return RepartidorServices.deleteRepartidor(Id);
    }

}
