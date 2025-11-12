package uniquindio.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import uniquindio.Helper.Sesion;
import uniquindio.Model.DTO.UserPostLoginDTO;

public class MainPageClientController {
    // INSTANCIA DEL LOGIN
    UserPostLoginDTO user = Sesion.getUsuarioActual();

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField txtIdPedido;
    @FXML
    private Button btnPerfil;

    public void initialize () {
        btnPerfil.setText(user.toString());
    }
}
