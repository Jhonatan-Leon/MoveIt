package uniquindio.Controller;

import uniquindio.Errors.ControllException;
import uniquindio.Facade.EnvioFacade;
import uniquindio.Model.DTO.CotizacionDTO;

public class CotizacionController {

    public static CotizacionDTO cotizarTarifa(CotizacionDTO cotizacionDTO) 
            throws ControllException.CotizacionInvalid, ControllException.TarifaError {
        try {
            return EnvioFacade.cotizarTarifa(cotizacionDTO);
        } catch (ControllException.CotizacionInvalid | ControllException.TarifaError e) {
            throw e;
        } catch (Exception e) {
            throw new ControllException.TarifaError("Error al procesar la cotización: " + e.getMessage());
        }
    }
}

