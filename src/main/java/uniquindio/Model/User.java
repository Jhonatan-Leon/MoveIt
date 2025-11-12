package uniquindio.Model;

abstract public class User {
    protected String id;
    protected String nombreCompleto;
    protected String email;
    protected String password;
    protected String telefono;
    protected boolean estado;
    protected TipoDocumento tipoDocumento;
    protected String NumeroDocumento;

    public User(String Id, String NombreCompleto, String email, String password, String telefono,
                TipoDocumento tipoDocumento, String NumeroDocumento, boolean estado) {
        this.id = Id;
        this.nombreCompleto = NombreCompleto;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.tipoDocumento = tipoDocumento;
        this.NumeroDocumento = NumeroDocumento;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        nombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return NumeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        NumeroDocumento = numeroDocumento;
    }
}
