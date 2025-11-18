package uniquindio.Services;

import uniquindio.Errors.ControllException;
import uniquindio.Model.Gestion.PackageGestion;
import uniquindio.Model.Package;
import java.util.List;

public class PackageService {
    private static final PackageGestion packageGestion = PackageGestion.getInstance();

    private PackageService(){
    }

    public static List<Package> getAllPackage()  {
        return packageGestion.getAllPackages();
    }

    public static boolean AddPackage(Package newPackage) throws ControllException.PackageCreate{
        if(newPackage != null){
            return packageGestion.AddPackage(newPackage);
        }

        if(packageGestion.getPackageById(newPackage.getIdPaquete()) != null){
            throw new ControllException.PackageCreate("Ya existe un paquete con el ID: " + newPackage.getIdPaquete());
        }

        throw new ControllException.PackageCreate("No hay datos para registrar");

    }

    public static Package updatePackage(Package updatePackage) throws ControllException.PackageNotFound {
        if(updatePackage == null){
            throw new ControllException.PackageNotFound("No hay datos para actualizar");
        }

        if(packageGestion.getPackageById(updatePackage.getIdPaquete()) == null){
            throw new ControllException.PackageNotFound("Paquete no encontrado");
        }
        return packageGestion.updatePackage(updatePackage);
    }

    public static Package getPackageById(String Id) throws ControllException.PackageNotFound {
        if(Id.isEmpty()) {
            throw new ControllException.PackageNotFound("Se necesita el identificador unico para buscar el paquete");
        }

        return packageGestion.getPackageById(Id);
    }

    public static boolean DeletePackage(String Id) throws ControllException.PackageNotFound{
        if(Id.isEmpty()){
            throw new ControllException.PackageNotFound("Se necesita el identificador unico para eliminar el paquete");
        }

        Package p = packageGestion.getPackageById(Id);
        if(p == null){
            throw new ControllException.PackageNotFound("Paquete no encontrado");
        }
        return packageGestion.deletePackage(p);
    }
}
