package uniquindio.Mappers;

import uniquindio.Model.DTO.ClientSesionDTO;
import uniquindio.Model.DTO.RepartidorSesionDTO;
import uniquindio.Model.Repartidor;

public class RepartidorMapper {
    public static RepartidorSesionDTO toDTO(Repartidor repartidor) {
        if (repartidor == null) return null;

        return new RepartidorSesionDTO(
                repartidor.getNumeroDocumento(),
                repartidor.getNombreCompleto(),
                repartidor.getEmail(),
                repartidor.getTelefono(),
                repartidor.isEstado()
        );
    }
}
