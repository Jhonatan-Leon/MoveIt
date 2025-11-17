package uniquindio.Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import uniquindio.Facade.EnvioFacade;
import uniquindio.Helper.Sesion;
import uniquindio.Mappers.ClientMapper;
import uniquindio.Model.Client;
import uniquindio.Model.DTO.ClientSesionDTO;
import uniquindio.Model.DTO.UserPostLoginDTO;
import uniquindio.Model.Envio;
import uniquindio.Services.ReportService;
import javafx.scene.Node;
import java.io.File;
import java.util.List;

public class MainPageClientController {
    // INSTANCIA DEL LOGIN
    UserPostLoginDTO user = Sesion.getUsuarioActual();

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
    @FXML
    private Button btnReporte;

    public void initialize () {
        Platform.runLater(() -> rootPane.requestFocus());
        btnPerfil.setText(user.toString());
        btnPerfil1.setText(user.toString());
        anchorOpcionesUsuario.setVisible(false);
        anchorPerfil.setVisible(false);
    }

    public void irDirecciones () {
        Navegacion.cambiarVista("/Vista/DireccionesClient.fxml");
    }

    public void irEnvios () {
        Navegacion.cambiarVista("/Vista/EnviosUser.fxml");
    }

    public void rastrear () {
        String mensaje = EnvioFacade.obtenerMensajeRastreo(txtIdPedido.getText());
        txtMensajePostRastreo.setText(mensaje);
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


    public void descargarReporte() {
        try {
            Client client = ClientMapper.sesionToEntity(user);
            List<Envio> envios = client.getListEnvio();

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Guardar reporte PDF");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("PDF Files", "*.pdf")
            );
            fileChooser.setInitialFileName("Reporte_Envios.pdf");

            File archivo = fileChooser.showSaveDialog(btnReporte.getScene().getWindow());

            if (archivo != null) {
                ReportService.exportarEnviosPDF(envios, archivo.getAbsolutePath());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
