package uniquindio.Model.Strategy;

import uniquindio.Model.Interface.RecargoStrategy;

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

