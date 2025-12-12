public class MultiplicacionesRecursivas {
    /**
     * Calcula la multiplicación de 'a' por 'b' solo usando sumas de forma recursiva.
     */
    public static int multiplicarRecursiva(int a, int b) {
        // Manejo de números negativos: La recursión debe contar sobre el factor positivo
        if (b < 0) {
            return -multiplicarRecursiva(a, -b);
        }
        
        // Caso Base: Si b es 0, la multiplicación es 0
        if (b == 0) {
            return 0;
        }
        
        // Paso Recursivo: a + (a * (b-1))
        return a + multiplicarRecursiva(a, b - 1);
    }
}