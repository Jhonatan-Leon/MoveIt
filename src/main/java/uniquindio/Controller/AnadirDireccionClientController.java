package uniquindio.Controller;

import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import netscape.javascript.JSObject;
import uniquindio.Errors.ControllException;
import uniquindio.Helper.Sesion;
import uniquindio.Mappers.ClientMapper;
import uniquindio.Model.Client;
import uniquindio.Model.DTO.UserPostLoginDTO;
import uniquindio.Model.Direccion;
import uniquindio.Model.Envio;
import uniquindio.Services.DireccionServices;
import uniquindio.Services.ReportService;
import uniquindio.Utils.JSBridge;

import java.io.File;
import java.util.List;

public class AnadirDireccionClientController {

    private UserPostLoginDTO user = Sesion.getUsuarioActual();
    private Double latitudSeleccionada;
    private Double longitudSeleccionada;

    @FXML private AnchorPane anchorPerfil;
    @FXML private Button btnEditarDatos;
    @FXML private Button btnConfirmar;
    @FXML private AnchorPane rootPane;
    @FXML private Button btnPerfil;
    @FXML private Button btnPerfil1;
    @FXML private AnchorPane anchorOpcionesUsuario;
    @FXML private TextField txtFdCedula;
    @FXML private TextField txtFdTelefono;
    @FXML private TextField txtFdCorreo;
    @FXML private Text txtNombreCompleto;
    @FXML private Button btnReporte;


    @FXML private TextField txtAlias;
    @FXML private TextField txtCiudad;
    @FXML private TextField txtCalle;
    @FXML private WebView webViewMapa;
    @FXML private TextField txtCoordenadas;
    @FXML private AnchorPane anchorMensaje;

    // ya ni se cuantas veces he tenido que cambiar cosas del controlador para que funcione

    public void initialize() throws ControllException.UserNotFound {
        // Inicializar info del usuario
        btnPerfil.setText(user.toString());
        btnPerfil1.setText(user.toString());
        anchorOpcionesUsuario.setVisible(false);
        anchorPerfil.setVisible(false);

        // Configurar WebView
        WebEngine engine = webViewMapa.getEngine();
        JSBridge bridge = new JSBridge(this);

        engine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                // Obtener el window de JS
                JSObject window = (JSObject) engine.executeScript("window");
                window.setMember("javaConnector", bridge);
            }
        });

        engine.load(getClass().getResource("/Elementos/mapa.html").toExternalForm());
    }

    public void irDirecciones () {
        Navegacion.cambiarVista("/Vista/DireccionesClient.fxml");
    }


    public void setCoordenadas(double lat, double lng) {
        this.latitudSeleccionada = lat;
        this.longitudSeleccionada = lng;

        Platform.runLater(() -> {
            txtCoordenadas.setText(lat + ", " + lng);
        });
    }

    public void anadirDireccion() throws ControllException.UserCreate, ControllException.UserNotFound {
        Direccion newDireccion = new Direccion(txtAlias.getText(),txtCalle.getText(),txtCiudad.getText(),txtCoordenadas.getText());
        Client client = ClientMapper.sesionToEntity(user);
        DireccionServices.addDireccion(newDireccion,client);
        anchorMensaje.setVisible(true);
    }

    // Métodos UI
    public void extenderUsuario() { anchorOpcionesUsuario.setVisible(true); }
    public void minimizarUsuario() { anchorOpcionesUsuario.setVisible(false); }
    public void quitarPerfil() { anchorPerfil.setVisible(false); }

    public void actualizarCoordenadas(double lat, double lng) {
        txtCoordenadas.setText("Lat: " + lat + "   Lng: " + lng);
    }

    public void irAtras() { Navegacion.volver(); }

    public void irAPerfil() {
        anchorPerfil.setVisible(true);
        txtNombreCompleto.setText(user.getNombreCompleto());
        txtFdCedula.setText(user.getId());
        txtFdTelefono.setText(user.getTelefono());
        txtFdCorreo.setText(user.getEmail());
    }

    public void irMain() { Navegacion.cambiarVista("/Vista/MainPageClient.fxml"); }

    public void editarDatos() {
        txtFdCedula.setEditable(true);
        txtFdCorreo.setEditable(true);
        txtFdTelefono.setEditable(true);
        btnEditarDatos.setVisible(false);
        btnConfirmar.setVisible(true);
    }

    public void confirmarCambios() {
        txtFdCedula.setEditable(false);
        user.setId(txtFdCedula.getText());
        txtFdCorreo.setEditable(false);
        user.setEmail(txtFdCorreo.getText());
        txtFdTelefono.setEditable(false);
        user.setTelefono(txtFdTelefono.getText());
        btnConfirmar.setVisible(false);
        btnEditarDatos.setVisible(true);
    }

    public void cerrarSesion() {
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

    public void irEnvios () {
        Navegacion.cambiarVista("/Vista/EnviosUser.fxml");
    }

    public void irCotizar () {
        Navegacion.cambiarVista("/Vista/CotizarClient.fxml");
    }
}
