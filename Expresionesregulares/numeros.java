import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class numeros {

    public static void main(String[] args) {
        String texto = "250 Euros es el dinero que me dio cuando yo le presté 200$ ¿o fueron 220 Libras?, ya no me acuerdo. Debo de estar perdiendo la memoria ya sé eran 120 Libras. Cuando dijiste 100 Libraste una batalla. Entonces sería que quería darte 100 Librasilios, qué es 1 Librasilio, 10 Librasilios son 10 veces 1 Librasillo.";

        
        String regexEuros = "(\\d+)(?=\\sEuros)";
        
        System.out.println("Montos en Euros encontrados:");
        encontrarYMostrar(texto, regexEuros);

        
        String regexDolares = "(?<=\\$)\\d+";

        System.out.println("\nMontos en Dólares encontrados:");
        encontrarYMostrar(texto, regexDolares);
    }
    
    private static void encontrarYMostrar(String texto, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(texto);

        
        int grupoCaptura = regex.contains("(?<=\\$") ? 0 : 1; 

        boolean encontrado = false;
        while (matcher.find()) {
            System.out.println(matcher.group(grupoCaptura));
            encontrado = true;
        }
        
        if (!encontrado) {
            System.out.println("Ninguno.");
        }
    }
}