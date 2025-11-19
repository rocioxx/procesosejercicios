public class Factorial {

    public static int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("El factorial solo está definido para números no negativos.");
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        return multiplicar(n, factorial(n - 1));
    }

    public static int multiplicar(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }

        boolean resultadoNegativo = (a < 0) ^ (b < 0);

        int absA = Math.abs(a);
        int absB = Math.abs(b);

        int resultadoAbsoluto = multiplicarRecursivo(absA, absB);

        if (resultadoNegativo) {
            return -resultadoAbsoluto;
        } else {
            return resultadoAbsoluto;
        }
    }

    private static int multiplicarRecursivo(int a, int b) {
        if (b == 1) {
            return a;
        }
        return a + multiplicarRecursivo(a, b - 1);
    }
    
    public static int multiplicarFactoriales(int n1, int n2) {
        int factN1 = factorial(n1);
        int factN2 = factorial(n2);
        
        return multiplicar(factN1, factN2);
    }

    public static void main(String[] args) {
        int n1 = 3;
        int n2 = 4;
        
        int resultado = multiplicarFactoriales(n1, n2);
        
        System.out.println("Factorial de " + n1 + ": " + factorial(n1));
        System.out.println("Factorial de " + n2 + ": " + factorial(n2));
        System.out.println("---");
        System.out.println("(" + n1 + "!) * (" + n2 + "!) = " + resultado);
        
        System.out.println("\n(0!) * (5!) = " + multiplicarFactoriales(0, 5));
    }
}