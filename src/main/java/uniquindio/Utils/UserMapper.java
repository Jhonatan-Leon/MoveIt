
package uniquindio.Utils;

import uniquindio.Model.ClassBuilder.UserBuilder;
import uniquindio.Model.DTO.UserDTO;
import uniquindio.Model.Direccion;
import uniquindio.Model.Client;

public class UserMapper {
    private UserMapper() {
    }

    public static UserDTO toDTO(Client client) {
        if (client == null) {
            return null;
        }
        Direccion direccion = null;
        if (client.getListDireccion() != null && !client.getListDireccion().isEmpty()) {
            direccion = client.getListDireccion().get(0);
        }

        return new UserDTO(
                client.getId(),
                client.getNombreCompleto(),
                client.getEmail(),
                client.getPassword(),
                client.getTelefono(),
                direccion,
                client.isEstado()
        );
    }

    public static Client toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        UserBuilder builder = new UserBuilder(
                String.valueOf(userDTO.getId()),
                userDTO.getNombreCompleto(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                null,// Definir si es necesario ponerlo
                null // Definir si es necesario ponerlo
        );

        if (userDTO.getDireccion() != null) {
            builder.AgregarDireccion(userDTO.getDireccion());
        }

        return new Client(builder);
    }

}