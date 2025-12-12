public class Factorial {

    private static int factorialIterativo(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("El factorial no está definido para números negativos.");
        }
        if (n == 0) {
            return 1;
        }
        
        int resultado = 1;
        for (int i = 1; i <= n; i++) {
            // Usamos la función de multiplicación del Ejercicio 1 (Iterativa)
            resultado = Multiplicaciones.multiplicarIterativa(resultado, i); 
        }
        return resultado;
    }
    
    /**
     * Calcula (a! * b!) usando solo la multiplicación iterativa.
     */
    public static int multiplicarFactorialesIterativa(int a, int b) {
        int factA = factorialIterativo(a);
        int factB = factorialIterativo(b);
        
        // Usamos la función de multiplicación del Ejercicio 1 (Iterativa)
        return Multiplicaciones.multiplicarIterativa(factA, factB);
    }
}