package uniquindio.Utils;

import uniquindio.Errors.ControllException;
import uniquindio.Mappers.ClientMapper;
import uniquindio.Model.Client;
import uniquindio.Model.DTO.ClientSesionDTO;
import uniquindio.Model.Envio;

import java.util.List;

public class JavaFxAux {
    public static List<Envio> obtenerListEnvios (ClientSesionDTO user) throws ControllException.UserNotFound {
        Client client = ClientMapper.sesionToEntity(user);
        List<Envio> lista = client.getListEnvio();
        return lista;
    }
}
