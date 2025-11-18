package uniquindio.Model.Strategy;

import uniquindio.Model.Interface.RecargoStrategy;

public class RecargoFragilStrategy implements RecargoStrategy {
    private static final double PORCENTAJE_RECARGO = 0.15; // 15%
    
    @Override
    public double calcularRecargo(double subtotal) {
        return subtotal * PORCENTAJE_RECARGO;
    }
    
    @Override
    public String getNombreServicio() {
        return "frágil";
    }
}

