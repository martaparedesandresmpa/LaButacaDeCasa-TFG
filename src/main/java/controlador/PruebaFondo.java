package controlador;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PruebaFondo extends JFrame {
    public PruebaFondo() {
        setTitle("Diagnóstico de Imagen");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- ESTA ES LA RUTA QUE VAMOS A COMPROBAR ---
        String ruta = "C:/Users/marta/Documents/proyecto final/Imagenes/fondo/butacas2.jpg";
        
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                File f = new File(ruta);
                if (f.exists()) {
                    // Si el archivo existe, lo dibuja
                    g.drawImage(new ImageIcon(ruta).getImage(), 0, 0, getWidth(), getHeight(), null);
                    System.out.println("✅ ¡IMAGEN ENCONTRADA! El código puede leer el archivo.");
                } else {
                    // Si no existe, pinta la ventana de ROJO brillante
                    g.setColor(Color.RED);
                    g.fillRect(0, 0, getWidth(), getHeight());
                    
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Arial", Font.BOLD, 16));
                    g.drawString("❌ ERROR: No se encuentra el archivo en:", 50, 100);
                    g.drawString(ruta, 50, 130);
                    System.out.println("❌ ARCHIVO NO ENCONTRADO EN: " + ruta);
                }
            }
        };
        add(panel);
    }

    public static void main(String[] args) {
        // Arrancamos la prueba
        SwingUtilities.invokeLater(() -> {
            new PruebaFondo().setVisible(true);
        });
    }
}