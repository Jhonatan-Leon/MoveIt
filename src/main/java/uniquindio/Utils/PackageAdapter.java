package uniquindio.Utils;

import uniquindio.Model.ClassBuilder.PaqueteBuilder;
import uniquindio.Model.DTO.PaqueteDTO;
import uniquindio.Model.Package;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PackageAdapter {
    public static Package adaptar(PaqueteDTO dto) {
        if (dto == null) {
            return null;
        }
        
        // Generar ID único para el paquete
        String idPaquete = UUID.randomUUID().toString();
        
        // Construir el paquete usando el Builder Pattern
        PaqueteBuilder builder = new PaqueteBuilder(
            idPaquete,
            (float) dto.getPeso(),
            (float) dto.getAlto(),
            (float) dto.getAncho()
        );
        
        if (dto.getLargo() > 0) {
            builder.withlargo((float) dto.getLargo());
        }
        
        return new Package(builder);
    }

    public static List<Package> adaptarLista(List<PaqueteDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<Package> paquetes = new ArrayList<>();
        for (PaqueteDTO dto : dtos) {
            paquetes.add(adaptar(dto));
        }
        
        return paquetes;
    }
}

