import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculadora {

    // --- Lógica del Algoritmo Shunting-Yard ---

    private static int obtenerPrioridad(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0; 
    }

    private static void aplicarOperador(Stack<Double> valores, Stack<Character> operadores) {
        if (operadores.isEmpty() || valores.size() < 2) {
            throw new IllegalArgumentException("Expresión mal formada.");
        }
        
        char op = operadores.pop();
        double b = valores.pop();
        double a = valores.pop();
        
        switch (op) {
            case '+': valores.push(a + b); break;
            case '-': valores.push(a - b); break;
            case '*': valores.push(a * b); break;
            case '/': 
                if (b == 0) throw new ArithmeticException("División por cero: No se puede dividir por cero.");
                valores.push(a / b);
                break;
        }
    }

    /**
     * Evalúa una expresión matemática con prioridad y paréntesis.
     */
    public static double evaluarExpresion(String expresion) {
        Stack<Double> valores = new Stack<>();
        Stack<Character> operadores = new Stack<>();

        // 1. Preprocesamiento y Tokenización
        String limpia = expresion.replaceAll("\\s+", ""); 
        Pattern pattern = Pattern.compile("(\\d+\\.?\\d*|[+\\-*/()])");
        Matcher matcher = pattern.matcher(limpia);

        while (matcher.find()) {
            String token = matcher.group();
            char primerCaracter = token.charAt(0);

            if (Character.isDigit(primerCaracter)) {
                // Es un número: va directo a la pila de valores
                valores.push(Double.parseDouble(token));
            } else if (primerCaracter == '(') {
                // Paréntesis de apertura: va directo a la pila de operadores
                operadores.push('(');
            } else if (primerCaracter == ')') {
                // Paréntesis de cierre: aplicar operadores hasta encontrar el '('
                while (operadores.peek() != '(') {
                    aplicarOperador(valores, operadores);
                }
                operadores.pop(); // Eliminar '(' de la pila
            } else {
                // Operador: aplicar operadores anteriores de mayor o igual prioridad
                while (!operadores.isEmpty() && operadores.peek() != '(' && 
                       obtenerPrioridad(operadores.peek()) >= obtenerPrioridad(primerCaracter)) {
                    aplicarOperador(valores, operadores);
                }
                operadores.push(primerCaracter);
            }
        }

        // 2. Aplicar los operadores restantes
        while (!operadores.isEmpty()) {
            aplicarOperador(valores, operadores);
        }
        
        // 3. El resultado final está en la cima de la pila de valores
        if (valores.size() != 1) {
             throw new IllegalArgumentException("Expresión mal balanceada o incompleta.");
        }

        return valores.pop();
    }
    
    // --- Punto de Entrada del Programa (Main) ---

    public static void main(String[] args) {
        System.out.println("--- Calculadora JAR con Prioridad y Paréntesis ---");

        if (args.length == 0) {
            System.out.println("Uso:");
            System.out.println("java -jar NombreDeTuArchivo.jar \"<expresión_matemática>\"");
            System.out.println("\nEjemplo: java -jar Calculadora.jar \"1 + 2 * 3\"");
            return;
        }

        // Juntamos todos los argumentos para formar la expresión completa
        String expresion = String.join(" ", args);
        
        try {
            double resultado = evaluarExpresion(expresion);
            
            System.out.println("\nExpresión evaluada:");
            System.out.println(expresion);
            System.out.println("--------------------");
            System.out.println("Resultado: " + resultado); 
            
        } catch (ArithmeticException e) {
            System.err.println("ERROR Matemático: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("ERROR de Sintaxis: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("ERROR inesperado al procesar la expresión.");
        }
    }
}