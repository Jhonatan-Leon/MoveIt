package uniquindio.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

public class Navegacion {
    private static Stage stagePrincipal;
    private static Stack<Parent> historial = new Stack<>();

    public static void setStagePrincipal(Stage stage) {
        stagePrincipal = stage;
    }

    public static void cambiarVista(String fxml) {
        try {
            // Cargar nueva vista
            FXMLLoader loader = new FXMLLoader(Navegacion.class.getResource(fxml));
            Parent nuevaVista = loader.load();

            // Guardar vista actual en el stack
            if (stagePrincipal.getScene() != null) {
                historial.push(stagePrincipal.getScene().getRoot());
            }

            // Establece una nueva vista
            stagePrincipal.getScene().setRoot(nuevaVista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void volver() {
        if (!historial.isEmpty()) {
            Parent vistaAnterior = historial.pop();
            stagePrincipal.getScene().setRoot(vistaAnterior);
        }
    }

    public static void reiniciarHistorial() {
        historial.clear();
    }

}
