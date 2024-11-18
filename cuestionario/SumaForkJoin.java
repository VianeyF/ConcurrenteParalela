import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SumaForkJoin extends RecursiveTask<Long> {
    private final long[] numeros;
    private final int inicio;
    private final int fin;
    private static final int THRESHOLD = 1000; // Umbral para dividir tareas

    public SumaForkJoin(long[] numeros, int inicio, int fin) {
        this.numeros = numeros;
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override
    protected Long compute() {
        if (fin - inicio <= THRESHOLD) {
            // Sumar directamente si el rango es menor o igual al umbral
            long suma = 0;
            for (int i = inicio; i < fin; i++) {
                suma += numeros[i];
            }
            return suma;
        } else {
            // Dividir la tarea en subtareas
            int medio = (inicio + fin) / 2;
            SumaForkJoin tareaIzquierda = new SumaForkJoin(numeros, inicio, medio);
            SumaForkJoin tareaDerecha = new SumaForkJoin(numeros, medio, fin);
            
            // Ejecutar las subtareas en paralelo
            tareaIzquierda.fork(); // Iniciar la tarea izquierda
            long resultadoDerecho = tareaDerecha.compute(); // Ejecutar la tarea derecha en el hilo actual
            long resultadoIzquierdo = tareaIzquierda.join(); // Unir el resultado de la tarea izquierda
            
            return resultadoIzquierdo + resultadoDerecho; // Combinar resultados
        }
    }

    public static void main(String[] args) {
        long[] numeros = new long[10_000_000]; // Crear un arreglo grande
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = i + 1; // Inicializar con valores
        }

        ForkJoinPool pool = new ForkJoinPool(); // Crear un pool de ForkJoin
        SumaForkJoin tarea = new SumaForkJoin(numeros, 0, numeros.length); // Crear la tarea
        long resultado = pool.invoke(tarea); // Invocar la tarea y obtener el resultado
        
        System.out.println("La suma es: " + resultado);
    }
}