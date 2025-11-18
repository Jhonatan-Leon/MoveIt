package uniquindio.Facade;

import uniquindio.Errors.ControllException;
import uniquindio.Model.Client;
import uniquindio.Model.DTO.CotizacionDTO;
import uniquindio.Model.DTO.UserLoginDTO;
import uniquindio.Model.Envio;
import uniquindio.Services.EnvioServices;
import uniquindio.Services.UserServices;
import uniquindio.Utils.LoginDTO;

import java.util.List;

public class EnvioFacade {

    public static Envio getEnvio(int IdEnvio){

        return EnvioServices.getEnvio(IdEnvio);
    }

    public static boolean AddEnvio(Envio envio){

        return EnvioServices.AddEnvio(envio);
    }

    public static boolean DeleteEnvio(int IdEnvio){

        return EnvioServices.DeleteEnvio(IdEnvio);
    }

    public static Envio updateEnvio(Envio updateEnvio){

        return EnvioServices.updateEnvio(updateEnvio);
    }

    public static List<Envio> GetListEnvios(){

        return  EnvioServices.getListEnvios();
    }

    public static String obtenerMensajeRastreo (String id) {
        return EnvioServices.obtenerMensajeRastreo(id);
    }

    public static CotizacionDTO cotizarTarifa(CotizacionDTO cotizacionDTO) 
            throws ControllException.CotizacionInvalid, ControllException.TarifaError {
        return EnvioServices.CotizarEnvio(cotizacionDTO);
    }

    public static Envio CrearEnvio(CotizacionDTO cotizacionDTO, UserLoginDTO client) 
            throws ControllException.UserNotFound, ControllException.EnvioCreate, 
                   ControllException.CotizacionInvalid, ControllException.TarifaError {
        Client user = UserServices.getUserById(Integer.parseInt(client.getId()));
        return EnvioServices.CrearSolicitudEnvio(cotizacionDTO, user);
    }
}
