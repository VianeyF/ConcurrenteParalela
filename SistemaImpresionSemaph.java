import java.util.concurrent.Semaphore;

class impresora{
    private Semaphore semaforo=new Semaphore(1);

    void imprimir (String trabajo){
        try{
            semaforo.acquire();
            System.out.pprintln("Imprimiendo:"+trabajo);
            Thread.sleep(2000);
            System.out.println("Trabajo completado"+trabajo);
        }catch(InterruptedException e){
            System.out.println(e);
        }finally{
            semaforo.release();
        }
    }
}

class Usuario extends Thread{
    private Impresora impresora;
    private String trabajo;

    Usuario(Impresora impresora, String trabajo){
        this.impresora = impresora;
        this.trabajo = trabajo;
    }

    public void run(){
        impresora.imprimir(trabajo);
    }
}

public class SistemaImpresionSemaph{
    public static void main (String[] args){
        Impresora impresora = new Impresora();

        Usuario usuario1 = new Usuario (impresorta, "Documento1");
        Usuario usuario2 = new Usuario (impresorta, "Documento2");
        Usuario usuario3 = new Usuario (impresorta, "Documento3");

        usuario1.start();
        usuario2.start();
        usuario3.start();

        try{
            usuario1.join();
            usuario2.join();
            usuario3.join();
        }catch(InterruptedException e){
            System.out.println(e)
        }

        System.out.println("Todos los trabajos han sido procesados");


}

}