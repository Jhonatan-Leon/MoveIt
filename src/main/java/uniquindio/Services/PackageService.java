package uniquindio.Services;

import uniquindio.Errors.ControllException;
import uniquindio.Model.Gestion.PackageGestion;
import uniquindio.Model.Package;
import java.util.List;

public class PackageService {
    private static final PackageGestion packageGestion = PackageGestion.getInstance();

    private PackageService(){
    }

    public static List<Package> getAllPackage() throws ControllException.ErrorServer {
        try{
            return packageGestion.getAllPackages();
        }catch (Exception e){
            throw new ControllException.ErrorServer("Error al obtener paquetes: " + e.getMessage());
        }
    }

    public static boolean AddPackage(Package newPackage) throws ControllException.PackageCreate{
        try{
            if(newPackage != null){
                return packageGestion.AddPackage(newPackage);
            }

            if(packageGestion.getPackageById(newPackage.getIdPaquete()) != null){
                throw new ControllException.PackageCreate("Ya existe un paquete con el ID: " + newPackage.getIdPaquete());
            }

            throw new ControllException.PackageCreate("No hay datos para registrar");

        }catch (Exception e){
            throw new ControllException.PackageCreate("Error al registrar paquete: " + e.getMessage());
        }
    }

    public static Package updatePackage(Package updatePackage) throws ControllException.ErrorServer {
        try{
           if(updatePackage == null){
              throw new ControllException.PackageNotFound("No hay datos para actualizar", null);
           }

           if(packageGestion.getPackageById(updatePackage.getIdPaquete()) == null){
               throw new ControllException.PackageNotFound("Paquete no encontrado", null);
           }

           return packageGestion.updatePackage(updatePackage);

        }catch (Exception e){
            throw new ControllException.ErrorServer("Error en el servidor"+ e.getMessage());
        }
    }

    public static Package getPackageById(String Id) throws ControllException.ErrorServer {
        try{
            if(Id.isEmpty()){
                throw new ControllException.PackageNotFound("Se necesita el identificador unico para buscar el paquete", null);
            }

            return packageGestion.getPackageById(Id);
        }catch (Exception e){
            throw new ControllException.ErrorServer("Error en el servidor");
        }
    }

    public static boolean DeletePackage(String Id) throws ControllException.ErrorServer{
        try{
            if(Id.isEmpty()){
                throw new ControllException.PackageNotFound("Se necesita el identificador unico para eliminar el paquete", null);
            }

            Package p = packageGestion.getPackageById(Id);

            if(p == null){
                throw new ControllException.PackageNotFound("Paquete no encontrado", null);
            }

            return packageGestion.deletePackage(p);
        }catch (Exception e){
            throw new ControllException.ErrorServer("Error en el servidor");
        }
    }
}
