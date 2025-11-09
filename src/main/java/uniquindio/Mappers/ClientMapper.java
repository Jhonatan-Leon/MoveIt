package uniquindio.Mappers;

import uniquindio.Model.Client;
import uniquindio.Model.DTO.ClientDTO;

public class ClientMapper {

    public static ClientDTO toDTO(Client client) {
        if (client == null) return null;

        return new ClientDTO(
                client.getId(),
                client.getNombreCompleto(),
                client.getEmail(),
                client.getTelefono(),
                client.isEstado()
        );
    }

}
