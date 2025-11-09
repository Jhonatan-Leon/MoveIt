package uniquindio.Helper;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;

public class HashPassword {
    private static String hashAlmacenado = "c7Jt+rL2x1rGk9f7M0N/9Q==:8X/Qz5n4HkP9rXp9P9eG9v7lQ3Z2rT/jJ5F8sL8R2hQ=";

    private HashPassword(){}

    public static byte[] hashPassword(final String password, final int iterations) throws NoSuchAlgorithmException {
        try{
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), hashAlmacenado.getBytes(), iterations, 32);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return skf.generateSecret(spec).getEncoded();
        }catch (Exception e){
            throw new RuntimeException("Error al hashear la contraseña", e);
        }
    }
}
