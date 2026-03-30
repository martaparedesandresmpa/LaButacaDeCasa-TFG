
package controlador;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import modelo.contenido;

public class VentanaPrincipal extends JFrame {

    private final Color COLOR_GRANATE = new Color(100, 0, 0);
    private final Color FONDO_OSCURO = new Color(17, 20, 24);
    private final Color DORADO = new Color(229, 184, 105);
    private final Color BARRA_OSCURA = new Color(30, 33, 37);
    private final Color BORDE_GRIS = new Color(42, 45, 52);

    private String base = "C:/Users/marta/Documents/proyecto final/Imagenes/";
    private String pelisPath = base + "todas/";

    private int pX, pY;
    private CardLayout cardLayout;
    private JPanel contenedorCentral;
    private JLabel lnkInicio, lnkCatalogo, lnkRanking;
    private List<contenido> todasLasPeliculas;

    private Map<String, ImageIcon> cacheImagenes = new HashMap<>();

    public VentanaPrincipal() {
        try {
            setUndecorated(true);
            setSize(1300, 950);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            getContentPane().setLayout(new BorderLayout());
            getContentPane().setBackground(FONDO_OSCURO);

            GestorCatalogo gestor = new GestorCatalogo();
            List<contenido> pelisBruto = gestor.obtenerPeliculas();

           
            todasLasPeliculas = new ArrayList<>();
            for (contenido c : pelisBruto) {
                if (c.imagen != null) {
                    String imgStr = c.imagen.toLowerCase().replaceAll("\\s+", "");
                    if (!imgStr.contains("matrix") && 
                        !imgStr.contains("johnwick") && !imgStr.contains("johnweek") && 
                        !imgStr.contains("titanic") && 
                        !imgStr.contains("succession") && !imgStr.contains("sucesion") && 
                        !imgStr.contains("starwars") && !imgStr.contains("starwors")) {
                        
                        todasLasPeliculas.add(c);
                    }
                }
            }

            cardLayout = new CardLayout();
            contenedorCentral = new JPanel(cardLayout);
            contenedorCentral.setOpaque(false);

            JPanel cabecera = crearCabecera();

            contenedorCentral.add(crearPantallaInicio(todasLasPeliculas), "PantallaInicio");
            contenedorCentral.add(crearPantallaCatalogo(todasLasPeliculas), "PantallaCatalogo");
            contenedorCentral.add(crearPantallaRanking(todasLasPeliculas), "PantallaRanking");

            add(cabecera, BorderLayout.NORTH);
            add(contenedorCentral, BorderLayout.CENTER);

            cardLayout.show(contenedorCentral, "PantallaInicio");
            actualizarEstiloMenuSuperior("Inicio");

            JButton btnX = crearBotonCerrar();
            getLayeredPane().add(btnX, JLayeredPane.PALETTE_LAYER);

            cabecera.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent me) {
                    pX = me.getX();
                    pY = me.getY();
                }
            });

            cabecera.addMouseMotionListener(new MouseAdapter() {
                public void mouseDragged(MouseEvent me) {
                    setLocation(getLocation().x + me.getX() - pX, getLocation().y + me.getY() - pY);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JScrollPane crearPantallaInicio(List<contenido> pelis) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(FONDO_OSCURO);
        p.setBorder(BorderFactory.createEmptyBorder(25, 0, 25, 0));

        p.add(crearFilaInicio("Novedades", obtenerImagenes(pelis, 0, 5), pelis));
        p.add(crearFilaInicio("Películas", obtenerImagenes(pelis, 5, 10), pelis));
        p.add(crearFilaInicio("Series", obtenerSeriesInicioManual(), pelis));
        p.add(crearFilaInicio("Recomendaciones", obtenerImagenes(pelis, 2, 7), pelis));

        JScrollPane s = new JScrollPane(p);
        s.setBorder(null);
        s.getViewport().setBackground(FONDO_OSCURO);
        s.getVerticalScrollBar().setUI(new ScrollPersonalizado());
        s.setWheelScrollingEnabled(true);
        s.getVerticalScrollBar().setUnitIncrement(24);
        s.getVerticalScrollBar().setBlockIncrement(120);
        return s;
    }

    private List<String> obtenerImagenes(List<contenido> lista, int ini, int fin) {
        List<String> imagenes = new ArrayList<>();
        if (lista == null) return imagenes;

        for (int i = ini; i < fin && i < lista.size(); i++) {
            imagenes.add(lista.get(i).imagen);
        }

        return imagenes;
    }

    private List<String> obtenerSeriesInicioManual() {
        List<String> series = new ArrayList<>();
        series.add("juegodelcalamar.jpg");
        series.add("narcos.jpg");
        series.add("onepiece.jpg");
        series.add("thelast.jpg");
        series.add("blackmirror.jpg");
        return series;
    }

    private JPanel crearFilaInicio(String tit, List<String> imgs, List<contenido> db) {
        JPanel fila = new JPanel();
        fila.setLayout(new BoxLayout(fila, BoxLayout.Y_AXIS));
        fila.setOpaque(false);
        fila.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));

        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT, 220, 0));
        panelTitulo.setOpaque(false);
        JLabel l = new JLabel(tit);
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Serif", Font.BOLD, 26));
        panelTitulo.add(l);

        fila.add(panelTitulo);
        fila.add(Box.createVerticalStrut(15));

        JPanel portadas = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 0));
        portadas.setOpaque(false);

        for (String nombreImagen : imgs) {
            contenido encontrado = buscarContenidoPorImagen(db, nombreImagen);
            JButton b = crearBotonPortada(new File(pelisPath + nombreImagen), 150, 215);

            if (encontrado != null) {
                contenido finalEncontrado = encontrado;
                b.addActionListener(e -> new VentanaDetalles(finalEncontrado, (ImageIcon) b.getIcon()).setVisible(true));
            }

            portadas.add(b);
        }

        fila.add(portadas);
        return fila;
    }

    private contenido buscarContenidoPorImagen(List<contenido> lista, String imagen) {
        if (lista == null) return null;

        for (contenido c : lista) {
            if (c.imagen != null && c.imagen.equalsIgnoreCase(imagen)) {
                return c;
            }
        }
        return null;
    }

    // --- CATÁLOGO PERFECTAMENTE CENTRADO ---
    private JScrollPane crearPantallaCatalogo(List<contenido> pelis) {
        // Creamos la cuadrícula de 5 columnas para las películas
        JPanel catalogoGrid = new JPanel(new GridLayout(0, 5, 25, 30));
        catalogoGrid.setBackground(FONDO_OSCURO);
        catalogoGrid.setOpaque(false);

        if (pelis != null) {
            for (contenido peli : pelis) {
                JButton btn = crearBotonPortada(new File(pelisPath + peli.imagen), 150, 215);
                btn.addActionListener(e -> new VentanaDetalles(peli, (ImageIcon) btn.getIcon()).setVisible(true));
                catalogoGrid.add(btn);
            }
        }

       
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 40));
        wrapper.setBackground(FONDO_OSCURO);
        wrapper.add(catalogoGrid);

        JScrollPane s = new JScrollPane(wrapper);
        s.setBorder(null);
        s.getViewport().setBackground(FONDO_OSCURO);
        s.getVerticalScrollBar().setUI(new ScrollPersonalizado());
        s.setWheelScrollingEnabled(true);
        s.getVerticalScrollBar().setUnitIncrement(24);
        s.getVerticalScrollBar().setBlockIncrement(120);
        return s;
    }

    private JScrollPane crearPantallaRanking(List<contenido> pelis) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(FONDO_OSCURO);
        p.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        JLabel tituloPagina = new JLabel("TOP 10 BUTACA");
        tituloPagina.setForeground(DORADO);
        tituloPagina.setFont(new Font("Segoe UI", Font.BOLD, 40));
        tituloPagina.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(tituloPagina);
        p.add(Box.createVerticalStrut(40));

        Map<String, Double> notas = new HashMap<>();
        notas.put("breakingbad", 9.5);
        notas.put("arcane", 9.0);
        notas.put("elclubdelalucha", 8.8);
        notas.put("forrestgump", 8.8);
        notas.put("pulpfiction", 8.8);
        notas.put("interstellar", 8.7);
        notas.put("narcos", 8.7);
        notas.put("gladiator", 8.5);
        notas.put("parasitos", 8.5);
        notas.put("thelastofus", 8.5);
        notas.put("vikingos", 8.5);

        class ItemRanking {
            contenido cont;
            double nota;

            ItemRanking(contenido cont, double nota) {
                this.cont = cont;
                this.nota = nota;
            }
        }

        List<ItemRanking> ranking = new ArrayList<>();

        if (pelis != null) {
            for (contenido c : pelis) {
                String clave = normalizarTitulo(c.titulo);
                if (notas.containsKey(clave)) {
                    ranking.add(new ItemRanking(c, notas.get(clave)));
                }
            }
        }

        ranking.sort((a, b) -> Double.compare(b.nota, a.nota));

        for (int i = 0; i < 10 && i < ranking.size(); i++) {
            ItemRanking item = ranking.get(i);
            p.add(crearFilaRanking(item.cont, item.nota, i + 1));
            p.add(Box.createVerticalStrut(15));

            if (i < 9 && i < ranking.size() - 1) {
                JSeparator separador = new JSeparator(JSeparator.HORIZONTAL);
                separador.setForeground(BORDE_GRIS);
                separador.setBackground(BORDE_GRIS);
                p.add(separador);
                p.add(Box.createVerticalStrut(15));
            }
        }

        JScrollPane s = new JScrollPane(p);
        s.setBorder(null);
        s.getViewport().setBackground(FONDO_OSCURO);
        s.getVerticalScrollBar().setUI(new ScrollPersonalizado());
        s.setWheelScrollingEnabled(true);
        s.getVerticalScrollBar().setUnitIncrement(24);
        s.getVerticalScrollBar().setBlockIncrement(120);
        return s;
    }

    private JPanel crearFilaRanking(contenido c, double nota, int puesto) {
        JPanel fila = new JPanel(new BorderLayout(30, 0));
        fila.setOpaque(false);
        fila.setMaximumSize(new Dimension(900, 120));

        JLabel lblPos = new JLabel(String.valueOf(puesto));
        lblPos.setForeground(DORADO);
        lblPos.setFont(new Font("Segoe UI", Font.BOLD, 55));
        lblPos.setPreferredSize(new Dimension(70, 120));

        JButton btnImg = crearBotonPortada(new File(pelisPath + c.imagen), 85, 120);
        btnImg.addActionListener(e -> new VentanaDetalles(c, (ImageIcon) btnImg.getIcon()).setVisible(true));

        JPanel txt = new JPanel(new GridLayout(2, 1));
        txt.setOpaque(false);

        JLabel nom = new JLabel(c.titulo.toUpperCase());
        nom.setForeground(Color.WHITE);
        nom.setFont(new Font("Segoe UI", Font.BOLD, 22));

        JLabel score = new JLabel("Nota Butaca: " + String.format("%.1f", nota));
        score.setForeground(DORADO);
        score.setFont(new Font("Segoe UI", Font.ITALIC, 16));

        txt.add(nom);
        txt.add(score);

        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        wrapper.setOpaque(false);
        wrapper.add(btnImg);
        wrapper.add(txt);

        fila.add(lblPos, BorderLayout.WEST);
        fila.add(wrapper, BorderLayout.CENTER);

        return fila;
    }

    private JPanel crearCabecera() {
        JPanel cab = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, COLOR_GRANATE, 0, getHeight(), FONDO_OSCURO);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());

                g.setColor(BARRA_OSCURA);
                g.fillRect(0, getHeight() - 2, getWidth(), 2);
            }
        };

        cab.setPreferredSize(new Dimension(1300, 200));
        cab.setLayout(new BoxLayout(cab, BoxLayout.Y_AXIS));

        JLabel lblTitulo = new JLabel("<html><center>LA BUTACA<br>DE TU CASA</center></html>", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 45));
        lblTitulo.setForeground(DORADO);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel menuSup = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 0));
        menuSup.setOpaque(false);

        lnkInicio = crearEnlaceSuperior("Inicio");
        lnkCatalogo = crearEnlaceSuperior("Catálogo");
        lnkRanking = crearEnlaceSuperior("Ranking");

        menuSup.add(lnkInicio);
        menuSup.add(lnkCatalogo);
        menuSup.add(lnkRanking);

        cab.add(Box.createVerticalStrut(30));
        cab.add(lblTitulo);
        cab.add(Box.createVerticalStrut(20));
        cab.add(menuSup);

        return cab;
    }

    private JButton crearBotonPortada(File f, int w, int h) {
        BotonRedondeado btn = new BotonRedondeado();
        btn.setPreferredSize(new Dimension(w, h));
        btn.setMinimumSize(new Dimension(w, h));
        btn.setMaximumSize(new Dimension(w, h));

        if (f.exists()) {
            String clave = f.getAbsolutePath() + "_" + w + "x" + h;

            if (!cacheImagenes.containsKey(clave)) {
                Image img = new ImageIcon(f.getAbsolutePath()).getImage()
                        .getScaledInstance(w, h, Image.SCALE_SMOOTH);
                cacheImagenes.put(clave, new ImageIcon(img));
            }

            btn.setIcon(cacheImagenes.get(clave));
        } else {
            btn.setBackground(Color.BLACK);
            btn.setOpaque(true);
            btn.setText("Sin imagen");
            btn.setForeground(Color.WHITE);
        }

        return btn;
    }

    private JLabel crearEnlaceSuperior(String texto) {
        JLabel l = new JLabel(texto);
        l.setForeground(Color.LIGHT_GRAY);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        l.setCursor(new Cursor(Cursor.HAND_CURSOR));

        l.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (texto.equals("Inicio")) {
                    cardLayout.show(contenedorCentral, "PantallaInicio");
                } else if (texto.equals("Catálogo")) {
                    cardLayout.show(contenedorCentral, "PantallaCatalogo");
                } else if (texto.equals("Ranking")) {
                    cardLayout.show(contenedorCentral, "PantallaRanking");
                }

                actualizarEstiloMenuSuperior(texto);
            }
        });

        return l;
    }

    private void actualizarEstiloMenuSuperior(String t) {
        for (JLabel l : Arrays.asList(lnkInicio, lnkCatalogo, lnkRanking)) {
            if (l != null) {
                l.setForeground(l.getText().equals(t) ? DORADO : Color.LIGHT_GRAY);
                l.setBorder(l.getText().equals(t)
                        ? BorderFactory.createMatteBorder(0, 0, 2, 0, DORADO)
                        : null);
            }
        }
    }

    private JButton crearBotonCerrar() {
        JButton btnX = new JButton("✕");
        btnX.setBounds(1260, 15, 30, 30);
        btnX.setForeground(Color.GRAY);
        btnX.setFont(new Font("Arial", Font.BOLD, 18));
        btnX.setContentAreaFilled(false);
        btnX.setBorder(null);
        btnX.addActionListener(e -> System.exit(0));
        return btnX;
    }

    private String normalizarTitulo(String titulo) {
        if (titulo == null) return "";

        return titulo.toLowerCase().trim()
                .replace("á", "a").replace("é", "e").replace("í", "i")
                .replace("ó", "o").replace("ú", "u").replace("ñ", "n")
                .replaceAll("\\s+", "")
                .replaceAll("[^a-z0-9]", "");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}

class BotonRedondeado extends JButton {
    private int radio = 15;

    public BotonRedondeado() {
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Shape clip = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), radio, radio);
        g2.setClip(clip);

        if (getIcon() != null) {
            getIcon().paintIcon(this, g2, 0, 0);
        } else {
            g2.setColor(Color.BLACK);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radio, radio);
            super.paintComponent(g2);
        }

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getModel().isRollover() ? new Color(229, 184, 105) : new Color(42, 45, 52));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radio, radio);
        g2.dispose();
    }
}

class ScrollPersonalizado extends BasicScrollBarUI {
    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle tb) {
        g.setColor(new Color(17, 20, 24));
        g.fillRect(tb.x, tb.y, tb.width, tb.height);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle tb) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(45, 48, 52));
        g2.fillRoundRect(tb.x + 2, tb.y + 2, tb.width - 4, tb.height - 4, 10, 10);
        g2.dispose();
    }

    @Override
    protected JButton createDecreaseButton(int o) {
        return crearBotonVacio();
    }

    @Override
    protected JButton createIncreaseButton(int o) {
        return crearBotonVacio();
    }

    private JButton crearBotonVacio() {
        JButton b = new JButton();
        b.setPreferredSize(new Dimension(0, 0));
        return b;
    }
}
