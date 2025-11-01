
package edu.co.Utils;

import edu.co.Model.ClassBuilder.UserBuilder;
import edu.co.Model.DTO.UserDTO;
import edu.co.Model.Direccion;
import edu.co.Model.User;

public class UserMapper {
    private UserMapper() {
    }

    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        Direccion direccion = null;
        if (user.getListDireccion() != null && !user.getListDireccion().isEmpty()) {
            direccion = user.getListDireccion().get(0);
        }

        return new UserDTO(
                user.getId(),
                user.getNombreCompleto(),
                user.getEmail(),
                user.getPassword(),
                user.getTelefono(),
                direccion,
                user.isEstado()
        );
    }

    public static User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        UserBuilder builder = new UserBuilder(
                userDTO.getId(),
                userDTO.getNombreCompleto(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getTelefono(),
                userDTO.isEstado()
        );

        if (userDTO.getDireccion() != null) {
            builder.AgregarDireccion(userDTO.getDireccion());
        }

        return new User(builder);
    }
}