package uniquindio.Controller;

import uniquindio.Errors.ControllException;
import uniquindio.Facade.CostoFacade;

import java.util.List;

public class CostoController {

    public static double costoEnvio(int IdClient, int IdEnvio, double distanciaKm, String prioridad, List<String> serviciosAdicionales)
            throws ControllException.UserNotFound, ControllException.EnvioNotFound, ControllException.TarifaError, ControllException.ErrorServer {
        try {
            return CostoFacade.costoEnvio(IdClient, IdEnvio, distanciaKm, prioridad, serviciosAdicionales);
        } catch (ControllException.UserNotFound | ControllException.EnvioNotFound | ControllException.TarifaError e) {
            throw e;
        } catch (Exception e) {
            throw new ControllException.ErrorServer("Error al calcular el costo del envío: " + e.getMessage());
        }
    }
}

