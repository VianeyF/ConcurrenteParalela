import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ParalelismoDeFlujoColas {
    public static void main(String[] args) {
        BlockingQueue<Integer> etapa1 = new LinkedBlockingQueue<>();
        BlockingQueue<Integer> etapa2 = new LinkedBlockingQueue<>();

        // Productor: llena la primera cola
        Thread productor = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    etapa1.put(i);
                }
                etapa1.put(-1); // Señal de fin
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Consumidor/Procesador de la primera etapa
        Thread procesador1 = new Thread(() -> {
            try {
                while (true) {
                    int dato = etapa1.take();
                    if (dato == -1) {
                        etapa2.put(-1); // Pasar señal de fin
                        break;
                    }
                    etapa2.put(dato * 2); // Procesar y pasar a la siguiente etapa
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Consumidor final
        Thread consumidor = new Thread(() -> {
            try {
                while (true) {
                    int dato = etapa2.take();
                    if (dato == -1) break; // Fin del flujo
                    System.out.println("Resultado final: " + dato);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Iniciar los hilos
        productor.start();
        procesador1.start();
        consumidor.start();
    }
}
