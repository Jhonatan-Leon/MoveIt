package uniquindio.Controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import uniquindio.Errors.ControllException;
import uniquindio.Helper.Sesion;
import uniquindio.Mappers.ClientMapper;
import uniquindio.Model.Client;
import uniquindio.Model.DTO.UserPostLoginDTO;
import uniquindio.Model.Direccion;
import uniquindio.Model.Envio;
import uniquindio.Services.ReportService;
import uniquindio.Utils.JavaFxAux;

import java.io.File;
import java.util.List;

public class DireccionesClientController {
    UserPostLoginDTO user = Sesion.getUsuarioActual();

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

    @FXML private TableView<Direccion> tableDirecciones;
    @FXML private TableColumn colId;
    @FXML private TableColumn colAlias;
    @FXML private TableColumn colCalle;
    @FXML private TableColumn colCiudad;
    @FXML private TableColumn colCoordenadas;

    public void initialize () throws ControllException.UserNotFound {
        colId.setCellValueFactory(new PropertyValueFactory<>("IdDireccion"));
        colAlias.setCellValueFactory(new PropertyValueFactory<>("Alias"));
        colCalle.setCellValueFactory(new PropertyValueFactory<>("Calle"));

        colCiudad.setCellValueFactory(new PropertyValueFactory<>("Ciudad"));
        colCoordenadas.setCellValueFactory(new PropertyValueFactory<>("Coordenadas"));
        List<Direccion> listDireccion = JavaFxAux.obtenerDirecciones(user);
        tableDirecciones.setItems(FXCollections.observableList(listDireccion));
        // COMUN
        btnPerfil.setText(user.toString());
        btnPerfil1.setText(user.toString());
        anchorOpcionesUsuario.setVisible(false);
        anchorPerfil.setVisible(false);
    }

    public void irAnadirDireccion () {
        Navegacion.cambiarVista("/Vista/AnadirDireccionClient.fxml");
    }

    // METODOS COMUNES

    public void extenderUsuario () {
        anchorOpcionesUsuario.setVisible(true);
    }

    public void minimizarUsuario () {
        anchorOpcionesUsuario.setVisible(false);
    }

    public void quitarPerfil () {
        anchorPerfil.setVisible(false);
    }

    public void irMain () { Navegacion.cambiarVista("/Vista/MainPageClient.fxml"); }

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

    public void irEnvios () {
        Navegacion.cambiarVista("/Vista/EnviosUser.fxml");
    }

    public void irCotizar () {
        Navegacion.cambiarVista("/Vista/CotizarClient.fxml");
    }

}
