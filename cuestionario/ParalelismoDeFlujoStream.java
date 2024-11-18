import java.util.stream.IntStream;

public class ParalelismoDeFlujoStream {
    public static void main(String[] args) {
        IntStream.range(1, 10) // Generar datos
                 .parallel()   // Ejecutar en paralelo
                 .map(n -> n * 2) // Primera etapa: duplicar cada número
                 .filter(n -> n % 3 == 0) // Segunda etapa: filtrar múltiplos de 3
                 .forEach(System.out::println); // Tercera etapa: imprimir resultados
    }
}
