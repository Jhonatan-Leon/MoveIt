package uniquindio.Utils;

import uniquindio.Model.Interface.RecargoStrategy;
import uniquindio.Model.Strategy.*;

import java.util.HashMap;
import java.util.Map;

public class RecargoStrategyFactory {
    private static final Map<String, RecargoStrategy> estrategias = new HashMap<>();
    
    static {
        estrategias.put("seguro", new RecargoSeguroStrategy());
        estrategias.put("fragil", new RecargoFragilStrategy());
        estrategias.put("frágil", new RecargoFragilStrategy());
        estrategias.put("firma", new RecargoFirmaStrategy());
        estrategias.put("prioridad", new RecargoPrioridadStrategy());
    }

    public static RecargoStrategy getEstrategia(String nombreServicio) {
        if (nombreServicio == null) {
            return null;
        }
        return estrategias.get(nombreServicio.toLowerCase());
    }
}

