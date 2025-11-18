package uniquindio.Utils;

import uniquindio.Model.Gestion.GestionEnvios;
import java.util.Random;

public class EnvioIdGenerator {
    private static final Random random = new Random();

    public static int generarIdEnvio() {
        int nuevoId;
        GestionEnvios gestion = GestionEnvios.getInstance();
        
        do {
            // Generar un ID aleatorio entre 1000 y 9999
            nuevoId = 1000 + random.nextInt(9000);
        } while (gestion.getEnvio(nuevoId) != null);
        
        return nuevoId;
    }
}

