public class AlgoritmoLamport{
    static int Tam=4;
    static int[] numero = {0,0,0,0};
    static boolean[] eligiendo = {false, false, false, false};
    static int Max;

    public static void Mostrar (int 0){
        System.out.println("\nSoy la seccion critica" + "o" + "\n");
    }

    public static int max (int[] vector){
        Max=0;
        for(int k=0; k<Tam; k++){
            while(vector[k]>Max){
                Max=vector[k];
            }
        }
        return Max;
    }

    public static void hilo(int i, int iteracion){
        int count =0;
        while (count < iteraciones){
            eligiendo[i]=true;
            numero[i]=1+max(numero);
            eligiendo[i]=false;
            for(int j=0; j<Tam; j++){
                while(eligiendo[j]){
                    while((numero[j]!=0)&&(numero[j]<numero[i])){

                    }
                    Mostrar(i);
                    numero[i]=0;
                    count++;
                }
            }
            
            public static void main (String[] args){
                System.out.println("Algoritmo de Lamport");
            }








        }
    }




  

}