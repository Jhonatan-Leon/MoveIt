package uniquindio.Helper;

import uniquindio.Errors.ControllException;
import uniquindio.Model.Envio;
import uniquindio.Model.TipoEstado;
import uniquindio.Services.EnvioServices;
import uniquindio.Services.RepartidorServices;

public class PersistirEnvio {
    public static Envio persistirEnvio(Envio envio, TipoEstado nuevoEstado)
            throws ControllException.EnvioCreate {

        if (TipoEstado.ENTREGADO.equals(nuevoEstado) || TipoEstado.INCIDENCIA.equals(nuevoEstado)) {
            liberarRepartidor(envio);
        } else if (TipoEstado.ASIGNADO.equals(nuevoEstado) || TipoEstado.EN_RUTA.equals(nuevoEstado)) {
            ocuparRepartidor(envio);
        }

        Envio actualizado = EnvioServices.updateEnvio(envio);
        if (actualizado == null) {
            throw new ControllException.EnvioCreate("No se pudo actualizar el envío");
        }
        return actualizado;
    }

    private static void liberarRepartidor(Envio envio) throws ControllException.EnvioCreate {
        if (envio.getRepartidor() == null) {
            return;
        }
        try {
            RepartidorServices.actualizarDisponibilidad(envio.getRepartidor().getId(), true);
        } catch (ControllException.UserNotFound e) {
            throw new ControllException.EnvioCreate("No se pudo liberar al repartidor");
        }
    }

    private static void ocuparRepartidor(Envio envio) throws ControllException.EnvioCreate {
        if (envio.getRepartidor() == null) {
            return;
        }
        try {
            RepartidorServices.actualizarDisponibilidad(envio.getRepartidor().getId(), false);
        } catch (ControllException.UserNotFound e) {
            throw new ControllException.EnvioCreate("No se pudo bloquear al repartidor");
        }
    }
}
