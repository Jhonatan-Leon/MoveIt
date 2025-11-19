package uniquindio.Facade;

import uniquindio.Errors.ControllException;
import uniquindio.Model.Admin; // Importar la clase Admin
import uniquindio.Model.Client;
import uniquindio.Model.DTO.AdminMetricasDTO;
import uniquindio.Model.DTO.MetricDataPointDTO;
import uniquindio.Model.Envio;
import uniquindio.Model.Repartidor;
import uniquindio.Model.TipoEstado;
import uniquindio.Services.AdminServices;

import java.util.List;

public class AdminFacade {
    public static List<Repartidor> listarRepartidores(){
        return AdminServices.listarRepartidores();
    }

    public static List<Repartidor> listarRepartidoresDisponibles(){
        return AdminServices.listarRepartidoresDisponibles();
    }

    public static Repartidor getRepartidor(String Id){
        return  AdminServices.obtenerRepartidor(Id);
    }

    public static boolean deleteRepartidor(String Id){
        return AdminServices.eliminarRepartidor(Id);
    }

    public static Repartidor createRepartidor(Repartidor repartidor){
        return AdminServices.crearRepartidor(repartidor);
    }

    public static Repartidor updateRepartidor(Repartidor repartidor){
        return AdminServices.actualizarRepartidor(repartidor);
    }

    public static Repartidor actualizarDisponibilidad(String id, boolean disponible)
            throws ControllException.UserNotFound {
        return AdminServices.actualizarDisponibilidadRepartidor(id, disponible);
    }

    public static List<Client> listarClientes() throws ControllException.UserNotFound {
        return AdminServices.listarClientes();
    }

    public static Client getClient(int id) throws ControllException.UserNotFound {
        return AdminServices.obtenerCliente(id);
    }

    public static Client createClient(Client client) throws ControllException.UserCreate{
        return  AdminServices.crearCliente(client);
    }

    public static boolean updateClient(Client client) throws ControllException.UserUpdate {
        return  AdminServices.actualizarCliente(client);
    }

    public static boolean deleteClient(int id) throws ControllException.UserDelete {
        return  AdminServices.eliminarCliente(id);
    }

    // -------------------- Métodos de Gestión de Administradores --------------------

    public static List<Admin> listarAdministradores() {
        return AdminServices.listarAdministradores();
    }

    public static Admin getAdminPorId(String id) throws ControllException.UserNotFound {
        return AdminServices.obtenerAdministradorPorId(id);
    }

    public static Admin getAdminPorEmail(String email) throws ControllException.UserNotFound {
        return AdminServices.obtenerAdministradorPorEmail(email);
    }

    public static Admin createAdmin(Admin admin) throws ControllException.UserCreate {
        return AdminServices.crearAdministrador(admin);
    }

    public static Admin updateAdmin(Admin admin) throws ControllException.UserUpdate {
        return AdminServices.actualizarAdministrador(admin);
    }

    public static boolean deleteAdmin(String id) throws ControllException.UserDelete {
        return AdminServices.eliminarAdministrador(id);
    }

    // -------------------- Métodos de Envíos y Métricas --------------------

    public static List<Envio> listarEnvios(){
        return AdminServices.listarEnvios();
    }

    public static Envio cambiarEstadoEnvioCliente(Client cliente,int idEnvio,TipoEstado nuevoEstado)
            throws ControllException.EnvioNotFound, ControllException.EnvioCreate {
        return AdminServices.cambiarEstadoEnvioCliente(cliente, idEnvio, nuevoEstado);
    }


    public static Envio asignarEnvioARepartidor(int idEnvio,
                                                String idRepartidor)
            throws ControllException.EnvioNotFound, ControllException.UserNotFound, ControllException.EnvioCreate {
        return AdminServices.asignarEnvioARepartidor(idEnvio, idRepartidor);
    }

    public static Envio reasignarEnvioARepartidor(int idEnvio,String idRepartidor)
            throws ControllException.EnvioNotFound, ControllException.UserNotFound, ControllException.EnvioCreate {
        return AdminServices.reasignarEnvioARepartidor(idEnvio, idRepartidor);
    }

    public static Envio actualizarEstadoEnvio(int idEnvio,TipoEstado nuevoEstado)
            throws ControllException.EnvioNotFound, ControllException.EnvioCreate {
        return AdminServices.actualizarEstadoEnvio(idEnvio, nuevoEstado);
    }

    public static Envio registrarIncidencia(int idEnvio,String descripcion)
            throws ControllException.EnvioNotFound, ControllException.EnvioCreate {
        return AdminServices.registrarIncidenciaEnvio(idEnvio, descripcion);
    }

    public static AdminMetricasDTO obtenerMetricas(){
        return AdminServices.obtenerMetricas();
    }

    public static List<MetricDataPointDTO> obtenerSerieIngresos(){
        return AdminServices.obtenerSerieIngresos();
    }
}