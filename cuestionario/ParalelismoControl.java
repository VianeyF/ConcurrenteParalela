/*El paralelismo de control permite ejecutar múltiples tareas independientes simultáneamente.
 Un ejemplo común es el uso de hilos para realizar tareas diferentes. Aquí hay un código que
  crea varios hilos que imprimen mensajes diferentes*/

class Task extends Thread {
    private String message;

    public Task(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(message + " - Count: " + i);
            try {
                Thread.sleep(100); // Simula trabajo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ParalelismoControl{
    public static void main(String[] args)  {
        Thread task1 = new Task("Task 1");
        Thread task2 = new Task("Task 2");
        Thread task3 = new Task("Task 3");

        task1.start();
        task2.start();
        task3.start();
    }
}
