package uniquindio.Mappers;

import uniquindio.Model.Admin;
import uniquindio.Model.DTO.AdminSesionDTO;

public class AdminMapper {
    public static AdminSesionDTO toDTO(Admin admin) {
        if (admin == null) return null;

        return new AdminSesionDTO(
                admin.getNumeroDocumento(),
                admin.getNombreCompleto(),
                admin.getEmail(),
                admin.getTelefono(),
                admin.isEstado()
        );
    }
}
