class SeccionCritica {
    private boolean[] deseo = {false, false}; // Bandera de deseo para cada hilo
    private int turno = 0; // Indica a quién le toca entrar en la sección crítica
    // Método para que el hilo 1 intente entrar en su sección crítica
    public synchronized void ejecutarSeccionCritica1() {
    deseo[0] = true; // Hilo 1 quiere entrar
    turno = 1; // Cede el turno al hilo 2
    // Mientras el hilo 2 también quiere entrar y es su turno, el hilo 1 retrocede
    while (deseo[1] && turno == 1) {
    // Retroceso: Hilo 1 espera hasta que el hilo 2 termine
    }
    // Sección crítica del hilo 1
    System.out.println("Hilo 1 está ejecutando su sección crítica.");
    try {
    Thread.sleep(50); // Simular tiempo en la sección crítica
    } catch (InterruptedException e) {
    Thread.currentThread().interrupt();
    }
    // Hilo 1 ya no desea entrar a la sección crítica
    deseo[0] = false;
    }
    // Método para que el hilo 2 intente entrar en su sección crítica
    public synchronized void ejecutarSeccionCritica2() {
    deseo[1] = true; // Hilo 2 quiere entrar
    turno = 0; // Cede el turno al hilo 1
    // Mientras el hilo 1 también quiere entrar y es su turno, el hilo 2 retrocede
    while (deseo[0] && turno == 0) {
    // Retroceso: Hilo 2 espera hasta que el hilo 1 termine
    }
    // Sección crítica del hilo 2
    System.out.println("Hilo 2 está ejecutando su sección crítica.");
    try {
    Thread.sleep(50); // Simular tiempo en la sección crítica
    } catch (InterruptedException e) {
    Thread.currentThread().interrupt();
    }
    // Hilo 2 ya no desea entrar a la sección crítica
    deseo[1] = false;
    }
    }
    class Hilo1 extends Thread {
    private final SeccionCritica seccionCritica;
    public Hilo1(SeccionCritica seccionCritica) {
    this.seccionCritica = seccionCritica;
    }
    @Override
    public void run() {
    while (true) { // Bucle infinito
    seccionCritica.ejecutarSeccionCritica1();
    }
    }
    }
    class Hilo2 extends Thread {
    private final SeccionCritica seccionCritica;
    public Hilo2(SeccionCritica seccionCritica) {
    this.seccionCritica = seccionCritica;
    }
    @Override
    public void run() {
    while (true) { // Bucle infinito
    seccionCritica.ejecutarSeccionCritica2();
    }
    }
    }
    public class AlternanciaHilosPeterson {
    public static void main(String[] args) {
    SeccionCritica seccionCritica = new SeccionCritica();
    // Crear los dos hilos
    Hilo1 hilo1 = new Hilo1(seccionCritica);
    Hilo2 hilo2 = new Hilo2(seccionCritica);
    // Iniciar los hilos
    hilo1.start();
    hilo2.start();
    }
    }