package uniquindio.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

import javafx.scene.control.TextField;
import uniquindio.Model.DTO.UserLoginDTO;
import uniquindio.Utils.LoginDTO;


public class LoginController {
    @FXML
    private TextField txtCorreo;
    @FXML
    private PasswordField txtContrasena;

    @FXML
    private void iniciarSesion(ActionEvent event) {
        UserLoginDTO dto = new UserLoginDTO(txtCorreo.getText(), txtContrasena.getText());

        UsuarioDTO usuario = loginService.autenticar(dto);

        if (usuario == null) {
            mostrarError("Correo o contraseña incorrectos");
            return;
        }

        Sesion.setUsuarioActual(usuario);
        navegarSegunRol(usuario);
    }


}
