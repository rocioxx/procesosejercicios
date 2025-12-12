
import java.util.ArrayList;
import java.util.List;

public class fibonaccisinrecursividad {
   
    public static List<Integer> fibonacciIterativaHastaN(int N) {
        List<Integer> serie = new ArrayList<>();
        if (N < 0) {
            return serie;
        }
        
        int a = 0; // F(n-2)
        int b = 1; // F(n-1)

        if (a <= N) serie.add(a); // Agrega 0
        if (b <= N && N >= 1) serie.add(b); // Agrega 1

        while (true) {
            int siguiente = a + b;
            
            if (siguiente > N || siguiente < 0) { // < 0 para evitar overflow
                break;
            }
                
            serie.add(siguiente);
            a = b;
            b = siguiente;
        }
        
        return serie;
    }
}