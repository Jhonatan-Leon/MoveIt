package uniquindio.Utils;

import javafx.application.Platform;
import uniquindio.Controller.CotizarClientController;

public class JSBrigeRuta {

    private final CotizarClientController controller;

    public JSBrigeRuta(CotizarClientController controller) {
        this.controller = controller;
    }

    // Este método lo llamamos desde JS
    public void enviarRuta(String inicio, String fin, String distancia, String duracion) {
        Platform.runLater(() -> {
            controller.recibirDatosRuta(inicio, fin, distancia, duracion);
        });
    }
}
