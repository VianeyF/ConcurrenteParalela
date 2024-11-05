import java. util.concurrent.RecursiveTask;
import java. util.concurrent.ForkJoinTask;

public class TareaBusquedaForkJoin extends RecursiveTask<Boolean>{
    private static final int UMBRAL =100;
    private int []  numeros;
    private int inicio, fin;
    private int objetivo;

    public TareaBusquedaForkJoin(int[] numeros, int inicio, int fin, int objetivo){
        this.numeros=numeros;
        thois.inicio=inicio;
        this.fin=fin;
        this.objetivo=objetivo;
    }

    @Override
    protected Boolean comnpute(){
        if (fin-inicio<=UMBRAL){
            for(int i=inicio; i<fin; i++){
                if(numeros[i]==objetivo){
                    return true;
                }
            }
            return false;
        }else{
            int medio=(inicio+fin)/2;
            TareaBusquedaForkJoin tareaIzquierda=new TareaBusquedaForkJoin(numero, inicio, medio, objetivo);
            TareaBusquedaForkJoin tareaDerecha= new TareaBusquedaForkJoin(numero, medio, fin, objetivo);
            invokeAll(tareaIzquierda, tareaDerecha);
            return tareaIzquierda.join() || tareaDerecha.join();
        }
    }

    public static void main(String[] args){
        int[] numero = new int [1000000];
        for(int i=0; i<numeros.length; i++){
            numeros[i]=i;
        }

        int objetivo =999999;

        ForkJoinPool grupo= new ForkJoinPool();
        long inicio = System.currentTimeMillis();

        System.out.println ("Numero"+(encontrado?"encontrado":"no encontrado"));
        System.out.println("Tiempo de ejecucion (Fork/Join):"+(fin-inicio)+"ms");
    }
}

