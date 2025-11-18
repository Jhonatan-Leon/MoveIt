package uniquindio.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import uniquindio.Facade.AdminFacade;
import uniquindio.Model.Repartidor;
import uniquindio.Model.TipoDocumento;
import uniquindio.Model.ClassBuilder.RepartidorBuilder;

import java.util.List;
import java.util.Optional;

public class GestionarRepartidoresAdminController {


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
    private TableColumn<Repartidor, Boolean> colEstado;
    @FXML
    private TableColumn<Repartidor, String> colZonaCobertura;

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
    private TextField txtZonaCobertura;
    @FXML
    private ChoiceBox<TipoDocumento> choiceTipoDocumento;
    @FXML
    private CheckBox checkEstado;

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
    private ObservableList<Repartidor> repartidoresObservable;

    @FXML
    public void initialize() {
        // Inicializar choicebox de tipo de documento
        choiceTipoDocumento.setItems(FXCollections.observableArrayList(TipoDocumento.values()));

        // Configurar columnas de la tabla
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        colNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombreCompleto()));
        colEmail.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));
        colTelefono.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTelefono()));
        colTipoDocumento.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getTipoDocumento()));
        colNumeroDocumento.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNumeroDocumento()));
        colEstado.setCellValueFactory(data -> new javafx.beans.property.SimpleBooleanProperty(data.getValue().isEstado()).asObject());
        colZonaCobertura.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getZonaCobertura()));

        // Cargar repartidores en la tabla
        cargarTabla();

        // Selección de tabla para llenar formulario
        tableRepartidores.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                cargarFormulario(newSel);
            }
        });
    }

    private void cargarTabla() {
        List<Repartidor> lista = AdminFacade.listarRepartidores();
        repartidoresObservable = FXCollections.observableArrayList(lista);
        tableRepartidores.setItems(repartidoresObservable);
    }

    private void cargarFormulario(Repartidor r) {
        txtNombre.setText(r.getNombreCompleto());
        txtEmail.setText(r.getEmail());
        txtTelefono.setText(r.getTelefono());
        txtNumeroDocumento.setText(r.getNumeroDocumento());
        txtZonaCobertura.setText(r.getZonaCobertura());
        choiceTipoDocumento.setValue(r.getTipoDocumento());
        checkEstado.setSelected(r.isEstado());
    }

    @FXML
    private void crearRepartidor() {
        try {
            RepartidorBuilder builder = new RepartidorBuilder(
                    "R" + System.currentTimeMillis(),
                    txtNombre.getText(),
                    txtEmail.getText(),
                    "default123"
            ).withtelefono(txtTelefono.getText())
                    .withtipoDocumento(choiceTipoDocumento.getValue())
                    .withnumeroDocumento(txtNumeroDocumento.getText())
                    .withestado(checkEstado.isSelected())
                    .withzonaCobertura(txtZonaCobertura.getText());

            Repartidor nuevo = AdminFacade.createRepartidor(builder.build());

            repartidoresObservable.add(nuevo);
            limpiarCampos();
            mostrarAlerta("Éxito", "Repartidor creado correctamente.", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo crear el repartidor: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void actualizarRepartidor() {
        Repartidor seleccionado = tableRepartidores.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Atención", "Seleccione un repartidor para actualizar.", Alert.AlertType.WARNING);
            return;
        }
        try {
            RepartidorBuilder builder = new RepartidorBuilder(
                    seleccionado.getId(),
                    txtNombre.getText(),
                    txtEmail.getText(),
                    seleccionado.getPassword() // mantener contraseña
            ).withtelefono(txtTelefono.getText())
                    .withtipoDocumento(choiceTipoDocumento.getValue())
                    .withnumeroDocumento(txtNumeroDocumento.getText())
                    .withestado(checkEstado.isSelected())
                    .withzonaCobertura(txtZonaCobertura.getText());

            Repartidor actualizado = AdminFacade.updateRepartidor(builder.build()); // si agregas build() -> builder.build()

            int index = repartidoresObservable.indexOf(seleccionado);
            repartidoresObservable.set(index, actualizado);
            limpiarCampos();
            mostrarAlerta("Éxito", "Repartidor actualizado correctamente.", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo actualizar el repartidor: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void eliminarRepartidor() {
        Repartidor seleccionado = tableRepartidores.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Atención", "Seleccione un repartidor para eliminar.", Alert.AlertType.WARNING);
            return;
        }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "¿Está seguro de eliminar este repartidor?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            boolean eliminado = AdminFacade.deleteRepartidor(seleccionado.getId());
            if (eliminado) {
                repartidoresObservable.remove(seleccionado);
                limpiarCampos();
                mostrarAlerta("Éxito", "Repartidor eliminado correctamente.", Alert.AlertType.INFORMATION);
            }
        }
    }

    @FXML
    private void limpiarCampos() {
        txtNombre.clear();
        txtEmail.clear();
        txtTelefono.clear();
        txtNumeroDocumento.clear();
        txtZonaCobertura.clear();
        choiceTipoDocumento.setValue(null);
        checkEstado.setSelected(true);
        tableRepartidores.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}