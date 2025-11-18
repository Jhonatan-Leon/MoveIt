package uniquindio.Controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import uniquindio.Errors.ControllException;
import uniquindio.Helper.Sesion;
import uniquindio.Mappers.ClientMapper;
import uniquindio.Model.Client;
import uniquindio.Model.DTO.UserPostLoginDTO;
import uniquindio.Model.Envio;
import uniquindio.Model.TipoEstado;
import uniquindio.Services.EnvioServices;
import uniquindio.Services.ReportService;
import uniquindio.Utils.JavaFxAux;

import java.io.File;
import java.util.Date;
import java.util.List;

public class EnvioUserController {

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

    // TABLA DE ENVIOS
    @FXML private TableView<Envio> tableEnvios;
    @FXML private TableColumn colId;
    @FXML private TableColumn colTipoDireccion;
    @FXML private TableColumn colEstado;
    @FXML private TableColumn colCosto;
    @FXML private TableColumn colFechaEstimada;
    @FXML private TableColumn colFechaCreacion;
// TABLA CONTENIDO
    @FXML private TableView<uniquindio.Model.Package> tableContenido;
    @FXML private TableColumn<uniquindio.Model.Package, String> colIdPaquete;
    @FXML private TableColumn<uniquindio.Model.Package, Float> colPeso;
    @FXML private TableColumn<uniquindio.Model.Package, Float> colAlto;
    @FXML private TableColumn<uniquindio.Model.Package, Float> colAncho;
    @FXML private TableColumn<uniquindio.Model.Package, Float> colVolumen;
    // FILTRO
    @FXML private DatePicker dpFechaInicio;
    @FXML private DatePicker dpFechaFin;
    @FXML private ChoiceBox<TipoEstado> chBxEstado;


    public EnvioUserController() throws ControllException.UserNotFound {
    }

    public void initialize () throws ControllException.UserNotFound {
        // TABLA ENVIOS
        colId.setCellValueFactory(new PropertyValueFactory<>("idEnvio"));
        colTipoDireccion.setCellValueFactory(new PropertyValueFactory<>("tipoDireccion"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));
        colFechaCreacion.setCellValueFactory(new PropertyValueFactory<>("fechaCreación"));
        colFechaEstimada.setCellValueFactory(new PropertyValueFactory<>("fechaEstimada"));
        //TABLA CONTENIDO
        colIdPaquete.setCellValueFactory(new PropertyValueFactory<>("idPaquete"));
        colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        colAlto.setCellValueFactory(new PropertyValueFactory<>("alto"));
        colAncho.setCellValueFactory(new PropertyValueFactory<>("ancho"));
        colVolumen.setCellValueFactory(new PropertyValueFactory<>("volumen"));
        // MOSTRAR TABLA DE CONTENIDO CON SELECCION
        tableEnvios.getSelectionModel().selectedItemProperty().addListener((obs, oldEnvio, envioSeleccionado) -> {
            if (envioSeleccionado != null) {
                tableContenido.setItems(
                        FXCollections.observableList(envioSeleccionado.getListpaquete())
                );
            }
        });

        List<Envio> listEnvios = JavaFxAux.obtenerListEnvios(user);
        btnPerfil.setText(user.toString());
        btnPerfil1.setText(user.toString());
        anchorOpcionesUsuario.setVisible(false);
        anchorPerfil.setVisible(false);
        tableEnvios.setItems(FXCollections.observableList(listEnvios));

        chBxEstado.getItems().setAll(TipoEstado.values());
        chBxEstado.setValue(null);

    }

    public void filtrarEnvios() {
        try {
            Client cliente = ClientMapper.sesionToEntity(user);

            Date fechaInicio = dpFechaInicio.getValue() != null ? java.sql.Date.valueOf(dpFechaInicio.getValue()) : null;
            Date fechaFin = dpFechaFin.getValue() != null ? java.sql.Date.valueOf(dpFechaFin.getValue()) : null;
            TipoEstado estado = chBxEstado.getValue();

            List<Envio> filtrados = EnvioServices.filtrarEnvios(cliente, fechaInicio, fechaFin, estado);
            tableEnvios.setItems(FXCollections.observableList(filtrados));
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void irMain () {
        Navegacion.cambiarVista("/Vista/MainPageClient.fxml");
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

    public void irDirecciones () {
        Navegacion.cambiarVista("/Vista/DireccionesClient.fxml");
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

    public void irCotizar () {
        Navegacion.cambiarVista("/Vista/CotizarClient.fxml");
    }



}
