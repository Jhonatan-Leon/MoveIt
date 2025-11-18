package uniquindio.Facade;

import uniquindio.Errors.ControllException;
import uniquindio.Model.Client;
import uniquindio.Model.Envio;
import uniquindio.Services.EnvioServices;
import uniquindio.Services.UserServices;

import java.util.List;

public class CostoFacade {
    public static double costoEnvio(int IdClient, int IdEnvio, double distanciaKm, String prioridad, List<String> serviciosAdicionales) 
            throws ControllException.UserNotFound, ControllException.EnvioNotFound, ControllException.TarifaError {
        
        Client usuario = UserServices.getUserById(IdClient);
        Envio envio = EnvioServices.buscarEnvioporUsuario(usuario, IdEnvio);

        if (envio.getCosto() > 0) {
            return envio.getCosto();
        }

        return EnvioServices.calcularCostoEnvio(envio, distanciaKm, prioridad, serviciosAdicionales);
    }
}
