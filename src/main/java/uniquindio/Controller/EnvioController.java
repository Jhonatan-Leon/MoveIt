package uniquindio.Controller;


import uniquindio.Facade.EnvioFacade;
import uniquindio.Model.Envio;

public class EnvioController {
    private final static EnvioFacade EnviosFacade = EnvioFacade.getInstance();

    public static Envio getEnvio(int IdEnvio) {
        try {
            return EnvioFacade.getEnvio(IdEnvio);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public static boolean AddEnvio(Envio envio){
        try {
            return EnvioFacade.AddEnvio(envio);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public static Envio updateEnvio(Envio updateEnvio){
        try {
            return EnvioFacade.updateEnvio(updateEnvio);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public static boolean DeleteEnvio(int IdEnvio){
        try {
            return EnvioFacade.DeleteEnvio(IdEnvio);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

}
