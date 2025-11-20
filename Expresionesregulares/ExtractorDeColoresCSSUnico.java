import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class ExtractorDeColoresCSSUnico extends JFrame {

    // 1. ELIMINAMOS JTextArea y lo reemplazamos por un JPanel dinámico
    private JPanel panelContenedorColores; 
    private JLabel labelEstado; // Para mostrar el nombre del archivo

    public ExtractorDeColoresCSSUnico() {
        super("Extractor de Colores CSS (Bloques)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // 1. Botón para abrir el JFileChooser
        JButton botonAbrir = new JButton("Buscar Archivo CSS y Extraer Colores");
        botonAbrir.addActionListener(e -> abrirFileChooser());

        // 2. Etiqueta para el estado/nombre del archivo
        labelEstado = new JLabel("Presione el botón para seleccionar un archivo .css", SwingConstants.CENTER);
        labelEstado.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        // 3. Panel Contenedor de Colores: Usamos FlowLayout o GridLayout para los bloques.
        // FlowLayout es más simple para que los bloques se acomoden solos.
        panelContenedorColores = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelContenedorColores.setBackground(Color.WHITE); 
        
        // Contenedor principal para la parte superior
        JPanel panelNorte = new JPanel(new BorderLayout());
        panelNorte.add(botonAbrir, BorderLayout.NORTH);
        panelNorte.add(labelEstado, BorderLayout.SOUTH);

        add(panelNorte, BorderLayout.NORTH);
        // Colocamos el panel de colores dentro de un JScrollPane
        add(new JScrollPane(panelContenedorColores), BorderLayout.CENTER);

        setSize(700, 450); // Aumentamos el tamaño para la visualización de bloques
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // --- Métodos de la Interfaz y Lógica de Archivos (Sin cambios mayores) ---

    private void abrirFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos CSS (*.css)", "css");
        fileChooser.setFileFilter(filter);

        int resultado = fileChooser.showOpenDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            List<String> colores = extraerColores(archivoSeleccionado.getAbsolutePath());
            mostrarResultados(archivoSeleccionado.getName(), colores); // Llamada al método modificado
        } else {
            labelEstado.setText("Búsqueda cancelada por el usuario.");
            panelContenedorColores.removeAll(); // Limpia los bloques anteriores
            panelContenedorColores.revalidate();
            panelContenedorColores.repaint();
        }
    }

    private List<String> extraerColores(String rutaArchivo) {
        List<String> colores = new ArrayList<>();
        // El regex sigue funcionando bien para HEX y rgb/rgba
        String colorRegex = "(#[0-9a-fA-F]{3,8})|(\\brgba?\\s*\\([^)]+\\))"; 
        Pattern pattern = Pattern.compile(colorRegex);

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Convertimos a minúsculas para igualar los rgb/rgba
                Matcher matcher = pattern.matcher(linea.toLowerCase()); 
                while (matcher.find()) {
                    String colorEncontrado = matcher.group(0).trim();
                    
                    // Se agrega el color solo si es único
                    if (!colores.contains(colorEncontrado)) {
                        colores.add(colorEncontrado);
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                "Error al leer el archivo: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
        return colores;
    }

    // --- EL MÉTODO CLAVE: Reemplaza texto por bloques de color ---

    private void mostrarResultados(String nombreArchivo, List<String> colores) {
        // Limpia el panel antes de agregar los nuevos bloques
        panelContenedorColores.removeAll(); 
        
        labelEstado.setText("Colores únicos encontrados en " + nombreArchivo + ": " + colores.size());

        if (colores.isEmpty()) {
            panelContenedorColores.add(new JLabel("No se encontraron códigos de color válidos en el archivo."));
        } else {
            for (String color : colores) {
                // 4. Se crea un nuevo panel personalizado (el bloque de color) por cada color.
                panelContenedorColores.add(new ColorBlockPanel(color));
            }
        }
        
        // Vuelve a calcular el diseño de los componentes dentro del panel
        panelContenedorColores.revalidate(); 
        // Fuerza a que se redibuje (necesario cuando se añaden o quitan componentes dinámicamente)
        panelContenedorColores.repaint(); 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ExtractorDeColoresCSSUnico::new);
    }
}

// ---------------------------------------------------------------------
// --- CLASE NUEVA: EL BLOQUE DE COLOR ---
// ---------------------------------------------------------------------

/**
 * JPanel personalizado que se dibuja con un color específico 
 * y muestra su código en una etiqueta.
 */
class ColorBlockPanel extends JPanel {
    
    private final String colorCode;
    private static final int WIDTH = 120;
    private static final int HEIGHT = 120;

   public ColorBlockPanel(String colorCode) {
    this.colorCode = colorCode;
    
    // Convertimos el código CSS a un objeto Color de Java
    Color color = parseColor(colorCode);

    // --- 1. Configuración del panel principal ---
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setBorder(BorderFactory.createLineBorder(Color.GRAY)); 

    // --- 2. Panel Superior: Muestra el Color ---
    JPanel colorDisplay = new JPanel();
    colorDisplay.setBackground(color);
    colorDisplay.setPreferredSize(new Dimension(WIDTH, HEIGHT - 20)); // Área grande para el color
    
    // --- 3. Panel Inferior: Muestra el Código de Color (¡Asegurando Contraste!) ---
    JPanel codePanel = new JPanel(new BorderLayout());
    codePanel.setBackground(new Color(230, 230, 230)); // Fondo gris claro fijo (como en tu ejemplo)
    
    JLabel codeLabel = new JLabel(colorCode, SwingConstants.CENTER);
    codeLabel.setFont(new Font("Monospaced", Font.BOLD, 10)); // Usamos BOLD para mejor visibilidad
    codeLabel.setForeground(Color.BLACK); // El texto SIEMPRE será negro

    codePanel.add(codeLabel, BorderLayout.CENTER);

    // --- 4. Ensamblar el ColorBlockPanel ---
    add(colorDisplay, BorderLayout.CENTER); 
    add(codePanel, BorderLayout.SOUTH);
}
    /**
     * Convierte el código de color CSS (HEX, rgb, rgba) a un objeto java.awt.Color.
     * Esto requiere una lógica personalizada para manejar rgb/rgba, ya que Color.decode() solo acepta HEX.
     */
   private Color parseColor(String code) {
        String cleanCode = code.toLowerCase().trim();
        
        try {
            if (cleanCode.startsWith("#")) {
                // Manejar colores HEX
                // Color.decode maneja formatos #RRGGBB y #RGB
                // Quitamos el alfa si hay (aunque el regex ya lo limita, es buena práctica)
                return Color.decode(cleanCode.substring(0, Math.min(cleanCode.length(), 7)));

            } else if (cleanCode.startsWith("rgb")) {
                // Manejar rgb() o rgba()
                
                // 1. Extraer solo los números entre paréntesis y eliminar todos los espacios en blanco.
                int start = cleanCode.indexOf('(') + 1;
                int end = cleanCode.indexOf(')');
                if (start == -1 || end == -1) throw new IllegalArgumentException("Formato rgb/rgba inválido.");

                String numbers = cleanCode.substring(start, end).replaceAll("\\s+", "");
                String[] parts = numbers.split(",");
                
                // Debe haber al menos R, G, B
                if (parts.length < 3) throw new IllegalArgumentException("Faltan componentes de color.");

                int r = Integer.parseInt(parts[0].trim());
                int g = Integer.parseInt(parts[1].trim());
                int b = Integer.parseInt(parts[2].trim());
                int a = 255; // Opacidad por defecto (1.0)
                
                // 2. Manejar el componente Alfa (opacidad) si es rgba()
                if (cleanCode.startsWith("rgba") && parts.length > 3) {
                    float alphaFloat = Float.parseFloat(parts[3].trim());
                    // Convertir el valor float (0.0 a 1.0) a int (0 a 255)
                    a = (int) (alphaFloat * 255f); 
                }
                
                // 3. Crear el objeto Color, asegurando que los valores estén dentro del rango 0-255.
                return new Color(
                    Math.max(0, Math.min(255, r)), 
                    Math.max(0, Math.min(255, g)), 
                    Math.max(0, Math.min(255, b)), 
                    Math.max(0, Math.min(255, a))
                );
            }
        } catch (NumberFormatException e) {
            System.err.println("Error de parseo numérico en el color: " + code + ". Detalles: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error de formato CSS para el color: " + code + ". Detalles: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error desconocido al parsear el color: " + code);
        }
        
        // Devuelve un color por defecto si falla el parseo
        return Color.LIGHT_GRAY;
    }
    

    /**
     * Comprueba si un color es oscuro para poder usar texto blanco (mejor contraste).
     * Se usa la fórmula de luminancia basada en RGB.
     */
    private boolean isDark(Color c) {
        if (c == null) return false;
        // Fórmula de luminancia: (0.299*R + 0.587*G + 0.114*B)
        double luminance = (0.299 * c.getRed() + 0.587 * c.getGreen() + 0.114 * c.getBlue()) / 255;
        // Si la luminancia es baja (menor que 0.5), es un color oscuro.
        return luminance < 0.5; 
    }
}