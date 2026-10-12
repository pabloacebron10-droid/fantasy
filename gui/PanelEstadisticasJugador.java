package lib.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import lib.modelo.Jugador;

/**
 * Panel que muestra las 5 estadisticas del jugador con barras de progreso.
 */
public class PanelEstadisticasJugador extends JPanel {

    private Jugador jugador;

    public PanelEstadisticasJugador(Jugador jugador) {
        this.jugador = jugador;
        configurarPanel();
    }

    private void configurarPanel() {
        setOpaque(false);
        setLayout(new GridLayout(5, 1, 0, 6));
        setBorder(new EmptyBorder(4, 4, 4, 4));

        if (jugador != null) {
            agregarFilaEstadistica("ATAQUE", jugador.getAtaque(), Estilos.COLOR_NARANJA);
            agregarFilaEstadistica("DEFENSA", jugador.getDefensa(), new Color(52, 152, 219));
            agregarFilaEstadistica("REBOTE", jugador.getRebote(), new Color(155, 89, 182));
            agregarFilaEstadistica("PASE", jugador.getPase(), new Color(46, 204, 113));
            agregarFilaEstadistica("TIRO", jugador.getTiro(), Estilos.COLOR_DORADO);
        }
    }

    /**
     * Anade una fila con el nombre de la estadistica y su barra de progreso.
     */
    private void agregarFilaEstadistica(String nombreStat, int valor, Color colorBarra) {
        JPanel fila = new JPanel(new BorderLayout(8, 0));
        fila.setOpaque(false);

        // Nombre de la estadistica en blanco para que se lea claramente
        JLabel lblNombre = new JLabel(nombreStat);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblNombre.setForeground(new Color(225, 230, 245));
        lblNombre.setPreferredSize(new Dimension(68, 18));
        fila.add(lblNombre, BorderLayout.WEST);

        // Barra de progreso con el valor
        JProgressBar barra = new JProgressBar(0, 100);
        barra.setValue(valor);
        barra.setStringPainted(true);
        barra.setString(valor + " / 100");
        barra.setFont(new Font("Segoe UI", Font.BOLD, 10));
        barra.setForeground(colorBarra);
        barra.setBackground(new Color(22, 24, 30));
        barra.setBorder(BorderFactory.createLineBorder(new Color(55, 60, 75), 1, true));
        fila.add(barra, BorderLayout.CENTER);

        add(fila);
    }
}
