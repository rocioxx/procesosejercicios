public class Multiplicaciones {

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
    
    public static void main(String[] args) {
        System.out.println("5 * 4 = " + multiplicar(5, 4));
        System.out.println("6 * 0 = " + multiplicar(6, 0));
        System.out.println("-7 * 3 = " + multiplicar(-7, 3));
        System.out.println("2 * -8 = " + multiplicar(2, -8));
        System.out.println("-9 * -2 = " + multiplicar(-9, -2));
    }
}