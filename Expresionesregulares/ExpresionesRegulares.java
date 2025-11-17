import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpresionesRegulares {
    public static void main(String[] args) {
        
        String texto = """
        {Un hombre} va al {médico} porque tiene un problema muy grave: cada vez que estornuda, {se convierte en un animal diferente}. 
        {El médico} le dice que es un caso muy raro y que necesita hacerle unas pruebas. {Le} pone unos electrodos en la cabeza y le conecta a una máquina que mide su actividad cerebral.
        
        Bien, ahora voy a provocarle un estornudo con este spray nasal y veremos qué pasa - dice {el médico}.
        Vale, pero tenga cuidado, no sé en qué me voy a convertir - dice {el hombre}.
        
        {El médico} le aplica el spray nasal {al hombre} y este empieza a estornudar. De repente, se transforma en un león y empieza a rugir y a intentar escapar de la jaula.
        
        ¡Increíble! - exclama {el médico} - Se ha convertido en un león. Esto es fascinante. Voy a anotar los resultados.
        {El médico} le aplica otro spray nasal {al hombre} y se estornuda. Esta vez, se transforma en un elefante y empieza a trompetear y a mover la trompa.
        
        Increíble - repite {el médico} - Ahora es un elefante. Esto es asombroso. Voy a anotar los resultados.
        {El médico} le aplica otro spray nasal {al hombre} y este estornuda de nuevo. Ahora, se transforma en un pingüino y empieza a graznar y a deslizarse por el suelo.
        
        Increíble - dice {el médico} por tercera vez - Ahora es un pingüino. Esto es extraordinario. Voy a anotar los resultados.
        {El médico} le aplica otro spray nasal {al hombre} y esta vez no se transforma en ningún animal, sino que se queda como está.
        
        Increíble - dice {el médico} por última vez - Ahora ha vuelto a la normalidad. Esto es milagroso. Voy a anotar los resultados.
        {El médico} le quita los electrodos al {hombre} y le dice:
        
        Bueno, señor, he terminado las pruebas. Y aquí está su problema.
        Usted tiene una alergia muy severa al spray nasal.
        ¿Y eso es grave? - pregunta el {hombre}, esperanzado.
        Tiene usted una alergia muy severa al spray nasal.
        """;

       
        String miNombre = "Rocío";

       
        Pattern hombre = Pattern.compile("(?i)\\{[^}]*\\bhombre\\b[^}]*\\}");
        Matcher mhombre = hombre.matcher(texto);
        texto = mhombre.replaceAll(miNombre);

       
        Pattern medico = Pattern.compile("(?i)\\{[^}]*\\bm[é]dico\\b[^}]*\\}");
        Matcher mmedico = medico.matcher(texto);
        texto = mmedico.replaceAll(miNombre);

      
        System.out.printf(texto);
    }
}
