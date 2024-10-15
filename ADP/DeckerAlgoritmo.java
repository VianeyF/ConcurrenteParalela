public class DeckerAlgoritmo {
    private static volatile boolean[] wantsToEnter = {false, false}; // Corregir "booleaan" a "boolean"
    private static volatile int turn = 0;

    public static void main(String[] args) {
        Thread process0 = new Thread(new Process(0));
        Thread process1 = new Thread(new Process(1));
        process0.start();
        process1.start();
    }

    static class Process implements Runnable {
        private int id;

        public Process(int id) {
            this.id = id; // Corregir la asignación
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                wantsToEnter[id] = true;

                while (wantsToEnter[1 - id]) {
                    if (turn != id) {
                        wantsToEnter[id] = false;
                        while (turn != id) {
                            // Espera activa
                        }
                        wantsToEnter[id] = true;
                    }
                }

                // Sección crítica
                System.out.println("Proceso " + id + " está en la sección crítica");

                try {
                    Thread.sleep(100); // Simular algún trabajo
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Corregir el espacio
                }

                // Salir de la sección crítica
                turn = 1 - id;
                wantsToEnter[id] = false;
            }
        }
    }
}
