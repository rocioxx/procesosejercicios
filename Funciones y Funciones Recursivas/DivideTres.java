public class DivideTres {
    /**
     * Divide tres números enteros ((a / b) / c) usando la división iterativa anterior.
     */
    public static int divideTresIterativa(int a, int b, int c) {
        // a / b
        int temp = Divide.divideEnteraIterativa(a, b); 
        
        // (a / b) / c
        return Divide.divideEnteraIterativa(temp, c);
    }
}