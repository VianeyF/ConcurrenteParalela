import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class TareaBusquedaForkJoin extends RecursiveTask<Boolean> {
    private static final int UMBRAL = 100;
    private int[] numeros;
    private int inicio, fin;
    private int objetivo;

    public TareaBusquedaForkJoin(int[] numeros, int inicio, int fin, int objetivo) {
        this.numeros = numeros;
        this.inicio = inicio;
        this.fin = fin;
        this.objetivo = objetivo;
    }

    @Override
    protected Boolean compute() {
        if (fin - inicio <= UMBRAL) {
            for (int i = inicio; i < fin; i++) {
                if (numeros[i] == objetivo) {
                    return true; // Número encontrado
                }
            }
            return false; // Número no encontrado
        } else {
            int medio = (inicio + fin) / 2;
            TareaBusquedaForkJoin tareaIzquierda = new TareaBusquedaForkJoin(numeros, inicio, medio, objetivo);
            TareaBusquedaForkJoin tareaDerecha = new TareaBusquedaForkJoin(numeros, medio, fin, objetivo);
            invokeAll(tareaIzquierda, tareaDerecha); // Invocar ambas tareas
            return tareaIzquierda.join() || tareaDerecha.join(); // Combinar resultados
        }
    }

    public static void main(String[] args) {
        int[] numeros = new int[1000000];
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = i; // Inicializar el arreglo
        }

        int objetivo = 999999;

        ForkJoinPool grupo = new ForkJoinPool();
        long inicioTiempo = System.currentTimeMillis();

        boolean encontrado = grupo.invoke(new TareaBusquedaForkJoin(numeros, 0, numeros.length, objetivo));

        long finTiempo = System.currentTimeMillis();

        System.out.println("Número " + (encontrado ? "encontrado" : "no encontrado"));
        System.out.println("Tiempo de ejecución (Fork/Join): " + (finTiempo - inicioTiempo) + " ms");
    }
}