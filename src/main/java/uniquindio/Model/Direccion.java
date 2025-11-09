package uniquindio.Model;

public class Direccion {
    private String IdDireccion;
    private String Alias;
    private String Calle;
    private String Ciudad;
    private String Coordenadas;

    public Direccion(String IdDireccion, String Alias, String Calle, String Ciudad, String Coordenadas) {
        this.IdDireccion = IdDireccion;
        this.Alias = Alias;
        this.Calle = Calle;
        this.Ciudad = Ciudad;
        this.Coordenadas = Coordenadas;

    }

    public String getIdDireccion() {
        return IdDireccion;
    }

    public void setIdDireccion(String idDireccion) {
        IdDireccion = idDireccion;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public String getCalle() {
        return Calle;
    }

    public void setCalle(String calle) {
        Calle = calle;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String ciudad) {
        Ciudad = ciudad;
    }

    public String getCoordenadas() {
        return Coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        Coordenadas = coordenadas;
    }
}

