package uniquindio.Mappers;

import uniquindio.Errors.ControllException;
import uniquindio.Facade.UserFacade;
import uniquindio.Model.Client;
import uniquindio.Model.DTO.ClientEnvioDTO;
import uniquindio.Model.DTO.ClientSesionDTO;
import uniquindio.Model.DTO.UserPostLoginDTO;

public class ClientMapper {

    public static ClientSesionDTO toDTO(Client client) {
        if (client == null) return null;

        return new ClientSesionDTO(
                client.getNumeroDocumento(),
                client.getNombreCompleto(),
                client.getEmail(),
                client.getTelefono(),
                client.isEstado()
        );
    }

    public static ClientEnvioDTO sesionToClientEnvioDTO (ClientSesionDTO sesion) throws ControllException.UserNotFound {
        int id = Integer.parseInt(sesion.getId());
        Client user = UserFacade.getInstance().getUserById(id);
        if (user == null) return null;

        return new ClientEnvioDTO(user.getListEnvio());
    }

    public static Client sesionToEntity (UserPostLoginDTO user) throws ControllException.UserNotFound {
        if (user == null) return null;
        else {
            Client client = UserFacade.getInstance().getUser(user.getEmail());
            return client;
        }
    }

}
