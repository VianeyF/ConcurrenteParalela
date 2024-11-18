import java.util.concurrent.ConcurrentLinkedQueue;

public class EjemploConcurrentLinkedQueue {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> cola = new ConcurrentLinkedQueue<>();

        cola.add("Tarea 1");
        cola.add("Tarea 2");

        // Operaciones concurrentes
        System.out.println(cola.poll()); // Remueve y devuelve la cabeza
        System.out.println(cola.peek()); // Consulta la cabeza sin removerla
    }
}
