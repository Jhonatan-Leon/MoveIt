package uniquindio.Helper;

import uniquindio.Model.Client;
import uniquindio.Model.DTO.ClientSesionDTO;
import uniquindio.Model.DTO.UserDTO;
import uniquindio.Model.DTO.UserPostLoginDTO;

public class Sesion {

    private static ClientSesionDTO usuarioActual;

    private Sesion() {
    }

    public static void iniciar(ClientSesionDTO usuario) {
        usuarioActual = usuario;
    }

    public static ClientSesionDTO getUsuarioActual() {
        return usuarioActual;
    }

    public static boolean haySesionActiva() {
        return usuarioActual != null;
    }

    public static void cerrar() {
        usuarioActual = null;
    }
}
