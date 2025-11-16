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
import uniquindio.Errors.ControllException;
import uniquindio.Helper.Sesion;
import uniquindio.Model.DTO.ClientSesionDTO;
import uniquindio.Model.Envio;
import uniquindio.Utils.JavaFxAux;

import java.util.List;

public class EnvioUserController {

    ClientSesionDTO user = Sesion.getUsuarioActual();

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

    public void irAtras () {
        Navegacion.volver();
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
