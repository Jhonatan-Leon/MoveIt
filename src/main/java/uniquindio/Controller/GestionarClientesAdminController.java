package uniquindio.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import uniquindio.Facade.UserFacade;
import uniquindio.Errors.ControllException;
import uniquindio.Model.ClassBuilder.UserBuilder;
import uniquindio.Model.Client;
import uniquindio.Model.TipoDocumento;

import java.util.List;

public class GestionarClientesAdminController {

    // ---- TABLA ----
    @FXML
    private TableView<Client> tableClientes;

    @FXML
    private TableColumn<Client, Integer> colId;
    @FXML
    private TableColumn<Client, String> colNombre;
    @FXML
    private TableColumn<Client, String> colEmail;
    @FXML
    private TableColumn<Client, String> colTelefono;
    @FXML
    private TableColumn<Client, TipoDocumento> colTipoDocumento;
    @FXML
    private TableColumn<Client, String> colNumeroDocumento;
    @FXML
    private TableColumn<Client, String> colEstado;
    @FXML
    private TableColumn<Client, String> colMetodoPago;

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
    private CheckBox chkEstado;

    @FXML
    private ChoiceBox<String> choiceMetodoPago;

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
    private ObservableList<Client> listaClientes;

    // ---- MÉTODO DE INICIALIZACIÓN ----
    @FXML
    public void initialize() {
        // Inicializar ChoiceBoxes
        choiceTipoDocumento.getItems().setAll(TipoDocumento.values());
        choiceMetodoPago.getItems().addAll("Efectivo", "Tarjeta", "Transferencia");

        // Inicializar columnas de la tabla
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colTipoDocumento.setCellValueFactory(new PropertyValueFactory<>("tipoDocumento"));
        colNumeroDocumento.setCellValueFactory(new PropertyValueFactory<>("numeroDocumento"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colMetodoPago.setCellValueFactory(new PropertyValueFactory<>("metodoPagoFavorito"));

        // Cargar clientes
        cargarClientes();

        // Listener para selección de fila
        tableClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                llenarFormulario(newSelection);
            }
        });

        // Asignar acciones a botones
        btnCrear.setOnAction(e -> crearCliente());
        btnActualizar.setOnAction(e -> actualizarCliente());
        btnEliminar.setOnAction(e -> eliminarCliente());
        btnLimpiar.setOnAction(e -> limpiarCampos());
    }

    // ---- MÉTODOS AUXILIARES ----
    private void cargarClientes() {
        try {
            List<Client> clientes = UserFacade.getInstance().getAllUsers();
            listaClientes = FXCollections.observableArrayList(clientes);
            tableClientes.setItems(listaClientes);
        } catch (ControllException.UserNotFound e) {
            listaClientes = FXCollections.observableArrayList();
            tableClientes.setItems(listaClientes);
            mostrarAlerta("Advertencia", "No se encontraron clientes", Alert.AlertType.WARNING);
        }
    }

    private void llenarFormulario(Client client) {
        txtNombre.setText(client.getNombreCompleto());
        txtEmail.setText(client.getEmail());
        txtTelefono.setText(client.getTelefono());
        txtNumeroDocumento.setText(client.getNumeroDocumento());
        choiceTipoDocumento.setValue(client.getTipoDocumento());
        chkEstado.setSelected(client.isEstado());
        choiceMetodoPago.setValue(client.getMetodoPagoFavorito());
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
    private void crearCliente() {
        try {
            // Validar campos mínimos
            if (txtNombre.getText().isEmpty() || txtEmail.getText().isEmpty() || choiceTipoDocumento.getValue() == null || txtNumeroDocumento.getText().isEmpty()) {
                mostrarAlerta("Advertencia", "Por favor, complete los campos obligatorios", Alert.AlertType.WARNING);
                return;
            }

            // Crear builder con los valores obligatorios
            UserBuilder builder = new UserBuilder(
                    generarId(),                      // ID único
                    txtNombre.getText(),              // Nombre completo
                    txtEmail.getText(),               // Email
                    "1234",                           // Password temporal o fijo
                    choiceTipoDocumento.getValue(),   // Tipo de documento
                    txtNumeroDocumento.getText()      // Número de documento
            );

            // Valores opcionales
            builder.withtelefono(txtTelefono.getText());
            builder.withMetodoPagoFavorito(choiceMetodoPago.getValue());
            builder.withEstado(chkEstado.isSelected());

            // Construir cliente y agregarlo usando UserFacade
            Client nuevoCliente = builder.build();
            UserFacade.getInstance().addUser(nuevoCliente);

            // Actualizar tabla y limpiar formulario
            cargarClientes();
            limpiarCampos();

            mostrarAlerta("Éxito", "Cliente creado correctamente", Alert.AlertType.INFORMATION);

        } catch (ControllException.UserCreate e) {
            mostrarAlerta("Error", "No se pudo crear el cliente: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    // Método auxiliar para generar un ID único simple
    private String generarId() {
        return txtNumeroDocumento.getText();
    }

    public void volver () {
        Navegacion.volver();
    }


    @FXML
    private void actualizarCliente() {
        Client seleccionado = tableClientes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Advertencia", "Debe seleccionar un cliente", Alert.AlertType.WARNING);
            return;
        }
        try {
            seleccionado.setNombreCompleto(txtNombre.getText());
            seleccionado.setEmail(txtEmail.getText());
            seleccionado.setTelefono(txtTelefono.getText());
            seleccionado.setNumeroDocumento(txtNumeroDocumento.getText());
            seleccionado.setTipoDocumento(choiceTipoDocumento.getValue());
            seleccionado.setEstado(chkEstado.isSelected()); // estado booleano
            seleccionado.setMetodoPagoFavorito(choiceMetodoPago.getValue());

            UserFacade.getInstance().updateUser(seleccionado);
            cargarClientes();
            mostrarAlerta("Éxito", "Cliente actualizado correctamente", Alert.AlertType.INFORMATION);
        } catch (ControllException.UserUpdate e) {
            mostrarAlerta("Error", "No se pudo actualizar el cliente", Alert.AlertType.ERROR);
        }
    }


    @FXML
    private void eliminarCliente() {
        Client seleccionado = tableClientes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Advertencia", "Debe seleccionar un cliente", Alert.AlertType.WARNING);
            return;
        }
        try {
            int selec = Integer.parseInt(seleccionado.getId());
            UserFacade.getInstance().deleteUser(selec);
            cargarClientes();
            limpiarCampos();
            mostrarAlerta("Éxito", "Cliente eliminado correctamente", Alert.AlertType.INFORMATION);
        } catch (ControllException.UserDelete e) {
            mostrarAlerta("Error", "No se pudo eliminar el cliente", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void limpiarCampos() {
        txtNombre.clear();
        txtEmail.clear();
        txtTelefono.clear();
        txtNumeroDocumento.clear();
        choiceTipoDocumento.setValue(null);
        chkEstado.setSelected(false);
        choiceMetodoPago.setValue(null);
        tableClientes.getSelectionModel().clearSelection();
    }
}

