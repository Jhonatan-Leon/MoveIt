package uniquindio.Services;

import uniquindio.Errors.ControllException;
import uniquindio.Facade.UserFacade;
import uniquindio.Model.Client;
import uniquindio.Model.Direccion;
import uniquindio.Model.Gestion.GestionDireccion;
import uniquindio.Model.Gestion.GestionUser;

public class DireccionServices {
    private static final GestionUser gestionUser = GestionUser.getInstance();

    public static void addDireccion(Direccion newDireccion, Client client) throws ControllException.UserCreate {
        try {
            if (newDireccion == null) {
                throw new ControllException.UserCreate("La dirección no puede ser nula");
            }

            Direccion existingById = GestionDireccion.getDireccion(newDireccion.getIdDireccion());
            if (existingById != null) {
                throw new ControllException.UserCreate("Ya existe una dirección con el ID: " + newDireccion.getIdDireccion());
            }

            if (GestionDireccion.getDireccionByAlias(newDireccion.getAlias()) != null) {
                throw new ControllException.UserCreate("Ya existe una dirección con el alias: " + newDireccion.getAlias());
            }

            // Agregar la dirección
            GestionDireccion.AddDireccion(newDireccion);
            UserServices.addDireccionToClient(client,newDireccion);

        } catch (ControllException.UserCreate e) {
            throw e;
        } catch (Exception e) {
            throw new ControllException.UserCreate("Error al registrar la dirección: " + e.getMessage());
        }
    }


}
