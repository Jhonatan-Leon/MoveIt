package edu.co.Facade;

import edu.co.Model.Repartidor;
import edu.co.Services.RepartidorServices;

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

}
