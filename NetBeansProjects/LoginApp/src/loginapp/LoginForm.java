/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package loginapp;
import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author rocio
 */
public class LoginForm extends javax.swing.JPanel {

    /**
     * Creates new form LoginForm
     */
    public LoginForm() {
        initComponents();
        txtEmail.setText("");
        txtURL.setText("");
        txtUsuario.setText("");
        txtPassword.setText("");
        errores.setText("");
    }
    
    private void validateFields() {
        String email = txtEmail.getText();
        String url = txtURL.getText();
        String usuario = txtUsuario.getText();
        String password = new String(txtPassword.getPassword());
        
        // Asume éxito al principio
        boolean allValid = true;
        
        // Limpiar mensajes de error previos
        errores.setText("");
        
        // --- 1. Validación de Correo Electrónico ---
        // Expresión: texto@dominio.ext
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!email.matches(emailRegex)) {
            JOptionPane.showMessageDialog(this, 
                "ERROR: El formato del correo electrónico es incorrecto.\n" +
                "Debe ser: ejemplo@dominio.com", 
                "Error de Validación", JOptionPane.ERROR_MESSAGE);
            allValid = false;
        }
        
        // --- 2. Validación de URL ---
        // Expresión: (https:// o http://)?cualquiercosa.dominio.es/cualquiercosa:puerto o IP:puerto
        // Simplificada: (https?://)?(dominio o IP):puerto
        String urlRegex = "^(https?://)?([\\w\\d.-]+\\.[a-z]{2,}|\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}):\\d+$";
        if (!url.matches(urlRegex)) {
            // Requisito 2.1: Comentar si no es https, pero se deja enviar.
            if (url.startsWith("http://")) {
                JOptionPane.showMessageDialog(this, 
                    "ADVERTENCIA: La URL usa el protocolo HTTP (no seguro).", 
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else if (!url.matches(urlRegex)) {
                 // Si no cumple el formato completo (incluyendo el puerto), error grave
                 JOptionPane.showMessageDialog(this, 
                    "ERROR: La URL debe tener el formato:\n" +
                    "https://cualquiercosa.dominio.es/cualquiercosa:puerto\n" +
                    "o ser una IP:puerto (ej: 192.168.1.1:8080)", 
                    "Error de Validación", JOptionPane.ERROR_MESSAGE);
                 allValid = false;
            }
        } else if (url.startsWith("http://")) {
             // Si el formato es correcto, pero usa HTTP
             JOptionPane.showMessageDialog(this, 
                    "ADVERTENCIA: La URL usa el protocolo HTTP (no seguro).", 
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        
        // --- 3. Validación de Usuario ---
        // Requisitos: 
        // 1. Solo mayúsculas, símbolos y números. Regex: ^[A-Z\\d\\W]+$ (\\W incluye símbolos, no letras ni números)
        // 2. Números no pueden estar al principio. Regex: ^\\d
        
        String usuarioRegex = "^[A-Z\\d\\W]+$"; // A-Z, 0-9, y símbolos
        
        if (!usuario.matches(usuarioRegex)) {
            JOptionPane.showMessageDialog(this, 
                "ERROR: El usuario solo puede contener mayúsculas, símbolos y números.", 
                "Error de Validación", JOptionPane.ERROR_MESSAGE);
            allValid = false;
        } else if (Pattern.compile("^\\d").matcher(usuario).find()) {
             JOptionPane.showMessageDialog(this, 
                "ERROR: El usuario no puede empezar con un número.", 
                "Error de Validación", JOptionPane.ERROR_MESSAGE);
            allValid = false;
        }
        
        // --- 4. Validación de Contraseña ---
        // Requisitos:
        // 1. Empieza por minúscula: ^[a-z]
        // 2. Puede tener mayúsculas: [A-Z]*
        // 3. No puede tener guion bajo (_): [^_]*
        // 4. Puede tener números: [0-9]*
        // 5. No puede tener símbolos: [^\\W]* (símbolos están en \\W, el negado [^\\W] excluye símbolos y _)
        
        // Regex: Empieza por minúscula, y luego cualquier combinación de mayúsculas, minúsculas, números, pero NO símbolos ni guion bajo
        // (Nota: el guion bajo es un caracter de palabra (\w) no un símbolo (\W), así que lo excluimos explícitamente en el primer grupo)
        String passwordRegex = "^[a-z][a-zA-Z0-9]*$"; 
        
        if (password.contains("_")) {
             JOptionPane.showMessageDialog(this, 
                "ERROR: La contraseña no puede contener guion bajo (_).", 
                "Error de Validación", JOptionPane.ERROR_MESSAGE);
             allValid = false;
        } else if (Pattern.compile("[\\W&&[^_]]").matcher(password).find()) {
            // Chequea si hay algún SÍMBOLO que NO sea guion bajo (excluye símbolos)
             JOptionPane.showMessageDialog(this, 
                "ERROR: La contraseña no puede contener símbolos (excepto letras, números y el guion bajo, que está prohibido).", 
                "Error de Validación", JOptionPane.ERROR_MESSAGE);
             allValid = false;
        } else if (!password.matches("^[a-z].*$")) {
             JOptionPane.showMessageDialog(this, 
                "ERROR: La contraseña debe empezar por una letra minúscula.", 
                "Error de Validación", JOptionPane.ERROR_MESSAGE);
             allValid = false;
        }
        
        // --- Resultado Final ---
        if (allValid) {
            errores.setForeground(new Color(0, 150, 0)); // Verde
            errores.setText("¡Inicio de sesión exitoso!");
        } else {
            errores.setForeground(Color.RED);
            errores.setText("Datos de inicio de sesión inválidos.");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtURL = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        errores = new javax.swing.JLabel();

        jLabel1.setText("Correo electrónico");

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        jLabel2.setText("URL");

        txtURL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtURLActionPerformed(evt);
            }
        });

        jLabel3.setText("Usuario");

        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });

        jLabel4.setText("Contraseña");

        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });

        btnLogin.setText("Iniciar sesión");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        errores.setText("jLabel5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(btnLogin))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
                        .addComponent(errores)
                        .addGap(77, 77, 77))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtURL, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errores)
                    .addComponent(btnLogin))
                .addContainerGap(33, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
       validateFields();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtURLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtURLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtURLActionPerformed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel errores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtURL;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
