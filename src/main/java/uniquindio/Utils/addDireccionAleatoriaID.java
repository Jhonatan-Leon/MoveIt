package uniquindio.Utils;

import uniquindio.Errors.ControllException;
import java.util.UUID;

public class addDireccionAleatoriaID {
    public static String addDireccionAleatoria() throws ControllException.UserCreate {
        try {

            String idAleatorio = UUID.randomUUID().toString();
            return idAleatorio;

        } catch (Exception e) {
            throw new ControllException.UserCreate("Error al agregar la dirección: " + e.getMessage());
        }
    }
}
