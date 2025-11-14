package uniquindio.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
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
    @FXML
    private Text txtMensajePostRastreo;
    @FXML
    private TextField txtFdCedula;
    @FXML
    private TextField txtFdTelefono;
    @FXML
    private TextField txtFdCorreo;
    @FXML
    private Text txtNombreCompleto;
    @FXML
    private AnchorPane anchorPerfil;
    @FXML
    private Button btnEditarDatos;
    @FXML
    private Button btnConfirmar;

    public void initialize () {
        btnPerfil.setText(user.toString());
        btnPerfil1.setText(user.toString());
        anchorOpcionesUsuario.setVisible(false);
        anchorPerfil.setVisible(false);
    }

    public void extenderUsuario () {
        anchorOpcionesUsuario.setVisible(true);
    }

    public void minimizarUsuario () {
        anchorOpcionesUsuario.setVisible(false);
    }

    public void irAPerfil () {
        anchorPerfil.setVisible(true);
        txtNombreCompleto.setText(user.getNombreCompleto());
        txtFdCedula.setText(user.getId());
        txtFdTelefono.setText(user.getTelefono());
        txtFdCorreo.setText(user.getEmail());
    }

    public void editarDatos () {
        txtFdCedula.setEditable(true);
        txtFdCorreo.setEditable(true);
        txtFdTelefono.setEditable(true);
        btnEditarDatos.setVisible(false);
        btnConfirmar.setVisible(true);
    }

    public void quitarPerfil () {
        anchorPerfil.setVisible(false);
    }

    public void confirmarCambios () {
        txtFdCedula.setEditable(false);
        user.setId(txtFdCedula.getText());
        txtFdCorreo.setEditable(false);
        user.setEmail(txtFdCorreo.getText());
        txtFdTelefono.setEditable(false);
        user.setTelefono(txtFdTelefono.getText());
        btnConfirmar.setVisible(false);
        btnEditarDatos.setVisible(true);
    }

    public void cerrarSesion () {
        Navegacion.cambiarVista("/Vista/Login.fxml");
        Navegacion.reiniciarHistorial();
        Sesion.cerrar();
    }

}
