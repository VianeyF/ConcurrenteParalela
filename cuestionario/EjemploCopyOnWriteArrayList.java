import java.util.concurrent.CopyOnWriteArrayList;

public class EjemploCopyOnWriteArrayList {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> lista = new CopyOnWriteArrayList<>();
        
        lista.add("Elemento 1");
        lista.add("Elemento 2");

        // Lecturas seguras en concurrencia
        for (String elemento : lista) {
            System.out.println(elemento);
        }
    }
}
