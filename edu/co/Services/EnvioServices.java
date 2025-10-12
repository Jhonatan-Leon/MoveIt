package edu.co.Services;

import edu.co.Model.Envio;
import edu.co.Model.Gestion.GestionEnvios;

import java.util.List;

public class EnvioServices {
    private static final GestionEnvios gestion = GestionEnvios.getInstance();

    public static Envio getEnvio(int IdEnvio){
        return gestion.getEnvio(IdEnvio);
    }

    public static List<Envio> CreateEnvioUser(int IdUser, Envio envio){

    }

    public static boolean DeleteEnvio(int IdEnvio){
        return  gestion.DeleteEnvio(IdEnvio);
    }

    public static Envio updateEnvio(Envio updateEnvio){
        return gestion.updateEnvio(updateEnvio);
    }

    public static boolean AddEnvio(Envio envio){
        return gestion.AddEnvio(envio);
    }

    public static List<Envio> getListEnvios (){
        return gestion.Envios();
    }
}
