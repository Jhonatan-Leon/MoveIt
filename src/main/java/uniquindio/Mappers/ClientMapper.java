package uniquindio.Mappers;

import uniquindio.Model.Client;
import uniquindio.Model.DTO.ClientSesionDTO;

public class ClientMapper {

    public static ClientSesionDTO toDTO(Client client) {
        if (client == null) return null;

        return new ClientSesionDTO(
                client.getId(),
                client.getNombreCompleto(),
                client.getEmail(),
                client.getTelefono(),
                client.isEstado()
        );
    }

}
