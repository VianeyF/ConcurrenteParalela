import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SumaMatricesParallel {

    public static void main(String[] args) {
        int n = 4; // tamaño matrices
        int[][] matrizA = generarMatriz(n);
        int[][] matrizB = generarMatriz(n);

        ForkJoinPool pool = new ForkJoinPool();
        SumaMatricesTask task = new SumaMatricesTask(matrizA, matrizB, 0, 0, n, n);

        int[][] resultado = pool.invoke(task);

        // muestra la matriz resultante
        System.out.println("Matriz A + Matriz B = ");
        for (int[] row : resultado) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    // genera la matriz
    public static int[][] generarMatriz(int n) {
        int[][] matriz = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = (int) (Math.random() * 10); 
            }
        }
        return matriz;
    }

    // RecursiveTask para la suma de matrices
    static class SumaMatricesTask extends RecursiveTask<int[][]> {
        private static final int UMBRAL = 2; // tamaño minimo para realizar la tarea sin dividir las tareas
        private int[][] matrizA, matrizB;
        private int filaInicio, columnaInicio, filaFin, columnaFin;

        public SumaMatricesTask(int[][] matrizA, int[][] matrizB, int filaInicio, int columnaInicio, int filaFin, int columnaFin) {
            this.matrizA = matrizA;
            this.matrizB = matrizB;
            this.filaInicio = filaInicio;
            this.columnaInicio = columnaInicio;
            this.filaFin = filaFin;
            this.columnaFin = columnaFin;
        }

        @Override
        protected int[][] compute() {
            int filas = filaFin - filaInicio;
            int columnas = columnaFin - columnaInicio;

            // Si el tamaño es pequeño, realiza la suma directamente
            if (filas <= UMBRAL && columnas <= UMBRAL) {
                int[][] resultado = new int[filas][columnas];
                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {
                        resultado[i][j] = matrizA[filaInicio + i][columnaInicio + j] + matrizB[filaInicio + i][columnaInicio + j];
                    }
                }
                return resultado;
            } else {
                // Divide la tarea en 4 cuadrantes
                int filaMedia = (filaInicio + filaFin) / 2;
                int columnaMedia = (columnaInicio + columnaFin) / 2;

                // Crear subtareas
                SumaMatricesTask topLeft = new SumaMatricesTask(matrizA, matrizB, filaInicio, columnaInicio, filaMedia, columnaMedia);
                SumaMatricesTask topRight = new SumaMatricesTask(matrizA, matrizB, filaInicio, columnaMedia, filaMedia, columnaFin);
                SumaMatricesTask bottomLeft = new SumaMatricesTask(matrizA, matrizB, filaMedia, columnaInicio, filaFin, columnaMedia);
                SumaMatricesTask bottomRight = new SumaMatricesTask(matrizA, matrizB, filaMedia, columnaMedia, filaFin, columnaFin);

                // Ejecuta las subtareas en paralelo
                invokeAll(topLeft, topRight, bottomLeft, bottomRight);

                // Combina los resultados de los cuadrantes
                return combinarMatrices(topLeft.join(), topRight.join(), bottomLeft.join(), bottomRight.join());
            }
        }

        // Combina cuatro cuadrantes en una matriz completa
        private int[][] combinarMatrices(int[][] topLeft, int[][] topRight, int[][] bottomLeft, int[][] bottomRight) {
            int filas = topLeft.length * 2;
            int columnas = topLeft[0].length * 2;
            int[][] resultado = new int[filas][columnas];

            // Rellena la matriz con los cuadrantes
            for (int i = 0; i < topLeft.length; i++) {
                System.arraycopy(topLeft[i], 0, resultado[i], 0, topLeft[i].length);
                System.arraycopy(topRight[i], 0, resultado[i], topLeft[i].length, topRight[i].length);
                System.arraycopy(bottomLeft[i], 0, resultado[i + topLeft.length], 0, bottomLeft[i].length);
                System.arraycopy(bottomRight[i], 0, resultado[i + topLeft.length], bottomLeft[i].length, bottomRight[i].length);
            }

            return resultado;
        }
    }
}
