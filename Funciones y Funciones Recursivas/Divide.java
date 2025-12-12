public class Divide {
    /**
     * Divide dos números enteros (a / b) usando solo restas (Iterativa).
     */
    public static int divideEnteraIterativa(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("División por cero.");
        }
        if (a == 0) {
            return 0;
        }

        // 1. Determinar el signo
        int signo = ((a < 0) ^ (b < 0)) ? -1 : 1;
        
        // 2. Trabajar con valores absolutos
        int dividendo = Math.abs(a);
        int divisor = Math.abs(b);
        
        int cociente = 0;
        
        // Resta repetida
        while (dividendo >= divisor) {
            dividendo = dividendo - divisor;
            cociente = cociente + 1; // Suma permitida para contar
        }
        
        return cociente * signo;
    }
}