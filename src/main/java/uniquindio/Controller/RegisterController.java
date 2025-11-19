package uniquindio.Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import uniquindio.Facade.UserFacade;
import uniquindio.Model.ClassBuilder.UserBuilder;
import uniquindio.Model.Client;

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
        try {
            if (txtId.getText().isEmpty() || txtNombreCompleto.getText().isEmpty() ||
                    txtCorreo.getText().isEmpty() || txtContrasena.getText().isEmpty()) {
                javafx.scene.control.Alert alerta = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
                alerta.setTitle("Registro incompleto");
                alerta.setHeaderText(null);
                alerta.setContentText("Por favor, complete todos los campos obligatorios.");
                alerta.showAndWait();
                return;
            }

            UserBuilder builder = new UserBuilder(
                    txtId.getText(),
                    txtNombreCompleto.getText(),
                    txtCorreo.getText(),
                    txtContrasena.getText(),
                    null, // TipoDocumento, si no lo tienes, se puede dejar null
                    txtId.getText() // Número de documento, aquí asumo que es igual a ID
            );

            if (!txtTelefono.getText().isEmpty()) {
                builder.withtelefono(txtTelefono.getText());
            }

            Client nuevoCliente = builder.build();

            UserFacade.getInstance().addUser(nuevoCliente);

            javafx.scene.control.Alert alertaExito = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
            alertaExito.setTitle("Registro exitoso");
            alertaExito.setHeaderText(null);
            alertaExito.setContentText("Usuario registrado con éxito: " + nuevoCliente.getNombreCompleto());
            alertaExito.showAndWait();

            txtId.clear();
            txtNombreCompleto.clear();
            txtCorreo.clear();
            txtContrasena.clear();
            txtTelefono.clear();


        } catch (Exception e) {
            javafx.scene.control.Alert alertaError = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alertaError.setTitle("Error al registrar");
            alertaError.setHeaderText(null);
            alertaError.setContentText("No se pudo registrar el usuario: " + e.getMessage());
            alertaError.showAndWait();

            e.printStackTrace();
        }    }

    @FXML
    public void irAtras () {
        Navegacion.volver();
    }

}
