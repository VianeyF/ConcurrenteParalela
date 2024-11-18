import java.util.concurrent.ConcurrentHashMap;

public class EjemploConcurrentHashMap {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> mapa = new ConcurrentHashMap<>();
        
        // Agregar elementos
        mapa.put("A", 1);
        mapa.put("B", 2);

        // Operación concurrente
        mapa.computeIfAbsent("C", key -> 3);

        // Iteración segura
        mapa.forEach((clave, valor) -> {
            System.out.println(clave + ": " + valor);
        });
    }
}
