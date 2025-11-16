package uniquindio.Services;

import uniquindio.Model.Envio;
import uniquindio.Model.Gestion.GestionEnvios;
import uniquindio.Model.TipoEstado;

import java.util.List;

public class EnvioServices {
    private static final GestionEnvios gestion = GestionEnvios.getInstance();

    public static Envio getEnvio(int IdEnvio){
        return gestion.getEnvio(IdEnvio);
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

    public static String obtenerMensajeRastreo (String idStg) {
        int id = Integer.parseInt(idStg);
        Envio envio = getEnvio(id);
        String mensaje = "";
        if (envio == null) {
            mensaje = "No existe un envio con ese ID.";
            return mensaje;
        }
        else if (envio.getEstado().equals(TipoEstado.SOLICITADO)) {
            mensaje = "Tu envío ha sido solicitado y estamos preparando todo para asignarlo.";
            return mensaje;
        }
        else if (envio.getEstado().equals(TipoEstado.ASIGNADO)) {
            mensaje = "Tu envío ya fue asignado a un repartidor. Pronto iniciará el recorrido.";
            return mensaje;
        }
        else if (envio.getEstado().equals(TipoEstado.EN_RUTA)) {
            mensaje = "Tu envío está en camino hacia la dirección indicada.";
            return mensaje;
        }
        else if (envio.getEstado().equals(TipoEstado.ENTREGADO)) {
            mensaje = "Tu envío ha sido entregado exitosamente.";
            return mensaje;
        }
        else if (envio.getEstado().equals(TipoEstado.INCIDENCIA)) {
            mensaje = "Se presentó una incidencia con tu envío. Uno de nuestros agentes se comunicará contigo.";
            return mensaje;
        }
        else {
            mensaje = "No encontramos un pedido con ese ID.";
            return mensaje;
        }
    }
}
