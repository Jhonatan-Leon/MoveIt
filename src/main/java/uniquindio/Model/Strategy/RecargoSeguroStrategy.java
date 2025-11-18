package uniquindio.Model.Strategy;

import uniquindio.Model.Interface.RecargoStrategy;

public class RecargoSeguroStrategy implements RecargoStrategy {
    private static final double PORCENTAJE_RECARGO = 0.10; // 10%
    
    @Override
    public double calcularRecargo(double subtotal) {
        return subtotal * PORCENTAJE_RECARGO;
    }
    
    @Override
    public String getNombreServicio() {
        return "seguro";
    }
}

