
package Java.co.Utils;

import Java.co.Model.ClassBuilder.UserBuilder;
import Java.co.Model.DTO.UserDTO;
import Java.co.Model.Direccion;
import Java.co.Model.Client;

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