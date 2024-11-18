/*La clase MiHilo extiende Thread y sobrescribe el método run().
En el método run(), se imprime un mensaje y se duerme el hilo durante 500 milisegundos.
En la clase Main, se crea una instancia de MiHilo y se inicia llamando al método start() */

class MiHilo extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Ejecutando hilo: " + i);
            try {
                Thread.sleep(500); // Dormir el hilo por 500 ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class EjemploMiHiloThread {
    public static void main(String[] args) {
        MiHilo hilo = new MiHilo(); // Crear una instancia del hilo
        hilo.start(); // Iniciar la ejecución del hilo
    }
}