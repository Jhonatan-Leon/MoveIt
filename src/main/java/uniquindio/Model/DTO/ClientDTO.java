package uniquindio.Model.DTO;

public class ClientDTO extends UserPostLoginDTO {

    public ClientDTO(String id, String nombreCompleto, String email, String telefono, boolean estado) {
        super(id, nombreCompleto, email, telefono, estado);
    }
}
