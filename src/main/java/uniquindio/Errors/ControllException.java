package uniquindio.Errors;

import uniquindio.Model.AplicationException;

public class ControllException {
    private ControllException() {
    }

    public static class ErrorServer extends AplicationException {
        public ErrorServer(String message) {
            super(message, 500);
        }
    }

    public static class UserNotFound extends AplicationException {
        public UserNotFound(String message) {
            super(message, 404);
        }
    }

    public static class UserUpdate extends AplicationException {
        public UserUpdate(String message) {
            super(message, 400);
        }
    }

    public static class UserDelete extends AplicationException {
        public UserDelete(String message) {
            super(message, 400);
        }
    }

    public static class UserCreate extends AplicationException {
        public UserCreate(String message) {
            super(message, 400);
        }
    }

    public static class PackageNotFound extends AplicationException {
        public PackageNotFound(String message) {
            super(message, 404);
        }
    }

    public static class PackageCreate extends AplicationException {
        public PackageCreate(String message) {
            super(message, 400);
        }
    }

    public static class EnvioNotFound extends AplicationException {
        public EnvioNotFound(String message) {
            super(message, 404);
        }
    }

    public static class EnvioCreate extends AplicationException {
        public EnvioCreate(String message) {
            super(message, 400);
        }
    }

    public static class CotizacionInvalid extends AplicationException {
        public CotizacionInvalid(String message) {
            super(message, 400);
        }
    }

    public static class TarifaError extends AplicationException {
        public TarifaError(String message) {
            super(message, 500);
        }
    }
}