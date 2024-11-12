import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ProductorConsumidorMultiples {

    public static void main(String[] args) {
        Buffer buffer = new Buffer(5); // Tamaño del buffer
        Thread productor1 = new Thread(new Productor(buffer), "Productor 1");
        Thread productor2 = new Thread(new Productor(buffer), "Productor 2");
        Thread consumidor1 = new Thread(new Consumidor(buffer), "Consumidor 1");
        Thread consumidor2 = new Thread(new Consumidor(buffer), "Consumidor 2");

        productor1.start();
        productor2.start();
        consumidor1.start();
        consumidor2.start();
    }

    // Clase Buffer
    static class Buffer {
        private final List<Integer> lista;
        private final int capacidad;

        public Buffer(int capacidad) {
            this.lista = new LinkedList<>();
            this.capacidad = capacidad;
        }

        // metodo para agregar un elemento al buffer (producir)
        public synchronized void agregar(int valor) throws InterruptedException {
            // espera mientras el buffer esta lleno
            while (lista.size() == capacidad) {
                wait();
            }
            lista.add(valor);
            System.out.println(Thread.currentThread().getName() + " produjo: " + valor);
            notifyAll(); //dice a los consumidores que hay un nuevo elemento 
        }

        // metodo para quitar un elemento del buffer (consumir)
        public synchronized int quitar() throws InterruptedException {
            // espera mientras el buffer esta vacío
            while (lista.isEmpty()) {
                wait();
            }
            int valor = lista.remove(0);
            System.out.println(Thread.currentThread().getName() + " consumió: " + valor);
            notifyAll(); // dice a los productores que hay espacio disponible
            return valor;
        }
    }

    // Clase Productor
    static class Productor implements Runnable {
        private final Buffer buffer;
        private final Random random = new Random();

        public Productor(Buffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    int valor = random.nextInt(100); // genera un numero aleatorio
                    buffer.agregar(valor);
                    Thread.sleep(500); // simula tiempo para producir
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Clase Consumidor
    static class Consumidor implements Runnable {
        private final Buffer buffer;

        public Consumidor(Buffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    int valor = buffer.quitar();
                    Thread.sleep(1000); // simula tiempo de procesamiento
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
