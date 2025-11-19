import java.util.ArrayList;
import java.util.List;

public class fibonacci {

    public static List<Integer> calcularFibonacciIterativo(int maximo) {
        List<Integer> serie = new ArrayList<>();
        
        if (maximo <= 1) {
            return serie;
        }

        int a = 0;
        int b = 1;
        
        serie.add(a); 

        while (b < maximo) {
            serie.add(b);
            
            int siguiente = a + b;
            
            a = b;
            b = siguiente;
        }
        
        return serie;
    }
    
    public static void main(String[] args) {
        int limite = 100;
        List<Integer> fibonacciIterativo = calcularFibonacciIterativo(limite);
        System.out.println("Fibonacci Iterativo (menores de " + limite + "):");
        System.out.println(fibonacciIterativo);
    }
}