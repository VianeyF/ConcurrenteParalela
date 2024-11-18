class MonitorDemo {
    private int counter = 0;

    // Método sincronizado para incrementar el contador
    synchronized void incrementar() {
        counter++;
        System.out.println("Contador: " + counter);
    }

    // Método sincronizado para obtener el valor del contador
    synchronized int obtenerValor() {
        return counter;
    }
}

// Clase que extiende Thread para incrementar el contador
class HiloIncrementador extends Thread {
    private final MonitorDemo monitor;

    HiloIncrementador(MonitorDemo monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            monitor.incrementar();
            try {
                Thread.sleep(100); // Pausa de 100 milisegundos
            } catch (InterruptedException e) {
                System.out.println("Hilo interrumpido: " + e);
            }
        }
    }
}

// Clase principal para ejecutar el programa
public class EjemploMonitor {
    public static void main(String[] args) {
        MonitorDemo monitor = new MonitorDemo();
        
        // Crear dos hilos que comparten el monitor
        HiloIncrementador hilo1 = new HiloIncrementador(monitor);
        HiloIncrementador hilo2 = new HiloIncrementador(monitor);

        // Iniciar los hilos
        hilo1.start();
        hilo2.start();

        // Esperar a que los hilos terminen
        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            System.out.println("Error al unir los hilos: " + e);
        }

        // Mostrar el valor final del contador
        System.out.println("Valor final del contador: " + monitor.obtenerValor());
    }
}
