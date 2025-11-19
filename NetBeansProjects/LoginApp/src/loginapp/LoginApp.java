package loginapp;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author rocio
 */
public class LoginApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Ejecutar la interfaz gráfica en el Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            // Crear el marco principal
            JFrame frame = new JFrame("Login");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            // Agregar el panel de login al marco
            LoginForm loginForm = new LoginForm();
            frame.getContentPane().add(loginForm);
            
            // Ajustar el tamaño del marco al contenido y hacerlo visible
            frame.pack();
            frame.setLocationRelativeTo(null); // Centrar en la pantalla
            frame.setVisible(true);
        });
    }
}