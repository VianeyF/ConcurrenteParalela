public class DeckerAlgoritmo3 {
    private static volatile boolean[] wantsToEnter = {false, false, false}; // Ampliar a 3 procesos
    private static volatile int turn = 0;

    public static void main(String[] args) {
        Thread process0 = new Thread(new Process(0));
        Thread process1 = new Thread(new Process(1));
        Thread process2 = new Thread(new Process(2)); // Añadir tercer proceso
        process0.start();
        process1.start();
        process2.start();
    }

    static class Process implements Runnable {
        private int id;

        public Process(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                wantsToEnter[id] = true;

                // Esperar hasta que los demás procesos no estén en la sección crítica
                for (int j = 0; j < wantsToEnter.length; j++) {
                    if (j != id) {
                        while (wantsToEnter[j]) {
                            if (turn != id) {
                                wantsToEnter[id] = false;
                                while (turn != id) {
                                    // Espera activa
                                }
                                wantsToEnter[id] = true;
                            }
                        }
                    }
                }

                // Sección crítica
                System.out.println("Proceso " + id + " está en la sección crítica");

                try {
                    Thread.sleep(100); // Simular algún trabajo
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                // Salir de la sección crítica
                turn = (turn + 1) % 3; // Cambiar turno para el siguiente proceso (3 procesos)
                wantsToEnter[id] = false;
            }
        }
    }
}
