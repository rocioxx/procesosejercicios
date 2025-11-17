import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class groups {

    public static void main(String[] args) {
        
        System.out.printf("\n--- 1. Extracción de texto entre comillas ---");
        String texto1 = "El hombre dijo 'El perro no sabe leer' y el perro le contestó 'Si me hubieras preguntado sabrías la verdad'. 'Cuál es la verdad' preguntó el hombre. 'La verdad es ...' y el perro sonó como 'aaaaagh'.";
        extraerTextoEntreComillas(texto1);
        System.out.printf("\n-------------------------------------------------");

       
        System.out.printf("\n--- 2. Extracción de números en Libras (Lookaround) ---");
        String texto2 = "250 Euros es el dinero que me dio cuando yo le presté 200$ ¿o fueron 220 Libras?, ya no me acuerdo. Debo de estar perdiendo la memoria ya sé eran 120 Libras. Cuando dijiste 100 Libraste una batalla. Entonces sería que quería darte 100 Librasilios, qué es 1 Librasilio, 10 Librasilios son 10 veces 1 Librasillo.";
        extraerNumerosConLibras(texto2);
        System.out.printf("\n-------------------------------------------------");
    }

    /**
     * Muestra solo el texto que se encuentra entre comillas simples o dobles.
     * Utiliza un grupo de captura para el contenido.
     * Patrón: ['"](.*?)['"]
     * - ['"] : Coincide con comilla simple o doble (la de apertura).
     * - (.*?) : **Grupo de captura 1**, coincide con cualquier carácter (el .), cero o más veces (*), de forma no codiciosa (el ?).
     * - ['"] : Coincide con comilla simple o doble (la de cierre).
     * @param texto La cadena de texto a analizar.
     */
    public static void extraerTextoEntreComillas(String texto) {
       
        String regex = "['\"](.*?)['\"]"; 
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(texto);

        System.out.printf("\nTexto analizado: " + texto);
        System.out.printf("\nTextos extraídos (usando groups):");
        
      
        while (matcher.find()) {
          
            System.out.println("-> " + matcher.group(1)); 
        }
    }

    /**
     * Muestra solo los números que son seguidos por la palabra "Libras" o "Libras." 
     * o "Libras?" etc. sin incluir el texto "Libras".
     * Utiliza Lookahead positivo: (?=...).
     * Patrón: \b\d+(?=\s*Libras\b)
     * - \b : Límite de palabra (asegura que el número no sea parte de una palabra más grande).
     * - \d+ : Coincide con uno o más dígitos (el número en sí).
     * - (?=\s*Libras\b) : **Lookahead positivo**. Coincide solo si lo que sigue (sin incluirlo en el resultado final)
     * es cero o más espacios en blanco (\s*) seguido de la palabra "Libras" (\b asegura que es la palabra completa).
     * @param texto La cadena de texto a analizar.
     */
    public static void extraerNumerosConLibras(String texto) {
       
        String regex = "\\b\\d+\\b(?=\\s*Libras\\b)"; 
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(texto);

        System.out.println("Texto analizado: " + texto);
        System.out.println("\nNúmeros extraídos (usando Lookahead, solo el número):");

       
        while (matcher.find()) {
            
            System.out.println("-> " + matcher.group(0)); 
        }
        
        System.out.println("\nNota: El Lookahead `(?=\\s*Libras\\b)` garantiza que solo se capturen los números seguidos de la palabra 'Libras', excluyendo 'Librasilios' y 'Euros'.");
    }
}
