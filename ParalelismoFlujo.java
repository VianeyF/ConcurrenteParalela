import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ParalelismoFlujo{
    public static void main (String [] args){
        List<Intenger>numeros=new ArrayList<>();
        for (int i=1; i<=1_000_000; i++){
            numeros.add(i);
        }
        long inicio = System.currentTimeMillis();

        long sumaPrimos = numeros.parallelStreeam()
                                  .filter(ParalelismoFlujo::esPrimo)
                                  .mapToLong(Interger::longValue)
                                  .sum();

        long fin = System.currentTimeMillis();

        System.out.println("Suma de numeros primos:"+sumaPrimos);
        System.out.println("Tiempo de ejecucion paralela" +(fila-inicio)+"ms");
    }
    private static boolean esPrimo(int numero){
        if (numero<=1) return false;
        for(int i=2; i<=Math.sqrt(numero); i++){
            if (numero%i ==0) return false;
        }
        return true;
    }
}
