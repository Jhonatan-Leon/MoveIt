package uniquindio.Utils;

import uniquindio.Errors.ControllException;
import uniquindio.Mappers.ClientMapper;
import uniquindio.Model.Client;
import uniquindio.Model.DTO.ClientSesionDTO;
import uniquindio.Model.DTO.UserPostLoginDTO;
import uniquindio.Model.Direccion;
import uniquindio.Model.Envio;

import java.util.List;

public class JavaFxAux {
    public static List<Envio> obtenerListEnvios (UserPostLoginDTO user) throws ControllException.UserNotFound {
        Client client = ClientMapper.sesionToEntity(user);
        List<Envio> lista = client.getListEnvio();
        return lista;
    }

    public static List<Direccion> obtenerDirecciones (UserPostLoginDTO user) throws ControllException.UserNotFound {
        Client client = ClientMapper.sesionToEntity(user);
        List<Direccion> lista = client.getListDireccion();
        return lista;
    }
}
