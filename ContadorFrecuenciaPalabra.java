import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class ContadorFrecuenciaPalabra {
    public static void main(String[] args) {
        String filePath = "C:/ConcurrenteParalela/texto.txt"; 

        // ConcurrentHashMap para acceso seguro en paralelo
        Map<String, Integer> PalabraContMap = new ConcurrentHashMap<>();

        try (Stream<String> lineas = Files.lines(Paths.get(filePath))) {
            // procesa cada línea en paralelo
            lineas.parallel()
                .flatMap(linea -> Stream.of(linea.split("\\s+"))) // divide las palabras por espacios
                .forEach(palabra -> PalabraContMap.merge(palabra, 1, Integer::sum)); //conteo de palabras
        } catch (IOException e) {
            e.printStackTrace();
        }

        // muestra el conteo de palabras
        PalabraContMap.forEach((palabra, cont) -> 
            System.out.println("Palabra: " + palabra + " | Frecuencia: " + cont)
        );
    }
}
