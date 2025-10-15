package edu.co.Model.Gestion;

import edu.co.Model.Direccion;

import java.util.ArrayList;
import java.util.List;

public class GestionDireccion {
    private static final List<Direccion> listDireccion = new ArrayList<>();
    private static GestionDireccion instance;

    public static GestionDireccion getInstance(){
        if(instance == null){
            instance = new GestionDireccion();
        }
        return instance;
    }

    public static List<Direccion> getDireccion(){
        return new ArrayList<>(listDireccion);
    };

    public static boolean AddDireccion(Direccion direction) {
        listDireccion.add(direction);
        return true;
    };

    public static boolean DeleteDireccion(String Id){
        for(Direccion d: listDireccion) {
            if(d.getIdDireccion().equals(Id)){
                listDireccion.remove(Id);
                return true;
            }
        }
        return  false;
    };

    public static Direccion getDireccion(String Id) {
      for(Direccion d: listDireccion) {
          if (d.getIdDireccion().equals(Id)) {
            return  d;
          };
      }
      return null;
    };

    public static boolean UpdateDrieccion(String Id, Direccion direction) {
        for(Direccion d: listDireccion) {
            if (d.getIdDireccion().equals(Id)) {
                d.setIdDireccion((direction.getIdDireccion()));
                d.setAlias((direction.getAlias()));
                d.setCalle((direction.getCalle()));
                d.setCiudad((direction.getCiudad()));
                d.setCoordenadas((direction.getCoordenadas()));
                return true;
            }
        }
        return false;
    };

    public static  List<Direccion> getDireccionByAlias(String Alias){
        for(Direccion d: listDireccion) {
            if(d.getAlias().equals(Alias)){
                return  List.of(d);
            }
        }
        return  null;
    };


}
