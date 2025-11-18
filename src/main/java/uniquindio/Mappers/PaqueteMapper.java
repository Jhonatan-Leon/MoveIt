package uniquindio.Mappers;

import uniquindio.Model.Package;
import uniquindio.Model.ClassBuilder.PaqueteBuilder;
import uniquindio.Model.DTO.PaqueteDTO;

public class PaqueteMapper {

    public static Package toEntity(PaqueteDTO dto) {

        float peso = (float) dto.getPeso();
        float ancho = (float) dto.getAncho();
        float alto = (float) dto.getAlto();

        PaqueteBuilder builder = new PaqueteBuilder(dto.getDescripcion(),
                peso,
                ancho,
                alto);

        return new Package(builder);
    }
}
