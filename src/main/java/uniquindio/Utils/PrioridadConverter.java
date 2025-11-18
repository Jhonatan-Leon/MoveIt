package uniquindio.Utils;

import uniquindio.Model.TipoPrioridad;

public class PrioridadConverter {
    
    //Convierte un string a un TipoPrioridad
    public static TipoPrioridad convertir(String prioridadStr) {
        if (prioridadStr == null || prioridadStr.trim().isEmpty()) {
            return TipoPrioridad.Baja;
        }
        
        switch (prioridadStr.toLowerCase()) {
            case "alta":
                return TipoPrioridad.Alta;
            case "media":
                return TipoPrioridad.Media;
            case "baja":
                return TipoPrioridad.Baja;
            default:
                return TipoPrioridad.Baja;
        }
    }
}

