package edu.co.Model;

import edu.co.Model.Interface.MetodoPago;

public class PagoFactory {
    public static MetodoPago GetMetodoPago(String metodoPago) {
        switch (metodoPago) {
            case "PSE":
                return new PSE();
            case "Tarjeta":
                return new Tarjeta();
            default:
                return null;
        }
    }
}
