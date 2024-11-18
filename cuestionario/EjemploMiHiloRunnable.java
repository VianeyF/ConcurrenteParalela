/* La clase MiRunnable implementa la interfaz Runnable y sobrescribe el método run().
Similar al primer ejemplo, se imprime un mensaje y se duerme el hilo.
En la clase Main, se crea una instancia de MiRunnable, luego se crea un objeto Thread 
pasando esta instancia y finalmente se inicia el hilo.*/

class MiRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Ejecutando runnable: " + i);
            try {
                Thread.sleep(500); // Dormir el hilo por 500 ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class EjemploMiHiloRunnable {
    public static void main(String[] args) {
        MiRunnable miRunnable = new MiRunnable(); // Crear una instancia de Runnable
        Thread hilo = new Thread(miRunnable); // Crear un hilo pasando el Runnable
        hilo.start(); // Iniciar la ejecución del hilo
    }
}