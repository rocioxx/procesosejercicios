import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReemplazoMadera {

    public static void main(String[] args) {
        String textoOriginal = "El lobo sopló y sopló pero la casa de madera no se cayó. Entonces pensó, ¿y si en vez de usar mis pulmones uso un palo de madera?. Fue entonces cuando buscó por todo el bosque pero sólo encontraba maderas gordas que no podía transportar. A lo mejor haciendo una carretilla de madera podría transportar algo más grande y usarlo para aporrear con fuerza la casita de madera…";

       
        String regex = "\\b(madera|maderas)\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(textoOriginal);

        
        StringBuilder sb = new StringBuilder();
        int contador = 0;
        int ultimoIndice = 0;

     
        while (matcher.find()) {
            contador++; 

            
            sb.append(textoOriginal, ultimoIndice, matcher.start());

          
            if (contador == 2 || contador == 4) {
                sb.append("metal");
            } else {
                sb.append(matcher.group()); 
            }

           
            ultimoIndice = matcher.end();
        }

        
        sb.append(textoOriginal.substring(ultimoIndice));

        String textoModificado = sb.toString();

       
       
        System.out.println(textoOriginal);
        System.out.println("TEXTO MODIFICADO");
    
        System.out.println(textoModificado);
    }
}