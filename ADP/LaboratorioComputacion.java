import java.util.concurrent.Semaphore;

class Estudiante extends Thread{
    private Semaphore semaforo;
    private int id;

    public Estudiante (Semaphore semaforo, int id){
        this.semaforo=semaforo;
        this.id=id;
    }
    @Override
    public void run(){
        try{
            System.out.println("Estudiante"+id+"esta esperado por una computrdora");
            semaforo.acquire();
            System.out.println("Estudianyte"+id+"esta usando una computadora");
            Thread.sleep(2000);
            System.out.println("Estudiante"+id+"ha terminado y libera la comutadora");
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            semaforo.release();
        }
    }
}

public class LaboratorioComputacion{
    public static void main (String[] args){
        final int NUM_COMPUTADORAS = 5;
        final int NUM_ESTUDIANTES =10;

        Semaphore semaforo = new Semaphore(NUM_COMPUTADORAS);
        for(int i=1; i<=NUM_ESTUDIANTES; i++){
            new Estudiante(semaforo,i).start();
        }
    }
}