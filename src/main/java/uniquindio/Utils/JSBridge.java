package uniquindio.Utils;

import uniquindio.Controller.AnadirDireccionClientController;

public class JSBridge {
    private final AnadirDireccionClientController controller;

    public JSBridge(AnadirDireccionClientController controller) {
        this.controller = controller;
    }

    // Este método será llamado desde JavaScript
    public void onMapClick(double lat, double lng) {
        controller.setCoordenadas(lat, lng);
    }
}
