public class Multiplicaciones {
    /**
     * Calcula la multiplicación de 'a' por 'b' solo usando sumas de forma iterativa.
     */
    public static int multiplicarIterativa(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }

        // 1. Determinar el signo del resultado
        int signo = ((a < 0) ^ (b < 0)) ? -1 : 1;
        
        // 2. Trabajar con valores absolutos
        int aAbs = Math.abs(a);
        int bAbs = Math.abs(b);

        int resultado = 0;
        
        // Sumar 'aAbs' 'bAbs' veces
        for (int i = 0; i < bAbs; i++) {
            resultado += aAbs;
        }
        
        return resultado * signo;
    }
}