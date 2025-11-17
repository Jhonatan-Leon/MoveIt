package uniquindio.Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import uniquindio.Errors.ControllException;
import uniquindio.Facade.UserFacade;
import uniquindio.Helper.Sesion;
import uniquindio.Mappers.ClientMapper;
import uniquindio.Mappers.RepartidorMapper;
import uniquindio.Model.Client;
import uniquindio.Model.DTO.ClientSesionDTO;
import uniquindio.Model.DTO.RepartidorSesionDTO;
import uniquindio.Model.DTO.UserLoginDTO;
import uniquindio.Model.Repartidor;
import uniquindio.Model.User;


public class LoginController {
    @FXML
    private TextField txtId;
    @FXML
    private PasswordField txtContrasena;
    @FXML
    private Text labelError;
    @FXML
    private AnchorPane rootPane;

    @FXML
    private void initialize() {
        labelError.setVisible(false);
        Platform.runLater(() -> rootPane.requestFocus());

    }

    @FXML
    private void iniciarSesion () {
        UserLoginDTO loginDTO = new UserLoginDTO(txtId.getText(), txtContrasena.getText());
        try {
            User usuario = UserFacade.getInstance().login(loginDTO);
            if (usuario  instanceof Client client) {
                ClientSesionDTO clientDTO = ClientMapper.toDTO(client);
                Sesion.iniciar(clientDTO);
                Navegacion.cambiarVista("/Vista/MainPageClient.fxml");
            }
            if (usuario instanceof Repartidor repartidor) {
                RepartidorSesionDTO repartidorDTO = RepartidorMapper.toDTO(repartidor);
                Sesion.iniciar(repartidorDTO);
                Navegacion.cambiarVista("/Vista/Repartidor/MainPageRepartidor.fxml");
            }
            // FALTA AÑADIR LO DE ADMINS

        } catch (ControllException.UserNotFound e) {
            labelError.setVisible(true);
            labelError.setText(e.getMessage());
        } catch (ControllException.ErrorServer e) {
            labelError.setVisible(true);
            labelError.setText("Error interno. Intenta más tarde.");
        }
    }

    @FXML
    private void irARegister () {
        Navegacion.cambiarVista("/Vista/Register.fxml");
    }

}
