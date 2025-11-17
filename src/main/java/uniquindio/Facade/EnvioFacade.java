package uniquindio.Facade;

import uniquindio.Model.Envio;
import uniquindio.Services.EnvioServices;

import java.util.List;

public class EnvioFacade {
    private static EnvioFacade instance;

    public static EnvioFacade getInstance(){
        if(instance == null){
            instance = new EnvioFacade();
        }
        return instance;
    }

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
}
