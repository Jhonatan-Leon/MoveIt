package uniquindio.Model;

public class Admin extends User {
    public Admin(String Id, String NombreCompleto, String email, String password, String telefono, TipoDocumento tipoDocumento, String NumeroDocumento, boolean estado) {
        super(Id, NombreCompleto, email, password, telefono, tipoDocumento, NumeroDocumento, estado);
    }
}