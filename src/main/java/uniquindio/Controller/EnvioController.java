package uniquindio.Controller;

import uniquindio.Errors.ControllException;
import uniquindio.Facade.EnvioFacade;
import uniquindio.Model.DTO.CotizacionDTO;
import uniquindio.Model.DTO.UserLoginDTO;
import uniquindio.Model.Envio;

import java.util.List;

public class EnvioController {

    public static Envio getEnvio(int IdEnvio) throws ControllException.ErrorServer {
        try {
            return EnvioFacade.getEnvio(IdEnvio);
        } catch (Exception e) {
            throw new ControllException.ErrorServer("Error al obtener el envío: " + e.getMessage());
        }
    }

    public static boolean AddEnvio(Envio envio) throws ControllException.ErrorServer {
        try {
            return EnvioFacade.AddEnvio(envio);
        } catch (Exception e) {
            throw new ControllException.ErrorServer("Error al agregar el envío: " + e.getMessage());
        }
    }

    public static Envio updateEnvio(Envio updateEnvio) throws ControllException.ErrorServer {
        try {
            return EnvioFacade.updateEnvio(updateEnvio);
        } catch (Exception e) {
            throw new ControllException.ErrorServer("Error al actualizar el envío: " + e.getMessage());
        }
    }

    public static boolean DeleteEnvio(int IdEnvio) throws ControllException.ErrorServer {
        try {
            return EnvioFacade.DeleteEnvio(IdEnvio);
        } catch (Exception e) {
            throw new ControllException.ErrorServer("Error al eliminar el envío: " + e.getMessage());
        }
    }

    public static List<Envio> GetListEnvios() throws ControllException.ErrorServer{
        try {
            return EnvioFacade.GetListEnvios();
        } catch (Exception e) {
            throw new ControllException.ErrorServer("Error al obtener la lista de envíos: " + e.getMessage());
        }
    }

    public static String obtenerMensajeRastreo(String id) throws ControllException.ErrorServer {
        try {
            return EnvioFacade.obtenerMensajeRastreo(id);
        } catch (Exception e) {
            throw new ControllException.ErrorServer("Error al obtener el mensaje de rastreo: " + e.getMessage());
        }
    }

    public static Envio CrearEnvio(CotizacionDTO cotizacionDTO, UserLoginDTO client)
            throws ControllException.UserNotFound, ControllException.EnvioCreate,
            ControllException.CotizacionInvalid, ControllException.TarifaError, ControllException.ErrorServer {
        try {
            return EnvioFacade.CrearEnvio(cotizacionDTO, client);
        } catch (ControllException.UserNotFound | ControllException.EnvioCreate | 
                 ControllException.CotizacionInvalid | ControllException.TarifaError e) {
            throw e;
        } catch (Exception e) {
            throw new ControllException.ErrorServer("Error al crear el envío: " + e.getMessage());
        }
    }
}

