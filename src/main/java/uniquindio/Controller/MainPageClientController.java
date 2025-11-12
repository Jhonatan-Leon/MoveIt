package uniquindio.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import uniquindio.Helper.Sesion;
import uniquindio.Model.DTO.ClientSesionDTO;
import uniquindio.Model.DTO.UserPostLoginDTO;

public class MainPageClientController {
    // INSTANCIA DEL LOGIN
    ClientSesionDTO user = Sesion.getUsuarioActual();

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField txtIdPedido;
    @FXML
    private Button btnPerfil;
    @FXML
    private Button btnPerfil1;
    @FXML
    private AnchorPane anchorOpcionesUsuario;

    public void initialize () {
        btnPerfil.setText(user.toString());
        btnPerfil1.setText(user.toString());
        anchorOpcionesUsuario.setVisible(false);
    }

    public void extenderUsuario () {
        anchorOpcionesUsuario.setVisible(true);
    }

    public void minimizarUsuario () {
        anchorOpcionesUsuario.setVisible(false);
    }

    public void irAPerfil () {
        //Navegacion.cambiarVista("");
    }

    public void cerrarSesion () {
        Navegacion.cambiarVista("/Vista/Login.fxml");
        Navegacion.reiniciarHistorial();
        Sesion.cerrar();
    }

}
