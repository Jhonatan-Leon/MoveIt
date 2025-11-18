package uniquindio.Model.Strategy;

import uniquindio.Model.Interface.RecargoStrategy;

/**
 * PATRÓN: Strategy (Comportamental) - Implementación concreta
 * 
 * Estrategia para calcular el recargo por servicio de prioridad adicional.
 * Aplica un 20% de recargo sobre el subtotal.
 */
public class RecargoPrioridadStrategy implements RecargoStrategy {
    private static final double PORCENTAJE_RECARGO = 0.20; // 20%
    
    @Override
    public double calcularRecargo(double subtotal) {
        return subtotal * PORCENTAJE_RECARGO;
    }
    
    @Override
    public String getNombreServicio() {
        return "prioridad";
    }
}

