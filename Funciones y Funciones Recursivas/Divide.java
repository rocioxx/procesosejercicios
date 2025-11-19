public class Divide {

    public static int divide(int dividendo, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("El divisor no puede ser cero.");
        }

        if (dividendo == 0) {
            return 0;
        }

        boolean resultadoNegativo = (dividendo < 0) ^ (divisor < 0);

        int absDividendo = Math.abs(dividendo);
        int absDivisor = Math.abs(divisor);

        int cociente = 0;
        int resto = absDividendo;

        while (resto >= absDivisor) {
            resto = resto - absDivisor;
            cociente = cociente + 1;
        }

        if (resultadoNegativo) {
            return -cociente;
        } else {
            return cociente;
        }
    }
    
   
    public static void main(String[] args) {
        System.out.println("20 / 4 = " + divide(20, 4));
        System.out.println("10 / 3 = " + divide(10, 3));
        System.out.println("-15 / 5 = " + divide(-15, 5));
        System.out.println("17 / -6 = " + divide(17, -6));
    }
}