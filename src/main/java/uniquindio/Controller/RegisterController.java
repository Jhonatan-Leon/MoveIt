package uniquindio.Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class RegisterController {
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtNombreCompleto;
    @FXML
    private TextField txtCorreo;
    @FXML
    private PasswordField txtContrasena;
    @FXML
    private AnchorPane rootPane;

    @FXML
    private void initialize() {
        Platform.runLater(() -> rootPane.requestFocus());
    }

    @FXML
    public void registrarse () {
        // nada xd, hay q crear llamar los metodos pa crear el usuario, unicamente client se registra, el admin deberia crear los repartidores
    }

    @FXML
    public void irAtras () {
        Navegacion.volver();
    }

}
