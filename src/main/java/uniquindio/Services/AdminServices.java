package uniquindio.Services;

import uniquindio.Errors.ControllException;
import uniquindio.Model.*;
import uniquindio.Model.DTO.AdminMetricasDTO;
import uniquindio.Model.DTO.MetricDataPointDTO;
import uniquindio.Model.Gestion.GestionAdmin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static uniquindio.Helper.PersistirEnvio.persistirEnvio;

public class AdminServices {

    private static final GestionAdmin gestion = GestionAdmin.getInstance();
    private static final String FORMATO_PERIODO = "yyyy-MM";

    public static List<Client> listarClientes() throws ControllException.UserNotFound {
        return UserServices.listUsers();
    }

    public static Client obtenerCliente(int id) throws ControllException.UserNotFound {
        return UserServices.getUserById(id);
    }

    public static Client crearCliente(Client client) throws ControllException.UserCreate {
        return UserServices.addUser(client);
    }

    public static boolean actualizarCliente(Client client) throws ControllException.UserUpdate {
        return UserServices.updateUser(client);
    }

    public static boolean eliminarCliente(int id) throws ControllException.UserDelete {
        return UserServices.deleteUser(id);
    }

    // -------------------- Gestión de repartidores --------------------

    public static List<Repartidor> listarRepartidores() {
        return RepartidorServices.getList();
    }

    public static Repartidor obtenerRepartidor(String id) {
        return RepartidorServices.getRepartidor(id);
    }

    public static List<Repartidor> listarRepartidoresDisponibles() {
        return RepartidorServices.getList()
                .stream()
                .filter(Repartidor::isDisponible)
                .collect(Collectors.toList());
    }

    public static Repartidor crearRepartidor(Repartidor repartidor) {
        return RepartidorServices.AddRepartidor(repartidor);
    }

    public static Repartidor actualizarRepartidor(Repartidor repartidor) {
        return RepartidorServices.updateRepartidor(repartidor.getId(), repartidor);
    }

    public static boolean eliminarRepartidor(String id) {
        return RepartidorServices.deleteRepartidor(id);
    }

    public static Repartidor actualizarDisponibilidadRepartidor(String id, boolean disponible)
            throws ControllException.UserNotFound {
        return RepartidorServices.actualizarDisponibilidad(id, disponible);
    }

    public static List<Envio> listarEnvios() {
        return EnvioServices.getListEnvios();
    }

    public static Envio obtenerEnvio(int idEnvio) throws ControllException.EnvioNotFound {
        Envio envio = EnvioServices.getEnvio(idEnvio);
        if (envio == null) {
            throw new ControllException.EnvioNotFound("Envío no encontrado con ID: " + idEnvio);
        }
        return envio;
    }

    public static Envio cambiarEstadoEnvioCliente(Client cliente,int idEnvio, TipoEstado nuevoEstado)
            throws ControllException.EnvioNotFound, ControllException.EnvioCreate {

        if (cliente == null) {
            throw new ControllException.EnvioCreate("El cliente no puede ser nulo");
        }
        Envio envio = EnvioServices.buscarEnvioporUsuario(cliente, idEnvio);
        if (envio == null) {
            throw new ControllException.EnvioNotFound("Envío no encontrado para el cliente");
        }
        envio.setEstado(nuevoEstado);
        return persistirEnvio(envio, nuevoEstado);
    }

    public static Envio actualizarEstadoEnvio(int idEnvio, TipoEstado nuevoEstado) throws ControllException.EnvioNotFound, ControllException.EnvioCreate {
        if (nuevoEstado == null) {
            throw new ControllException.EnvioCreate("El nuevo estado no puede ser nulo");
        }
        Envio envio = obtenerEnvio(idEnvio);
        envio.setEstado(nuevoEstado);
        if (TipoEstado.ENTREGADO.equals(nuevoEstado)) {
            envio.setFechaEntregaReal(new Date());
        }
        return persistirEnvio(envio, nuevoEstado);
    }

    public static Envio registrarIncidenciaEnvio(int idEnvio, String descripcion)
            throws ControllException.EnvioNotFound, ControllException.EnvioCreate {

        Envio envio = obtenerEnvio(idEnvio);
        envio.setEstado(TipoEstado.INCIDENCIA);
        envio.setUltimaIncidencia(descripcion);
        return persistirEnvio(envio, TipoEstado.INCIDENCIA);
    }

    public static Envio asignarEnvioARepartidor(int idEnvio,String idRepartidor)
            throws ControllException.EnvioNotFound, ControllException.UserNotFound, ControllException.EnvioCreate {

        if (idRepartidor == null || idRepartidor.isBlank()) {
            throw new ControllException.UserNotFound("El ID del repartidor no puede estar vacío");
        }

        Envio envio = obtenerEnvio(idEnvio);
        Repartidor nuevoRepartidor = RepartidorServices.getRepartidor(idRepartidor);
        if (nuevoRepartidor == null) {
            throw new ControllException.UserNotFound("Repartidor no encontrado con ID: " + idRepartidor);
        }
        if (!nuevoRepartidor.isDisponible() &&
                (envio.getRepartidor() == null ||
                        !Objects.equals(envio.getRepartidor().getId(), nuevoRepartidor.getId()))) {
            throw new ControllException.EnvioCreate("El repartidor no está disponible");
        }

        Repartidor repartidorAnterior = envio.getRepartidor();
        envio.setRepartidor(nuevoRepartidor);
        envio.setEstado(TipoEstado.ASIGNADO);
        Envio actualizado = persistirEnvio(envio, TipoEstado.ASIGNADO);

        try {
            RepartidorServices.actualizarDisponibilidad(nuevoRepartidor.getId(), false);
            if (repartidorAnterior != null &&
                    !Objects.equals(repartidorAnterior.getId(), nuevoRepartidor.getId())) {
                RepartidorServices.actualizarDisponibilidad(repartidorAnterior.getId(), true);
            }
        } catch (ControllException.UserNotFound e) {
            throw new ControllException.EnvioCreate("No se pudo actualizar la disponibilidad del repartidor");
        }

        return actualizado;
    }

    public static Envio reasignarEnvioARepartidor(int idEnvio,String idRepartidor)
            throws ControllException.EnvioNotFound, ControllException.UserNotFound, ControllException.EnvioCreate {
        return asignarEnvioARepartidor(idEnvio, idRepartidor);
    }

    public static AdminMetricasDTO obtenerMetricas() {
        List<Envio> envios = EnvioServices.getListEnvios();
        AdminMetricasDTO dto = new AdminMetricasDTO();
        dto.setTiempoPromedioEntregaHoras(calcularTiempoPromedioHoras(envios));
        dto.setIngresosPorPeriodo(calcularIngresosPorPeriodo(envios));
        return dto;
    }

    public static List<MetricDataPointDTO> obtenerSerieIngresos() {
        return calcularIngresosPorPeriodo(EnvioServices.getListEnvios());
    }

    private static double calcularTiempoPromedioHoras(List<Envio> envios) {
        return envios.stream()
                .filter(envio -> envio.getFechaCreación() != null && envio.getFechaEntregaReal() != null)
                .mapToLong(envio -> envio.getFechaEntregaReal().getTime() - envio.getFechaCreación().getTime())
                .filter(diff -> diff > 0)
                .average()
                .orElse(0d) / (1000d * 60d * 60d);
    }

    private static List<MetricDataPointDTO> calcularIngresosPorPeriodo(List<Envio> envios) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_PERIODO);
        Map<String, Double> ingresos = envios.stream()
                .filter(envio -> envio.getFechaCreación() != null)
                .collect(Collectors.groupingBy(
                        envio -> sdf.format(envio.getFechaCreación()),
                        Collectors.summingDouble(Envio::getCosto)
                ));
        return mapToMetricList(ingresos);
    }

    private static List<MetricDataPointDTO> mapToMetricList(Map<String, ? extends Number> map) {
        return map.entrySet()
                .stream()
                .map(entry -> new MetricDataPointDTO(entry.getKey(), entry.getValue().doubleValue()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    //  Gestión de Administradores

    public static List<Admin> listarAdministradores() {
        return gestion.getAdmins();
    }

    public static Admin obtenerAdministradorPorId(String id) throws ControllException.UserNotFound {
        Admin admin = gestion.getAdminById(id);
        if (admin == null) {
            throw new ControllException.UserNotFound("Administrador no encontrado con ID: " + id);
        }
        return admin;
    }

    public static Admin obtenerAdministradorPorEmail(String email) throws ControllException.UserNotFound {
        Admin admin = gestion.getAdmin(email);
        if (admin == null) {
            throw new ControllException.UserNotFound("Administrador no encontrado con Email: " + email);
        }
        return admin;
    }

    public static Admin crearAdministrador(Admin admin) throws ControllException.UserCreate {
        if (gestion.getAdmin(admin.getEmail()) != null) {
            throw new ControllException.UserCreate("Ya existe un administrador con el email: " + admin.getEmail());
        }
        return gestion.addAdmin(admin);
    }


    public static Admin actualizarAdministrador(Admin admin) throws ControllException.UserUpdate {
        Admin adminActualizado = gestion.updateAdmin(admin.getId(), admin);
        if (adminActualizado == null) {
            throw new ControllException.UserUpdate("No se pudo actualizar el administrador, ID no encontrado: " + admin.getId());
        }
        return adminActualizado;
    }

    public static boolean eliminarAdministrador(String id) throws ControllException.UserDelete {
        if (!gestion.deleteAdmin(id)) {
            throw new ControllException.UserDelete("No se pudo eliminar el administrador con ID: " + id);
        }
        return true;
    }

}
