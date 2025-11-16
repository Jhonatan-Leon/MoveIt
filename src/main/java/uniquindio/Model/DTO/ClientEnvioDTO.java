package uniquindio.Model.DTO;

import uniquindio.Model.Envio;

import java.util.ArrayList;

public class ClientEnvioDTO {
    private ArrayList<Envio> listEnvio;

    public ClientEnvioDTO(ArrayList<Envio> listEnvio) {
        this.listEnvio = listEnvio;
    }

    public ArrayList<Envio> getListEnvio() {
        return listEnvio;
    }

    public void setListEnvio(ArrayList<Envio> listEnvio) {
        this.listEnvio = listEnvio;
    }
}

