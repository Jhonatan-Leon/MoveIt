package uniquindio.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import uniquindio.Model.Gestion.GestionRepartidor;
import uniquindio.Model.Repartidor;
import uniquindio.Model.TipoDocumento;
import uniquindio.Model.ClassBuilder.RepartidorBuilder;

import java.util.List;
public class GestionRepartidorAdminController {
    // ---- TABLA ----
    @FXML
    private TableView<Repartidor> tableRepartidores;

    @FXML
    private TableColumn<Repartidor, String> colId;
    @FXML
    private TableColumn<Repartidor, String> colNombre;
    @FXML
    private TableColumn<Repartidor, String> colEmail;
    @FXML
    private TableColumn<Repartidor, String> colTelefono;
    @FXML
    private TableColumn<Repartidor, TipoDocumento> colTipoDocumento;
    @FXML
    private TableColumn<Repartidor, String> colNumeroDocumento;
    @FXML
    private TableColumn<Repartidor, String> colZonaCobertura;
    @FXML
    private TableColumn<Repartidor, String> colEstado;

    // ---- CAMPOS DEL FORMULARIO ----
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtNumeroDocumento;
    @FXML
    private ChoiceBox<TipoDocumento> choiceTipoDocumento;
    @FXML
    private TextField txtZonaCobertura;
    @FXML
    private CheckBox chkEstado;

    // ---- BOTONES ----
    @FXML
    private Button btnCrear;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnLimpiar;

    // ---- LISTA OBSERVABLE ----
    private ObservableList<Repartidor> listaRepartidores;

    @FXML
    public void initialize() {
        // Inicializar ChoiceBox
        choiceTipoDocumento.getItems().setAll(TipoDocumento.values());

        // Inicializar columnas
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colTipoDocumento.setCellValueFactory(new PropertyValueFactory<>("tipoDocumento"));
        colNumeroDocumento.setCellValueFactory(new PropertyValueFactory<>("numeroDocumento"));
        colZonaCobertura.setCellValueFactory(new PropertyValueFactory<>("zonaCobertura"));
        colEstado.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().isEstado() ? "Activo" : "Inactivo"
                )
        );



        // Cargar tabla
        cargarRepartidores();

        // Listener de selección
        tableRepartidores.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                llenarFormulario(newSel);
            }
        });

        // Botones
        btnCrear.setOnAction(e -> crearRepartidor());
        btnActualizar.setOnAction(e -> actualizarRepartidor());
        btnEliminar.setOnAction(e -> eliminarRepartidor());
        btnLimpiar.setOnAction(e -> limpiarCampos());
    }

    private void cargarRepartidores() {
        List<Repartidor> repartidores = GestionRepartidor.getInstance().getlist();
        listaRepartidores = FXCollections.observableArrayList(repartidores);
        tableRepartidores.setItems(listaRepartidores);
    }

    private void llenarFormulario(Repartidor r) {
        txtNombre.setText(r.getNombreCompleto());
        txtEmail.setText(r.getEmail());
        txtTelefono.setText(r.getTelefono());
        txtNumeroDocumento.setText(r.getNumeroDocumento());
        choiceTipoDocumento.setValue(r.getTipoDocumento());
        txtZonaCobertura.setText(r.getZonaCobertura());
        chkEstado.setSelected(r.isEstado());
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // ---- MÉTODOS CRUD ----
    @FXML
    private void crearRepartidor() {
        if (txtNombre.getText().isEmpty() || txtEmail.getText().isEmpty() || choiceTipoDocumento.getValue() == null || txtNumeroDocumento.getText().isEmpty()) {
            mostrarAlerta("Advertencia", "Complete los campos obligatorios", Alert.AlertType.WARNING);
            return;
        }

        RepartidorBuilder builder = new RepartidorBuilder(
                generarId(),
                txtNombre.getText(),
                txtEmail.getText(),
                "1234" // password temporal
        );

        builder.withtelefono(txtTelefono.getText())
                .withtipoDocumento(choiceTipoDocumento.getValue())
                .withnumeroDocumento(txtNumeroDocumento.getText())
                .withzonaCobertura(txtZonaCobertura.getText())
                .withestado(chkEstado.isSelected());

        Repartidor nuevo = builder.build();
        GestionRepartidor.getInstance().addRepartidor(nuevo);

        cargarRepartidores();
        limpiarCampos();
        mostrarAlerta("Éxito", "Repartidor creado correctamente", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void actualizarRepartidor() {
        Repartidor seleccionado = tableRepartidores.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Advertencia", "Seleccione un repartidor", Alert.AlertType.WARNING);
            return;
        }

        seleccionado.setNombreCompleto(txtNombre.getText());
        seleccionado.setEmail(txtEmail.getText());
        seleccionado.setTelefono(txtTelefono.getText());
        seleccionado.setTipoDocumento(choiceTipoDocumento.getValue());
        seleccionado.setNumeroDocumento(txtNumeroDocumento.getText());
        seleccionado.setZonaCobertura(txtZonaCobertura.getText());
        seleccionado.setEstado(chkEstado.isSelected());

        tableRepartidores.refresh();
        mostrarAlerta("Éxito", "Repartidor actualizado correctamente", Alert.AlertType.INFORMATION);
    }


    @FXML
    private void eliminarRepartidor() {
        Repartidor seleccionado = tableRepartidores.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Advertencia", "Seleccione un repartidor", Alert.AlertType.WARNING);
            return;
        }

        boolean eliminado = GestionRepartidor.getInstance().DeleteRepatidor(seleccionado.getId());
        if (eliminado) {
            cargarRepartidores();
            limpiarCampos();
            mostrarAlerta("Éxito", "Repartidor eliminado correctamente", Alert.AlertType.INFORMATION);
        } else {
            mostrarAlerta("Error", "No se pudo eliminar el repartidor", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void limpiarCampos() {
        txtNombre.clear();
        txtEmail.clear();
        txtTelefono.clear();
        txtNumeroDocumento.clear();
        choiceTipoDocumento.setValue(null);
        txtZonaCobertura.clear();
        chkEstado.setSelected(false);
        tableRepartidores.getSelectionModel().clearSelection();
    }

    private String generarId() {
        return txtNumeroDocumento.getText();
    }

    public void volver () {
        Navegacion.volver();
    }

}
