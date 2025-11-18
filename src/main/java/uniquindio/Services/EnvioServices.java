package uniquindio.Services;

import uniquindio.Errors.ControllException;
import uniquindio.Model.*;
import uniquindio.Model.ClassBuilder.EnvioBuilder;
import uniquindio.Model.DTO.CotizacionDTO;
import uniquindio.Model.Gestion.GestionEnvios;
import uniquindio.Utils.EnvioIdGenerator;
import uniquindio.Utils.PackageAdapter;
import uniquindio.Utils.PrioridadConverter;
import uniquindio.Model.Package;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EnvioServices {
    private static final GestionEnvios gestion = GestionEnvios.getInstance();

    public static Envio getEnvio(int IdEnvio) {
        return gestion.getEnvio(IdEnvio);
    }

    public static boolean DeleteEnvio(int IdEnvio) {
        return gestion.DeleteEnvio(IdEnvio);
    }

    public static Envio updateEnvio(Envio updateEnvio) {
        return gestion.updateEnvio(updateEnvio);
    }

    public static boolean AddEnvio(Envio envio) {
        return gestion.AddEnvio(envio);
    }



    public static List<Envio> getListEnvios() {
        return gestion.Envios();
    }

    public static String obtenerMensajeRastreo(String idStg) {
        int id = Integer.parseInt(idStg);
        Envio envio = getEnvio(id);
        String mensaje = "";
        if (envio == null) {
            mensaje = "No existe un envio con ese ID.";
            return mensaje;
        } else if (envio.getEstado().equals(TipoEstado.SOLICITADO)) {
            mensaje = "Tu envío ha sido solicitado y estamos preparando todo para asignarlo.";
            return mensaje;
        } else if (envio.getEstado().equals(TipoEstado.ASIGNADO)) {
            mensaje = "Tu envío ya fue asignado a un repartidor. Pronto iniciará el recorrido.";
            return mensaje;
        } else if (envio.getEstado().equals(TipoEstado.EN_RUTA)) {
            mensaje = "Tu envío está en camino hacia la dirección indicada.";
            return mensaje;
        } else if (envio.getEstado().equals(TipoEstado.ENTREGADO)) {
            mensaje = "Tu envío ha sido entregado exitosamente.";
            return mensaje;
        } else if (envio.getEstado().equals(TipoEstado.INCIDENCIA)) {
            mensaje = "Se presentó una incidencia con tu envío. Uno de nuestros agentes se comunicará contigo.";
            return mensaje;
        } else {
            mensaje = "No encontramos un pedido con ese ID.";
            return mensaje;
        }
    }


    public static List<Envio> filtrarEnvios(Client cliente, Date fechaInicio, Date fechaFin, TipoEstado estado) {
        List<Envio> todos = getEnviosPorCliente(cliente);

        return todos.stream()
                .filter(e -> (fechaInicio == null || !e.getFechaCreación().before(fechaInicio)))
                .filter(e -> (fechaFin == null || !e.getFechaCreación().after(fechaFin)))
                .filter(e -> (estado == null || e.getEstado() == estado))
                .collect(Collectors.toList());
    }

    public static List<Envio> getEnviosPorCliente(Client cliente) {
        return cliente.getListEnvio();
    }

    public static Envio buscarEnvioporUsuario(Client client, int IdEnvio) throws ControllException.EnvioNotFound{
        Envio envioEncontrado = client.getListEnvio().stream()
                .filter(envio -> envio.getIdEnvio() == IdEnvio)
                .findFirst()
                .orElse(null);

        if(envioEncontrado == null){
            throw new ControllException.EnvioNotFound("Envio no encontrado");
        }

        return envioEncontrado;

    }

    public static CotizacionDTO CotizarEnvio(CotizacionDTO cotizacionDTO) throws ControllException.CotizacionInvalid, ControllException.TarifaError {
        if (cotizacionDTO == null) {
            throw new ControllException.CotizacionInvalid("La cotización no puede ser nula");
        }

        if (cotizacionDTO.getDistanciaKm() <= 0) {
            throw new ControllException.CotizacionInvalid("La distancia en Km debe ser mayor a 0");
        }

        if (cotizacionDTO.getPaquetes() == null || cotizacionDTO.getPaquetes().isEmpty()) {
            throw new ControllException.CotizacionInvalid("Debe incluir al menos un paquete en la cotización");
        }

        // PATRÓN: Adapter - Convierte String a TipoPrioridad
        TipoPrioridad prioridad = PrioridadConverter.convertir(cotizacionDTO.getPrioridad());

        List<Package> paquetes = PackageAdapter.adaptarLista(cotizacionDTO.getPaquetes());
        
        // Calcular peso y volumen total
        double pesoTotal = TarifaService.calcularPesoTotal(paquetes);
        double volumenTotal = TarifaService.calcularVolumenTotal(paquetes);
        
        if (pesoTotal <= 0) {
            throw new ControllException.CotizacionInvalid("El peso total debe ser mayor a 0");
        }
        
        // Calcular la tarifa
        Tarifa tarifa = TarifaService.calcularTarifa(
            cotizacionDTO.getDistanciaKm(),
            pesoTotal,
            volumenTotal,
            prioridad,
            cotizacionDTO.getServiciosAdicionales()
        );
        
        // Obtener el costo total
        double costoTotal = TarifaService.obtenerCostoTotal(tarifa);
        
        if (costoTotal <= 0) {
            throw new ControllException.TarifaError("Error al calcular el costo de la tarifa");
        }
        
        // Asignar el costo calculado al DTO
        cotizacionDTO.setCostoCalculado(costoTotal);
        
        return cotizacionDTO;
    }

    public static Envio CrearSolicitudEnvio(CotizacionDTO cotizacionDTO, Client cliente) 
            throws ControllException.EnvioCreate, ControllException.CotizacionInvalid, ControllException.TarifaError {
        if (cotizacionDTO == null) {
            throw new ControllException.EnvioCreate("La cotización no puede ser nula");
        }
        if (cliente == null) {
            throw new ControllException.EnvioCreate("El cliente no puede ser nulo");
        }
        if (cotizacionDTO.getDistanciaKm() <= 0) {
            throw new ControllException.EnvioCreate("La distancia en Km es requerida y debe ser mayor a 0");
        }
        if (cotizacionDTO.getTiempoDemora() <= 0) {
            throw new ControllException.EnvioCreate("El tiempo de demora es requerido y debe ser mayor a 0");
        }

        CotizacionDTO cotizacionCompleta = CotizarEnvio(cotizacionDTO);
        TipoPrioridad prioridad = PrioridadConverter.convertir(cotizacionDTO.getPrioridad());

        int idEnvio = EnvioIdGenerator.generarIdEnvio();
        List<Package> paquetes = PackageAdapter.adaptarLista(cotizacionDTO.getPaquetes());

        if (paquetes == null || paquetes.isEmpty()) {
            throw new ControllException.EnvioCreate("Debe incluir al menos un paquete en el envío");
        }

        Date fechaCreacion = new Date();
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaCreacion);
        calendar.add(Calendar.MINUTE, (int) Math.round(cotizacionDTO.getTiempoDemora()));
        Date fechaEstimada = calendar.getTime();

        EnvioBuilder builder = new EnvioBuilder(
            idEnvio,
            "Origen-Destino",
            new ArrayList<>(paquetes),
            TipoEstado.SOLICITADO
        );

        if (cotizacionDTO.getDireccionOrigen() != null && cotizacionDTO.getDireccionDestino() != null) {
            builder.withDirecciones(cotizacionDTO.getDireccionOrigen(), cotizacionDTO.getDireccionDestino());
        }
        
        builder.withCosto(cotizacionCompleta.getCostoCalculado())
               .withfechaCreacion(fechaCreacion)
               .withfechaEstimada(fechaEstimada)
               .withPrioridad(prioridad)
               .withServiciosAdicionales(cotizacionDTO.getServiciosAdicionales())
               .withDistancia(cotizacionDTO.getDistanciaKm())
               .withTiempoDemora((int) cotizacionDTO.getTiempoDemora());
        
        Envio nuevoEnvio = new Envio(builder);
        
        if (cliente.getListEnvio() == null) {
            cliente.setListEnvio(new ArrayList<>());
        }
        cliente.getListEnvio().add(nuevoEnvio);

        if (!gestion.AddEnvio(nuevoEnvio)) {
            throw new ControllException.EnvioCreate("Error al agregar el envío a la gestión");
        }
        
        return nuevoEnvio;
    }

    public static double calcularCostoEnvio(Envio envio, double distanciaKm, String prioridad, List<String> serviciosAdicionales) 
            throws ControllException.TarifaError {
        if (envio == null) {
            throw new ControllException.TarifaError("El envío no puede ser nulo");
        }

        if (distanciaKm <= 0) {
            throw new ControllException.TarifaError("La distancia en Km debe ser mayor a 0");
        }

        if (envio.getListpaquete() == null || envio.getListpaquete().isEmpty()) {
            throw new ControllException.TarifaError("El envío debe tener al menos un paquete");
        }

        TipoPrioridad tipoPrioridad = PrioridadConverter.convertir(prioridad);

        double pesoTotal = TarifaService.calcularPesoTotal(envio.getListpaquete());
        double volumenTotal = TarifaService.calcularVolumenTotal(envio.getListpaquete());

        if (pesoTotal <= 0) {
            throw new ControllException.TarifaError("El peso total debe ser mayor a 0");
        }

        Tarifa tarifa = TarifaService.calcularTarifa(
                distanciaKm,
                pesoTotal,
                volumenTotal,
                tipoPrioridad,
                serviciosAdicionales
        );

        double costoTotal = TarifaService.obtenerCostoTotal(tarifa);

        if (costoTotal <= 0) {
            throw new ControllException.TarifaError("Error al calcular el costo de la tarifa");
        }

        return costoTotal;
    }

}
