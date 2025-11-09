package uniquindio.Helper;

import uniquindio.Model.DTO.UserDTO;
import uniquindio.Model.DTO.UserPostLoginDTO;

public class Sesion {

    private static UserPostLoginDTO usuarioActual;

    private Sesion() {
    }

    public static void iniciar(UserPostLoginDTO usuario) {
        usuarioActual = usuario;
    }

    public static UserPostLoginDTO getUsuarioActual() {
        return usuarioActual;
    }

    public static boolean haySesionActiva() {
        return usuarioActual != null;
    }

    public static void cerrar() {
        usuarioActual = null;
    }
}
