package uniquindio.Model.Gestion;

import uniquindio.Model.Package;
import java.util.ArrayList;
import java.util.List;

public class PackageGestion {
    private static PackageGestion Instance;
    private static final List<Package> listPackage = new ArrayList<>();

    public static PackageGestion getInstance() {
        if(Instance == null){
            return new PackageGestion();
        }
        return Instance;
    }

    private PackageGestion() {
    }

    public List<Package> getAllPackages(){
        return new ArrayList<>(listPackage);
    }

    public boolean AddPackage(Package newPackage){
        listPackage.add(newPackage);
        return true;
    }

    public Package updatePackage(Package updatePackage){
        for(Package p : listPackage) {
            if(p.equals(updatePackage)){
                p.setPeso(updatePackage.getPeso());
                p.setAlto(updatePackage.getAlto());
                p.setAncho(updatePackage.getAncho());
                p.setLargo(updatePackage.getLargo());
            }
        }

        return null;
    }


    public boolean deletePackage(Package p){
        return listPackage.remove(p);
    }

    public Package getPackageById(String Id){
        for(Package p: listPackage){
            if(p.getIdPaquete().equals(Id)){
                return p;
            }
        }
        return null;
    }
}
