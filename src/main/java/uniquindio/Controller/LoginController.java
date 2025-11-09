package uniquindio.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import uniquindio.Errors.ControllException;
import uniquindio.Facade.UserFacade;
import uniquindio.Helper.Sesion;
import uniquindio.Mappers.ClientMapper;
import uniquindio.Model.Client;
import uniquindio.Model.DTO.ClientSesionDTO;
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
    private void initialize() {
        labelError.setVisible(false);
    }

    @FXML
    private void iniciarSesion () {
        UserLoginDTO loginDTO = new UserLoginDTO(txtId.getText(), txtContrasena.getText());

        try {
            User usuario = UserFacade.getInstance().login(loginDTO);
            if (usuario  instanceof Client client) {
                ClientSesionDTO clientDTO = ClientMapper.toDTO(client);
                Sesion.iniciar(clientDTO);
                // Navegacion.cambiarVista();
            }
            if (usuario instanceof Repartidor repartidor) {

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


}
