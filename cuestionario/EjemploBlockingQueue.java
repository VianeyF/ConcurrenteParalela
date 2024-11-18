import java.util.concurrent.ArrayBlockingQueue;

public class EjemploBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> cola = new ArrayBlockingQueue<>(10);

        // Productor
        Thread productor = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    cola.put(i);
                    System.out.println("Producido: " + i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Consumidor
        Thread consumidor = new Thread(() -> {
            try {
                while (true) {
                    Integer valor = cola.take();
                    System.out.println("Consumido: " + valor);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        productor.start();
        consumidor.start();

        productor.join();
        consumidor.interrupt();
    }
}
