public class Factorialrecursivo {

    private static int factorialRecursiva(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("El factorial no está definido para números negativos.");
        }
        // Caso Base
        if (n == 0) {
            return 1;
        }
        
        // Paso Recursivo: n * (n-1)!
        // Usamos la función de multiplicación del Ejercicio 1 (Recursiva)
        return MultiplicacionesRecursivas.multiplicarRecursiva(n, factorialRecursiva(n - 1));
    }

    /**
     * Calcula (a! * b!) usando solo la multiplicación recursiva.
     */
    public static int multiplicarFactorialesRecursiva(int a, int b) {
        int factA = factorialRecursiva(a);
        int factB = factorialRecursiva(b);
        
        // Usamos la función de multiplicación del Ejercicio 1 (Recursiva)
        return MultiplicacionesRecursivas.multiplicarRecursiva(factA, factB);
    }
}