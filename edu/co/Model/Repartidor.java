package edu.co.Model;

public class Repartidor {
    private String Id;
    private String NombreCompeto;
    private TipoDocumento tipoDocumento;
    private String NumeroDocumento;
    private String ZonaCobertura;

    public Repartidor(String Id, String NombreCompleto, TipoDocumento tipoDocumento, String NumeroDocumento, String ZonaCobertura){
        this.Id = Id;
        this.NombreCompeto = NombreCompeto;
        this.tipoDocumento = tipoDocumento;
        this.NumeroDocumento = NumeroDocumento;
        this.ZonaCobertura = ZonaCobertura;
    }

    public String getId() {
        return Id;
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

    public void setZonaCobertura(String zonaCobertura) {
        ZonaCobertura = zonaCobertura;
    }
}
