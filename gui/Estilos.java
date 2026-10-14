package lib.gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Colores, fuentes y metodos de ayuda para los estilos visuales.
 */
public class Estilos {

    // Paleta de colores para la interfaz deportiva
    public static final Color COLOR_FONDO_PRINCIPAL = new Color(18, 19, 24);
    public static final Color COLOR_FONDO_SECUNDARIO = new Color(28, 31, 38);
    public static final Color COLOR_TARJETA = new Color(35, 38, 47);
    public static final Color COLOR_TARJETA_BORDE = new Color(60, 66, 80);
    public static final Color COLOR_NARANJA = new Color(230, 90, 20);       // Naranja intenso con buen contraste
    public static final Color COLOR_NARANJA_HOVER = new Color(245, 110, 35);
    public static final Color COLOR_TEXTO_BLANCO = Color.WHITE;
    public static final Color COLOR_TEXTO_GRIS = new Color(175, 180, 195);
    public static final Color COLOR_VERDE = new Color(40, 167, 69);
    public static final Color COLOR_ROJO = new Color(215, 45, 45);
    public static final Color COLOR_DORADO = new Color(255, 195, 18);

    // Tipografias claras y legibles
    public static final Font FUENTE_TITULO_GRANDE = new Font("Segoe UI", Font.BOLD, 28);
    public static final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 20);
    public static final Font FUENTE_SUBTITULO = new Font("Segoe UI", Font.BOLD, 15);
    public static final Font FUENTE_TEXTO = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FUENTE_TEXTO_NEGRITA = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FUENTE_PEQUENA = new Font("Segoe UI", Font.PLAIN, 11);
    public static final Font FUENTE_BOTON = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FUENTE_VALORACION = new Font("Segoe UI", Font.BOLD, 22);

    // Cache para almacenar imagenes y evitar descargarlas o reescalarlas repetidamente
    private static HashMap<String, ImageIcon> cacheImagenes = new HashMap<String, ImageIcon>();

    /**
     * Crea un boton principal naranja con letras blancas en negrita de alto contraste.
     */
    public static JButton crearBotonPrincipal(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(FUENTE_BOTON);
        boton.setForeground(Color.WHITE);
        boton.setBackground(COLOR_NARANJA);
        boton.setOpaque(true);
        boton.setContentAreaFilled(true);
        boton.setFocusPainted(false);
        boton.setBorder(crearBordeRelleno(COLOR_NARANJA_HOVER, 8, 14));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }

    /**
     * Crea un boton secundario oscuro con borde visible y letras blancas de facil lectura.
     */
    public static JButton crearBotonSecundario(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(FUENTE_BOTON);
        boton.setForeground(Color.WHITE);
        boton.setBackground(new Color(45, 52, 65));
        boton.setOpaque(true);
        boton.setContentAreaFilled(true);
        boton.setFocusPainted(false);
        boton.setBorder(crearBordeRelleno(new Color(85, 95, 115), 8, 14));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }

    /**
     * Crea un boton de accion en color rojo para eliminar o cancelar.
     */
    public static JButton crearBotonPeligro(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(FUENTE_BOTON);
        boton.setForeground(Color.WHITE);
        boton.setBackground(COLOR_ROJO);
        boton.setOpaque(true);
        boton.setContentAreaFilled(true);
        boton.setFocusPainted(false);
        boton.setBorder(crearBordeRelleno(new Color(240, 70, 70), 8, 14));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }

    /**
     * Crea un boton para la barra de filtros asegurando contraste tanto activo como inactivo.
     */
    public static JButton crearBotonFiltro(String texto, boolean activo) {
        JButton boton = new JButton(texto);
        boton.setFont(FUENTE_BOTON);
        boton.setOpaque(true);
        boton.setContentAreaFilled(true);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        if (activo) {
            // Boton seleccionado en naranja vivo
            boton.setForeground(Color.WHITE);
            boton.setBackground(COLOR_NARANJA);
            boton.setBorder(crearBordeRelleno(COLOR_NARANJA_HOVER, 6, 14));
        } else {
            // Boton sin seleccionar con fondo oscuro pero texto blanco bien visible
            boton.setForeground(Color.WHITE);
            boton.setBackground(new Color(42, 47, 58));
            boton.setBorder(crearBordeRelleno(new Color(75, 84, 102), 6, 14));
        }
        return boton;
    }

    /**
     * Crea un borde con color exterior y margen interior (padding).
     */
    public static Border crearBordeRelleno(Color colorBorde, int paddingVertical, int paddingHorizontal) {
        LineBorder linea = new LineBorder(colorBorde, 1, true);
        EmptyBorder relleno = new EmptyBorder(paddingVertical, paddingHorizontal, paddingVertical, paddingHorizontal);
        return new CompoundBorder(linea, relleno);
    }

    /**
     * Carga y escala una imagen desde los recursos del proyecto o desde una URL.
     */
    public static ImageIcon cargarImagen(String rutaOUrl, int ancho, int alto, JLabel labelDestino) {
        if (rutaOUrl == null || rutaOUrl.trim().isEmpty()) {
            return cargarImagenLocal("/images/default_player.png", ancho, alto);
        }

        String claveCache = rutaOUrl + "_" + ancho + "x" + alto;
        if (cacheImagenes.containsKey(claveCache)) {
            ImageIcon iconoCache = cacheImagenes.get(claveCache);
            if (labelDestino != null) {
                labelDestino.setIcon(iconoCache);
            }
            return iconoCache;
        }

        // Si es una direccion web de Internet
        if (rutaOUrl.startsWith("http://") || rutaOUrl.startsWith("https://")) {
            try {
                BufferedImage imagen = ImageIO.read(new URL(rutaOUrl));
                if (imagen == null) {
                    throw new IOException("La URL no contiene una imagen compatible.");
                }
                Image escalada = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                ImageIcon iconoWeb = new ImageIcon(escalada);
                cacheImagenes.put(claveCache, iconoWeb);
                if (labelDestino != null) {
                    labelDestino.setIcon(iconoWeb);
                }
                return iconoWeb;
            } catch (IOException e) {
                System.err.println("No se pudo cargar la imagen " + rutaOUrl + ": " + e.getMessage());
                ImageIcon iconoReserva = cargarImagenLocal("/images/default_player.png", ancho, alto);
                if (labelDestino != null) {
                    labelDestino.setIcon(iconoReserva);
                }
                return iconoReserva;
            }
        }

        // Carga normal de imagen local desde el classpath
        ImageIcon iconoLocal = cargarImagenLocal(rutaOUrl, ancho, alto);
        cacheImagenes.put(claveCache, iconoLocal);
        if (labelDestino != null) {
            labelDestino.setIcon(iconoLocal);
        }
        return iconoLocal;
    }

    /**
     * Carga un archivo de imagen de la carpeta resources y lo redimensiona.
     */
    private static ImageIcon cargarImagenLocal(String rutaRecurso, int ancho, int alto) {
        try {
            URL recursoUrl = Estilos.class.getResource(rutaRecurso);
            if (recursoUrl != null) {
                ImageIcon iconoOriginal = new ImageIcon(recursoUrl);
                Image escalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                return new ImageIcon(escalada);
            }
        } catch (Exception e) {
            // Si falla continua al fallback
        }

        // Si no se encuentra la imagen pedida, usamos la imagen por defecto
        if (!rutaRecurso.equals("/images/default_player.png")) {
            return cargarImagenLocal("/images/default_player.png", ancho, alto);
        }

        // Imagen minima si tampoco estuviera la imagen por defecto
        BufferedImage vacia = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = vacia.createGraphics();
        g.setColor(COLOR_FONDO_SECUNDARIO);
        g.fillRect(0, 0, ancho, alto);
        g.setColor(COLOR_NARANJA);
        g.drawRect(2, 2, ancho - 4, alto - 4);
        g.dispose();
        return new ImageIcon(vacia);
    }
}
