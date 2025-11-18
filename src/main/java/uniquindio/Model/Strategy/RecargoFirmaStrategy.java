package uniquindio.Model.Strategy;

import uniquindio.Model.Interface.RecargoStrategy;

public class RecargoFirmaStrategy implements RecargoStrategy {
    private static final double PORCENTAJE_RECARGO = 0.05; // 5%
    
    @Override
    public double calcularRecargo(double subtotal) {
        return subtotal * PORCENTAJE_RECARGO;
    }
    
    @Override
    public String getNombreServicio() {
        return "firma requerida";
    }
}

