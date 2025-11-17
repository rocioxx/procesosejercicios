import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class ExtractorDeColoresCSSUnico extends JFrame {

    private JTextArea areaResultados;

    public ExtractorDeColoresCSSUnico() {
        super("Extractor de Colores CSS (Único)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // 1. Botón para abrir el JFileChooser
        JButton botonAbrir = new JButton("Buscar Archivo CSS y Extraer Colores");
        botonAbrir.addActionListener(e -> abrirFileChooser());
        
        // 2. Área de texto para mostrar los resultados
        areaResultados = new JTextArea("Presione el botón para seleccionar un archivo .css");
        areaResultados.setEditable(false);
        
        add(botonAbrir, BorderLayout.NORTH);
        add(new JScrollPane(areaResultados), BorderLayout.CENTER);

        setSize(550, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    
    private void abrirFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos CSS (*.css)", "css");
        fileChooser.setFileFilter(filter);
        
        int resultado = fileChooser.showOpenDialog(this); // Muestra la ventana de diálogo

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            List<String> colores = extraerColores(archivoSeleccionado.getAbsolutePath());
            mostrarResultados(archivoSeleccionado.getName(), colores);
        } else {
            areaResultados.setText("Búsqueda cancelada por el usuario.");
        }
    }

    
    private List<String> extraerColores(String rutaArchivo) {
        List<String> colores = new ArrayList<>();
       
        String colorRegex = "(#[0-9a-fA-F]{3,6})|(\\brgba?\\s*\\([^)]+\\))";
        Pattern pattern = Pattern.compile(colorRegex);

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(linea.toLowerCase());
                while (matcher.find()) {
                    String colorEncontrado = matcher.group(0).trim();
                    
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

  
    private void mostrarResultados(String nombreArchivo, List<String> colores) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Colores Únicos encontrados en ").append(nombreArchivo).append(" ---\n\n");
        
        if (colores.isEmpty()) {
            sb.append("No se encontraron códigos de color válidos en el archivo.");
        } else {
            for (String color : colores) {
                sb.append(color).append("\n");
            }
            sb.append("\nTotal de colores únicos: ").append(colores.size());
        }
        
        areaResultados.setText(sb.toString());
    }

    public static void main(String[] args) {
      
        SwingUtilities.invokeLater(ExtractorDeColoresCSSUnico::new);
    }
}