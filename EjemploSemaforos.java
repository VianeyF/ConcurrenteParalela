
import java.util.concurrent.Semaphore;

class RecursoCompartido {

    private final Semaphore semaforo = new Semaphore(2);

    public void usarRecurso(int id) {
        try {
            System.out.println("Hilo" + id + "esperando acceder al recurso...");
            semaforo.acquire();

            System.out.println("Hilo" + id + "ha accedido al recurso");
            Thread.sleep(2000);
            System.out.println("Hilo" + id + "ha terminado de usar el recurso");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforo.release();
            System.out.println("Hilo" + id + "ha liberado el recurso");

        }
    }
}

class HiloTrabajo extends Thread {

    private final RecursoCompartido recurso;
    private final int id;

    public HiloTrabajo(RecursoCompartido recurso, int id) {
        this.recurso = recurso;
        this.id = id;
    }

    @Override
    public void run() {
        recurso.usarRecurso(id);
    }

}

public class EjemploSemaforos {

    public static void main(String[] args) {
        RecursoCompartido recurso = new RecursoCompartido();

        for (int i=1; i < 5; i++) {
            HiloTrabajo hilo = new HiloTrabajo(recurso, i);
            hilo.start();
        }
    }
}
