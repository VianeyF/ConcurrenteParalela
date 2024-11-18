public class MiHiloDaemon extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println("Hilo daemon en ejecución");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MiHiloDaemon hilo = new MiHiloDaemon();
        hilo.setDaemon(true); // Establecer como daemon
        hilo.start(); // Iniciar el hilo daemon

        // El hilo principal termina aquí
        System.out.println("Fin del hilo principal");
    }
}