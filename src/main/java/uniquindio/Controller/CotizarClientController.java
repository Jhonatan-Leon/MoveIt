package uniquindio.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import netscape.javascript.JSObject;
import uniquindio.Errors.ControllException;
import uniquindio.Helper.Sesion;
import uniquindio.Mappers.ClientMapper;
import uniquindio.Model.Client;
import uniquindio.Model.DTO.CotizacionDTO;
import uniquindio.Model.DTO.PaqueteDTO;
import uniquindio.Model.DTO.UserPostLoginDTO;
import uniquindio.Model.Direccion;
import uniquindio.Model.Envio;
import uniquindio.Services.ReportService;
import uniquindio.Utils.JSBrigeRuta;
import uniquindio.Utils.JavaFxAux;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class CotizarClientController {

    UserPostLoginDTO user = Sesion.getUsuarioActual();
    private ObservableList<PaqueteDTO> listPaqueteDTO = FXCollections.observableArrayList();

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
    @FXML private TextField txtPeso;
    @FXML private TextField txtLargo;
    @FXML private TextField txtAncho;
    @FXML private TextField txtAlto;
    @FXML private TableView<PaqueteDTO> tvListPaquetes;
    @FXML private TableColumn<PaqueteDTO, Double> colPeso;
    @FXML private TableColumn<PaqueteDTO, Double> colLargo;
    @FXML private TableColumn<PaqueteDTO, Double> colAncho;
    @FXML private TableColumn<PaqueteDTO, Double> colAlto;
    @FXML private WebView webView;
    @FXML private TextField txtInicio;     // Para coordenadas de inicio
    @FXML private TextField txtFin;        // Para coordenadas de fin
    @FXML private TextField txtDistancia;  // Para distancia en km
    @FXML private TextField txtDuracion;   // Para tiempo en minutos
    private List<String> serviciosAdicionales;
    @FXML private CheckBox cbSeguro;
    @FXML private CheckBox cbFragil;
    @FXML private CheckBox cbFirma;
    @FXML private ChoiceBox<String> ChBxPrioridad;
    @FXML private ChoiceBox<Direccion> chBxDireccionInicio;
    @FXML private ChoiceBox<Direccion> chBxDireccionFin;
    @FXML private TextField txtCosto;
    @FXML private AnchorPane anchorCoti;


    public void initialize () throws URISyntaxException, ControllException.UserNotFound {
        btnPerfil.setText(user.toString());
        btnPerfil1.setText(user.toString());
        anchorOpcionesUsuario.setVisible(false);
        anchorPerfil.setVisible(false);

        // Asociar lista observable al TableView
        tvListPaquetes.setItems(listPaqueteDTO);
        colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        colLargo.setCellValueFactory(new PropertyValueFactory<>("largo"));
        colAncho.setCellValueFactory(new PropertyValueFactory<>("ancho"));
        colAlto.setCellValueFactory(new PropertyValueFactory<>("alto"));

        File htmlFile = new File("src/main/resources/Elementos/MapaRuta.html");
        webView.getEngine().load(htmlFile.toURI().toString());

        JSBrigeRuta bridge = new JSBrigeRuta(this);

        webView.getEngine().getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == javafx.concurrent.Worker.State.SUCCEEDED) {
                // Obtenemos el objeto window de JS
                JSObject window = (JSObject) webView.getEngine().executeScript("window");
                // Exponemos nuestro bridge a JS
                window.setMember("java", bridge);
            }
        });
        chBxDireccionInicio.getSelectionModel().selectedItemProperty().addListener((obs, oldV, dir) -> {
            if (dir != null) {
                double[] c = parseCoords(dir.getCoordenadas());
                webView.getEngine().executeScript(
                        "setStartPoint(" + c[0] + "," + c[1] + ");"
                );
            }
        });

        chBxDireccionFin.getSelectionModel().selectedItemProperty().addListener((obs, oldV, dir) -> {
            if (dir != null) {
                double[] c = parseCoords(dir.getCoordenadas());
                webView.getEngine().executeScript(
                        "setEndPoint(" + c[0] + "," + c[1] + ");"
                );
            }
        });

        List<String> serviciosAdicionales = new ArrayList<>();
        serviciosAdicionales.add("Alta");
        serviciosAdicionales.add("Media");
        serviciosAdicionales.add("Baja");
        List<Direccion> listaDirecciones = getDirecciones();
        chBxDireccionInicio.setItems(FXCollections.observableArrayList(listaDirecciones));
        chBxDireccionFin.setItems(FXCollections.observableArrayList(listaDirecciones));
        ChBxPrioridad.setItems(FXCollections.observableList(serviciosAdicionales));
    }

    public void cotizar () throws ControllException.CotizacionInvalid, ControllException.TarifaError {
        capturarServicios();
        int distancia = (int) Math.round(Double.parseDouble(txtDuracion.getText()));
        CotizacionDTO cotizacion = new CotizacionDTO(Double.parseDouble(txtDistancia.getText()),
                distancia,
                String.valueOf(ChBxPrioridad.getValue()),
                listPaqueteDTO,
                serviciosAdicionales,
                chBxDireccionInicio.getValue(),
                chBxDireccionFin.getValue(),
                0
                );
        CotizacionDTO coti = CotizacionController.cotizarTarifa(cotizacion);
        txtCosto.setText(String.valueOf(coti.getCostoCalculado()));
    }

    public List<Direccion> getDirecciones () throws ControllException.UserNotFound {
        return JavaFxAux.obtenerDirecciones(user);
    }

    private double[] parseCoords(String coords) {
        String[] parts = coords.split(",");
        return new double[]{
                Double.parseDouble(parts[0].trim()), // lat
                Double.parseDouble(parts[1].trim())  // lng
        };
    }


    public void recibirDatosRuta(String inicio, String fin, String distancia, String duracion) {
        txtInicio.setText(inicio);
        txtFin.setText(fin);
        txtDistancia.setText(distancia);
        txtDuracion.setText(duracion);
    }

    public void capturarServicios() {
        serviciosAdicionales = new ArrayList<>();
        if(cbSeguro.isSelected()) serviciosAdicionales.add("Seguro");
        if(cbFragil.isSelected()) serviciosAdicionales.add("Frágil");
        if(cbFirma.isSelected()) serviciosAdicionales.add("Firma requerida");
    }


    public void agregarPaquete () {
        try {
            PaqueteDTO paqueteDTO = new PaqueteDTO(
                    Double.parseDouble(txtPeso.getText()),
                    Double.parseDouble(txtLargo.getText()),
                    Double.parseDouble(txtAncho.getText()),
                    Double.parseDouble(txtAlto.getText())
            );

            listPaqueteDTO.add(paqueteDTO); // Actualiza TableView automáticamente

            // Limpiar campos
            txtPeso.clear();
            txtLargo.clear();
            txtAncho.clear();
            txtAlto.clear();
        } catch (NumberFormatException e) {
            System.out.println("Error: Debes ingresar números válidos en los campos de dimensiones.");
        }
    }

    public List<PaqueteDTO> getPaquetes() {
        // Devuelve la lista como List normal
        return new ArrayList<>(listPaqueteDTO);
    }

    public void crearPedido () {
    }

    //-----------------------------------------
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

    public void confirmar () {
        anchorCoti.setVisible(true);
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
}
