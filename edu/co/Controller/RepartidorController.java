package edu.co.Controller;

import edu.co.Errors.ControllException;
import edu.co.Facade.RepartidorFacade;
import edu.co.Model.Repartidor;

import java.util.List;

public class RepartidorController {
    private final RepartidorFacade repartidorFacade = RepartidorFacade.getInstance();

    public Repartidor getRepartidor(String Id) throws ControllException.UserNotFound {
        try{
            return repartidorFacade.getRepartidor(Id);
        }catch (Exception e){
            throw new ControllException.UserNotFound("Usuario no encontrado");
        }
    }

    public boolean CreateRepartidor(Repartidor rep) throws ControllException.UserCreate {
        try{
            return repartidorFacade.addRepartidor(rep);
        }catch (Exception e) {
            throw new ControllException.UserCreate("Error al registrar usuario: " + e.getMessage());
        }
    }

    public Repartidor updateRepartidor(Repartidor rep) throws ControllException.UserUpdate {
        try{
            return repartidorFacade.updateRepartidor(rep);
        }catch (Exception e) {
            throw new ControllException.UserUpdate("Error al registrar usuario: " + e.getMessage());
        }
    }

    public boolean deleteRepartidor(String Id) throws ControllException.UserDelete {
        try{
            return repartidorFacade.deleteRepartidor(Id);
        }catch (Exception e) {
            throw new ControllException.UserDelete("Error al registrar usuario: " + e.getMessage());
        }
    }

    public List<Repartidor> getAllRep() throws  ControllException.UserNotFound {
        try {
            return repartidorFacade.listaRepartidor();
        }catch (Exception e){
            throw new ControllException.UserNotFound("No hay usuarios registrados");
        }
    }
}
