package uniquindio.Model.DTO;

public class ClientSesionDTO extends UserPostLoginDTO {

    public ClientSesionDTO(String id, String nombreCompleto, String email, String telefono, boolean estado) {
        super(id, nombreCompleto, email, telefono, estado);
    }
}
