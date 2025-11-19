package uniquindio.Model.DTO;

public class AdminSesionDTO extends UserPostLoginDTO{
    public AdminSesionDTO(String id, String nombreCompleto, String email, String telefono, boolean estado) {
        super(id, nombreCompleto, email, telefono, estado);
    }
}
