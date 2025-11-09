package uniquindio.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

import javafx.scene.control.TextField;
import uniquindio.Errors.ControllException;
import uniquindio.Facade.UserFacade;
import uniquindio.Helper.Sesion;
import uniquindio.Mappers.ClientMapper;
import uniquindio.Model.Client;
import uniquindio.Model.DTO.ClientDTO;
import uniquindio.Model.DTO.UserLoginDTO;
import uniquindio.Model.Repartidor;
import uniquindio.Model.User;


public class LoginController {
    @FXML
    private TextField txtId;
    @FXML
    private PasswordField txtContrasena;

    @FXML
    private void iniciarSesion () throws ControllException.ErrorServer {
        UserLoginDTO loginDTO = new UserLoginDTO(txtId.getText(), txtContrasena.getText());

        try {
            User usuario = UserFacade.getInstance().login(loginDTO);
            if (usuario  instanceof Client client) {
                ClientDTO clientDTO = ClientMapper.toDTO(client);
                Sesion.iniciar(clientDTO);
                // Navegacion.cambiarVista();
            }
            if (usuario instanceof Repartidor repartidor) {

            }
            // FALTA AÑADIR LO DE ADMINS

        } catch (Exception e) {
            throw new ControllException.ErrorServer("Error interno del servidor");
        }
    }


}
