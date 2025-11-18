package uniquindio.Controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import uniquindio.Helper.Sesion;

public class MainPageAdminController {
    public void cerrarSesion () {
        Navegacion.volver();
        Sesion.cerrar();
    }

    @FXML
    private void irGestionarClientes(ActionEvent event) {
        Navegacion.cambiarVista("/Vista/GestionarClientesAdmin.fxml");
    }

    @FXML
    private void irGestionarRepartidores(ActionEvent event) {
        Navegacion.cambiarVista("/Vista/GestionRepartidoresAdmin.fxml");
    }

    @FXML
    private void irAsignarEnvios(ActionEvent event) {
        Navegacion.cambiarVista("/Vista/AsignarEnviosAdmin.fxml");
    }

    @FXML
    private void irPanelMetricas(ActionEvent event) {
        Navegacion.cambiarVista("/Vista/PanelMetricasAdmin.fxml");
    }

}
