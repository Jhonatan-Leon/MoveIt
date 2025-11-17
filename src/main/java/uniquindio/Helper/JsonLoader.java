package uniquindio.Helper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonLoader {
    //Lee el JSON y lo convierte a un objeto indicado
    public static <T> List<T> CargarProductos(String fileName, Class<T> clazz) {
        try (var inputStream = JsonLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            try (var reader = new InputStreamReader(inputStream)) {
                // crear objecto
                Gson gson = new Gson();
                //Indica como tratar el archivo .JSON
                Type listType = TypeToken.getParameterized(List.class, clazz).getType();
                //Lee los datos del json y convierte a un List tipo especifico pasado
                return gson.fromJson(reader, listType);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
