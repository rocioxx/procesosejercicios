import java.util.ArrayList;
import java.util.List;

public class fibonacci {
    
    // Función auxiliar que devuelve el n-ésimo valor de Fibonacci
    private static int fibValorRecursivo(int n) {
        if (n <= 1) {
            return n;
        }
        return fibValorRecursivo(n - 1) + fibValorRecursivo(n - 2);
    }

    /**
     * Crea una lista de números de Fibonacci menores o iguales a N de forma recursiva.
     */
    public static List<Integer> fibonacciRecursivaHastaN(int N) {
        List<Integer> serie = new ArrayList<>();
        if (N < 0) {
            return serie;
        }

        int i = 0;
        while (true) {
            int valorFib = fibValorRecursivo(i);
            
            if (valorFib > N || valorFib < 0) { // < 0 para evitar overflow si N es muy grande
                break;
            }
            serie.add(valorFib);
            i++;
        }
        return serie;
    }
}