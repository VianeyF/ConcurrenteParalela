/*El paralelismo de datos se utiliza para ejecutar la misma operación 
sobre diferentes partes de un conjunto de datos. Un ejemplo clásico es
 el uso de la API de Streams en Java para procesar una lista en paralelo: */

import java.util.Arrays;
import java.util.List;

public class ParalelismoDatos {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Procesamiento paralelo para calcular el cuadrado de cada número
        numbers.parallelStream()
               .map(n -> n * n)
               .forEach(result -> System.out.println("Square: " + result));
    }
}