public class AlgoritmoLamport {
    static int Tam = 4;
    static int[] numero = {0, 0, 0, 0};
    static boolean[] eligiendo = {false, false, false, false};
    static int Max;

    public static void Mostrar(int i) {
        System.out.println("\nSoy la sección crítica del hilo: " + i + "\n");
    }

    public static int max(int[] vector) {
        Max = 0;
        for (int k = 0; k < Tam; k++) {
            if (vector[k] > Max) {
                Max = vector[k];
            }
        }
        return Max;
    }

    public static void hilo(int i, int iteraciones) {
        int count = 0;
        while (count < iteraciones) {
            eligiendo[i] = true;
            numero[i] = 1 + max(numero);
            eligiendo[i] = false;

            for (int j = 0; j < Tam; j++) {
                while (eligiendo[j]) {
                    // Espera activa mientras el hilo j está eligiendo
                }
                while ((numero[j] != 0) && (numero[j] < numero[i])) {
                    // Espera activa mientras el hilo j tiene un número menor
                }
            }

            // Sección crítica
            Mostrar(i);
            numero[i] = 0; // Sale de la sección crítica
            count++;
        }
    }

    public static void main(String[] args) {
        System.out.println("Algoritmo de Lamport");

        // Simulación de varios hilos (esto es solo un esqueleto)
        // En una implementación real, deberías crear hilos aquí.
        for (int i = 0; i < Tam; i++) {
            final int idHilo = i; // Necesario para el uso en lambda o hilos
            new Thread(() -> hilo(idHilo, 5)).start(); // Inicia cada hilo
        }
    }
}