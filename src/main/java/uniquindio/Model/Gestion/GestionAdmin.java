package uniquindio.Model.Gestion;

import uniquindio.Helper.JsonLoader;
import uniquindio.Model.Admin;

import java.util.ArrayList;
import java.util.List;

public class GestionAdmin {
    private static List<Admin> LIST_ADMIN = new ArrayList<>();
    private static GestionAdmin instance;

    private GestionAdmin() {
        LIST_ADMIN = JsonLoader.CargarProductos("DatosQuemados/Admin.json", Admin.class);
    }

    public static GestionAdmin getInstance() {
        if (instance == null) {
            instance = new GestionAdmin();
        }
        return instance;
    }

    public Admin addAdmin(Admin admin) {
        LIST_ADMIN.add(admin);
        return admin;
    }

    public List<Admin> getAdmins() {
        return new ArrayList<>(LIST_ADMIN);
    }

    public Admin getAdmin(String email) {
        for (Admin a : LIST_ADMIN) {
            if (a.getEmail().equals(email)) {
                return a;
            }
        }
        return null;
    }

    public Admin getAdminById(String id) {
        for (Admin a : LIST_ADMIN) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return null;
    }

    public Admin updateAdmin(String id, Admin admin) {
        for (Admin a : LIST_ADMIN) {
            if (a.getId().equals(id)) {
                a.setNombreCompleto(admin.getNombreCompleto());
                a.setEmail(admin.getEmail());
                a.setPassword(admin.getPassword());
                a.setTelefono(admin.getTelefono());
                a.setTipoDocumento(admin.getTipoDocumento());
                a.setNumeroDocumento(admin.getNumeroDocumento());
                a.setEstado(admin.isEstado());
                // Los atributos de la clase base User deben ser públicos o tener setters.
                return a;
            }
        }
        return null;
    }


    public boolean deleteAdmin(String id) {
        for (Admin a : LIST_ADMIN) {
            if (a.getId().equals(id)) {
                LIST_ADMIN.remove(a);
                return true;
            }
        }
        return false;
    }

    public Admin getAdminByMail(String email) {
        for (Admin a : LIST_ADMIN) {
            if (a.getEmail().equals(email)) {
                return a;
            }
        }
        return null;
    }
}