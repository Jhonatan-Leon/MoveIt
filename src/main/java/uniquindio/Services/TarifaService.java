package uniquindio.Services;

import uniquindio.Model.Interface.RecargoStrategy;
import uniquindio.Model.Tarifa;
import uniquindio.Model.TipoPrioridad;
import uniquindio.Model.Package;
import uniquindio.Utils.RecargoStrategyFactory;
import java.util.List;

public class TarifaService {
    private static final double TARIFA_BASE = 1000.0;
    private static final double TARIFA_PESO = 500.0;
    private static final double TARIFA_VOLUMEN = 2000.0;

    //Recargos
    private static final double PRIORIDAD_ALTA = 0.3;
    private static final double PRIORIDAD_MEDIA = 0.15;
    private static final double PRIORIDAD_BAJA = 0.0;

    //No se como utilizar esta parte
    private static final double RECARGO_SEGURO = 0.1;
    private static final double RECARGO_FRAGIL = 0.15;
    private static final double RECARGO_PRIORIDAD = 0.2;

    public static Tarifa calcularTarifa(double distanciaKm, double pesoTotal, double volumenTotal, 
                                       TipoPrioridad prioridad, List<String> serviciosAdicionales) {
        

        double costoBase = distanciaKm * TARIFA_BASE;
        double costoPeso = pesoTotal * TARIFA_PESO;
        double costoVolumen = volumenTotal * TARIFA_VOLUMEN;
        double subtotal = costoBase + costoPeso + costoVolumen;

        double recargoPrioridad = 0.0;
        switch (prioridad) {
            case Alta:
                recargoPrioridad = subtotal * PRIORIDAD_ALTA;
                break;
            case Media:
                recargoPrioridad = subtotal * PRIORIDAD_MEDIA;
                break;
            case Baja:
                recargoPrioridad = subtotal * PRIORIDAD_BAJA;
                break;
        }

        // PATRÓN: Strategy (Comportamental)
        // Aplicar recargos por servicios adicionales usando estrategias
        // Esto permite agregar nuevos tipos de recargo sin modificar este código
        double recargoServicios = 0.0;
        if (serviciosAdicionales != null) {
            for (String servicio : serviciosAdicionales) {
                RecargoStrategy estrategia = RecargoStrategyFactory.getEstrategia(servicio);
                if (estrategia != null) {
                    recargoServicios += estrategia.calcularRecargo(subtotal);
                }
            }
        }
        
        // Calcular recarga adicional total
        float recargaAdicional = (float) (recargoPrioridad + recargoServicios);
        
        // Crear y retornar la tarifa
        return new Tarifa(
            (float) distanciaKm,
            (float) pesoTotal,
            (float) volumenTotal,
            prioridad,
            recargaAdicional
        );
    }

    //Calcular costo de la tarifa
    public static double obtenerCostoTotal(Tarifa tarifa) {
        double costoBase = tarifa.getDistancia() * TARIFA_BASE;
        double costoPeso = tarifa.getPeso() * TARIFA_PESO;
        double costoVolumen = tarifa.getVolumen() * TARIFA_VOLUMEN;
        double subtotal = costoBase + costoPeso + costoVolumen;
        
        return subtotal + tarifa.getRecargaAdicional();
    }
    
    //Calcular volumen total de una lista de paquetes
    public static double calcularVolumenTotal(List<Package> paquetes) {
        if (paquetes == null || paquetes.isEmpty()) {
            return 0.0;
        }
        
        double volumenTotal = 0.0;
        for (Package paquete : paquetes) {
            // Volumen en m³
            double volumen = (paquete.getLargo() / 100.0) * (paquete.getAncho() / 100.0) * (paquete.getAlto() / 100.0);
            volumenTotal += volumen;
        }
        return volumenTotal;
    }

    //Calcular peso total de una lista de paquetes
    public static double calcularPesoTotal(List<Package> paquetes) {
        if (paquetes == null || paquetes.isEmpty()) {
            return 0.0;
        }
        
        double pesoTotal = 0.0;
        for (Package paquete : paquetes) {
            pesoTotal += paquete.getPeso();
        }
        return pesoTotal;
    }
}

