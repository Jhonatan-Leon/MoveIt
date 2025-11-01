package edu.co.Model;

public class Repartidor {
    private String Id;
    private String NombreCompeto;
    private TipoDocumento tipoDocumento;
    private String email;
    private String password;
    private String NumeroDocumento;
    private String ZonaCobertura;
    private boolean estado;

    public Repartidor(String Id, String NombreCompleto, TipoDocumento tipoDocumento, String email, String password, String NumeroDocumento, String ZonaCobertura, boolean estado){
        this.Id = Id;
        this.NombreCompeto = NombreCompeto;
        this.tipoDocumento = tipoDocumento;
        this.email = email;
        this.password = password;
        this.NumeroDocumento = NumeroDocumento;
        this.ZonaCobertura = ZonaCobertura;
        this.estado = estado;
    }

    public String getId() {
        return Id;
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

    public void setId(String id) {
        Id = id;
    }

    public String getNombreCompeto() {
        return NombreCompeto;
    }

    public void setNombreCompeto(String nombreCompeto) {
        NombreCompeto = nombreCompeto;
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

    public String getZonaCobertura() {
        return ZonaCobertura;
    }

    public boolean isEstado() {return estado;}

    public void setEstado(boolean estado) {this.estado = estado;}

    public void setZonaCobertura(String zonaCobertura) {
        ZonaCobertura = zonaCobertura;
    }
}
