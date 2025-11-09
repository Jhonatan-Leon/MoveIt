package uniquindio.Facade;

import uniquindio.Errors.ControllException;
import uniquindio.Services.PackageService;
import uniquindio.Model.Package;
import java.util.List;

public class PackageFacade {
    private static PackageFacade instance;

    public PackageFacade getInstance(){
        if(instance == null){
            instance = new PackageFacade();
        }
        return instance;
    }

    public List<Package> getAllPackages() throws ControllException.ErrorServer{
        return PackageService.getAllPackage();
    }

    public boolean AddPackage(Package newPackage) throws ControllException.PackageCreate{
        return PackageService.AddPackage(newPackage);
    }

    public Package updatePackage(Package updatePackage) throws ControllException.ErrorServer{
        return PackageService.updatePackage(updatePackage);
    }

    public boolean deletePackage(String Id) throws ControllException.ErrorServer{
        return PackageService.DeletePackage(Id);
    }


}
