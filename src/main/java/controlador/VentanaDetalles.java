package controlador;

import modelo.contenido;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URI;

public class VentanaDetalles extends JFrame {

    private final Color FONDO_OSCURO = new Color(17, 20, 24);
    private final Color DORADO = new Color(229, 184, 105);
    private final Color ROJO_CINE = new Color(220, 0, 0);
    private final Color BLANCO_SUAVE = new Color(230, 230, 230);

    private final String base = "C:/Users/marta/Documents/proyecto final/Imagenes/";
    private final String pelisPath = base + "todas/";

    public VentanaDetalles(contenido peli, ImageIcon portadaOriginal) {
        setTitle(peli.titulo);
        setSize(1150, 700);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setLayout(null); 
        getContentPane().setBackground(FONDO_OSCURO);

        // BOTÓN CERRAR
        JButton btnCerrar = new JButton("X");
        btnCerrar.setBounds(1090, 15, 30, 30);
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 20));
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        btnCerrar.setFocusPainted(false);
        btnCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCerrar.addActionListener(e -> dispose());
        add(btnCerrar);

        // PORTADA
        JLabel lblPortada = new JLabel();
        lblPortada.setBounds(60, 90, 380, 540);
        lblPortada.setBorder(BorderFactory.createLineBorder(DORADO, 1));
        lblPortada.setHorizontalAlignment(SwingConstants.CENTER);
        lblPortada.setVerticalAlignment(SwingConstants.CENTER);

        ImageIcon iconoFinal = cargarPortadaGrande(peli);
        if (iconoFinal != null) {
            lblPortada.setIcon(iconoFinal);
        } else if (portadaOriginal != null) {
            Image img = portadaOriginal.getImage().getScaledInstance(380, 540, Image.SCALE_SMOOTH);
            lblPortada.setIcon(new ImageIcon(img));
        } else {
            lblPortada.setText("Sin imagen");
            lblPortada.setForeground(Color.WHITE);
        }
        add(lblPortada);

        // TÍTULO DE LA PELÍCULA
        JLabel lblTitulo = new JLabel(peli.titulo != null ? peli.titulo.toUpperCase() : "TÍTULO DESCONOCIDO");
        lblTitulo.setForeground(DORADO);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 42));
        lblTitulo.setBounds(500, 100, 580, 60);
        add(lblTitulo);

        // LÓGICA DE DATOS
        String t = "";
        if (peli.titulo != null) {
            t = peli.titulo.toLowerCase().trim()
                .replace("á", "a").replace("é", "e").replace("í", "i")
                .replace("ó", "o").replace("ú", "u").replace("ñ", "n")
                .replaceAll("\\s+", "").replaceAll("[^a-z0-9]", "");
        }

        String textoSinopsis = (peli.sinopsis != null && !peli.sinopsis.trim().isEmpty()) ? peli.sinopsis : "Sinopsis no disponible.";
        String textoGenero = (peli.genero != null && !peli.genero.trim().isEmpty()) ? peli.genero : "Varios";
        String textoAnio = (peli.anio > 0) ? String.valueOf(peli.anio) : "N/D";

        // Asignaciones manuales de seguridad
        if (t.contains("arcane")) { textoAnio = "2021"; textoGenero = "Animación, Acción"; textoSinopsis = "En medio del conflicto entre Piltover y Zaun, dos hermanas quedan separadas por la violencia y una tecnología capaz de cambiar su mundo."; }
        else if (t.contains("interestelar") || t.contains("interstellar")) { textoAnio = "2014"; textoGenero = "Ciencia Ficción, Drama"; textoSinopsis = "En una Tierra al borde del colapso, un grupo de astronautas cruza un agujero de gusano para buscar un nuevo hogar para la humanidad."; }
        else if (t.contains("sociedaddelanieve")) { textoAnio = "2023"; textoGenero = "Drama, Supervivencia"; textoSinopsis = "Tras estrellarse su avión en los Andes, un grupo de supervivientes debe afrontar condiciones extremas para seguir con vida."; }
        else if (t.contains("thelast")) { textoAnio = "2023"; textoGenero = "Acción, Drama"; textoSinopsis = "En un mundo arrasado por una infección, Joel debe escoltar a Ellie a través de Estados Unidos en un viaje de supervivencia."; }

        // INFORMACIÓN AÑO Y GÉNERO
        JLabel lblInfo = new JLabel("AÑO: " + textoAnio + "   |   GÉNERO: " + textoGenero);
        lblInfo.setForeground(BLANCO_SUAVE);
        lblInfo.setFont(new Font("Segoe UI", Font.ITALIC, 20));
        lblInfo.setBounds(500, 170, 600, 30);
        add(lblInfo);

        // TÍTULO SINOPSIS
        JLabel lblSinopsisTitulo = new JLabel("SINOPSIS");
        lblSinopsisTitulo.setForeground(Color.WHITE);
        lblSinopsisTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblSinopsisTitulo.setBounds(500, 235, 200, 30);
        add(lblSinopsisTitulo);

        // --- BLOQUE CORREGIDO: JTextArea con JScrollPane ---
        JTextArea txtSinopsis = new JTextArea(textoSinopsis);
        txtSinopsis.setLineWrap(true);
        txtSinopsis.setWrapStyleWord(true);
        txtSinopsis.setEditable(false);
        txtSinopsis.setOpaque(false);
        txtSinopsis.setForeground(Color.WHITE);
        txtSinopsis.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        txtSinopsis.setMargin(new java.awt.Insets(0, 0, 0, 0));

        JScrollPane scrollSinopsis = new JScrollPane(txtSinopsis);
        scrollSinopsis.setBounds(500, 285, 580, 250); // Tamaño ajustado para que no se corte
        scrollSinopsis.setBorder(null);
        scrollSinopsis.setOpaque(false);
        scrollSinopsis.getViewport().setOpaque(false);
        scrollSinopsis.getVerticalScrollBar().setPreferredSize(new Dimension(0,0)); // Scroll invisible pero funcional
        add(scrollSinopsis);

        // BOTÓN TRÁILER
        JButton btnVerTrailer = new JButton("▶ REPRODUCIR TRÁILER");
        btnVerTrailer.setBounds(525, 560, 300, 64);
        btnVerTrailer.setBackground(ROJO_CINE);
        btnVerTrailer.setForeground(Color.WHITE);
        btnVerTrailer.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnVerTrailer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVerTrailer.addActionListener(e -> {
            try {
                String url = "https://www.youtube.com/results?search_query=" + (peli.titulo != null ? peli.titulo.replace(" ", "+") : "") + "+trailer";
                Desktop.getDesktop().browse(new URI(url));
            } catch (Exception ex) { ex.printStackTrace(); }
        });
        add(btnVerTrailer);

        // BOTÓN VOLVER
        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setBounds(845, 560, 160, 64);
        btnVolver.setBackground(FONDO_OSCURO);
        btnVolver.setForeground(DORADO);
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnVolver.setBorder(BorderFactory.createLineBorder(DORADO, 2));
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVolver.addActionListener(e -> dispose());
        add(btnVolver);
    }

    private ImageIcon cargarPortadaGrande(contenido peli) {
        if (peli.imagen == null) return null;
        File archivo = new File(pelisPath + peli.imagen);
        if (!archivo.exists()) return null;
        Image img = new ImageIcon(archivo.getAbsolutePath()).getImage().getScaledInstance(380, 540, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
}