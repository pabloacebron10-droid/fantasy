package lib.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import lib.modelo.EquipoFantasy;
import lib.modelo.Jugador;
import lib.modelo.JugadorLeyenda;

/**
 * Tarjeta visual para mostrar los datos de un jugador en el mercado.
 */
public class TarjetaJugador extends JPanel {

    private Jugador jugador;
    private EquipoFantasy equipo;
    private VentanaMercado ventanaMercado;

    private JLabel lblFoto;
    private JButton btnAccion;
    private JButton btnDetalles;

    public TarjetaJugador(Jugador jugador, EquipoFantasy equipo, VentanaMercado ventanaMercado) {
        this.jugador = jugador;
        this.equipo = equipo;
        this.ventanaMercado = ventanaMercado;

        configurarTarjeta();
    }

    private void configurarTarjeta() {
        setLayout(new BorderLayout(0, 8));
        setBackground(Estilos.COLOR_TARJETA);
        setPreferredSize(new Dimension(240, 395));

        boolean yaEnEquipo = equipo.contieneJugador(jugador);

        // Borde verde si el jugador esta fichado, o borde normal si no
        if (yaEnEquipo) {
            setBorder(Estilos.crearBordeRelleno(Estilos.COLOR_VERDE, 10, 10));
        } else {
            setBorder(Estilos.crearBordeRelleno(Estilos.COLOR_TARJETA_BORDE, 10, 10));
        }

        // 1. Parte superior: posicion y media (OVR)
        JPanel panelCabecera = new JPanel(new BorderLayout());
        panelCabecera.setOpaque(false);

        JLabel lblPosicion = new JLabel(jugador.getPosicion().toUpperCase());
        lblPosicion.setFont(Estilos.FUENTE_PEQUENA);
        lblPosicion.setForeground(Estilos.COLOR_NARANJA);
        panelCabecera.add(lblPosicion, BorderLayout.WEST);

        JLabel lblOvr = new JLabel(jugador.getValoracionGeneral() + " OVR");
        lblOvr.setFont(Estilos.FUENTE_TEXTO_NEGRITA);
        lblOvr.setForeground(Estilos.COLOR_DORADO);
        panelCabecera.add(lblOvr, BorderLayout.EAST);

        add(panelCabecera, BorderLayout.NORTH);

        // 2. Parte central: imagen y datos del jugador
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setOpaque(false);

        // Foto del jugador
        lblFoto = new JLabel();
        lblFoto.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblFoto.setPreferredSize(new Dimension(170, 160));
        lblFoto.setMaximumSize(new Dimension(170, 160));
        lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
        Estilos.cargarImagen(jugador.getImagen(), 170, 160, lblFoto);
        panelCentral.add(lblFoto);

        panelCentral.add(Box.createVerticalStrut(8));

        // Nombre
        JLabel lblNombre = new JLabel(jugador.getNombreCompleto());
        lblNombre.setFont(Estilos.FUENTE_SUBTITULO);
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(lblNombre);

        // Equipo y numero
        JLabel lblEquipoInfo = new JLabel(jugador.getEquipoHistorico() + " · #" + jugador.getNumeroCamiseta());
        lblEquipoInfo.setFont(Estilos.FUENTE_PEQUENA);
        lblEquipoInfo.setForeground(Estilos.COLOR_TEXTO_GRIS);
        lblEquipoInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(lblEquipoInfo);

        panelCentral.add(Box.createVerticalStrut(8));

        // Resumen rapido de 3 estadisticas principales
        JPanel panelStatsMini = new JPanel(new GridLayout(1, 3, 5, 0));
        panelStatsMini.setOpaque(false);
        panelStatsMini.setMaximumSize(new Dimension(215, 26));
        panelStatsMini.add(crearPillStat("ATQ", jugador.getAtaque()));
        panelStatsMini.add(crearPillStat("DEF", jugador.getDefensa()));
        panelStatsMini.add(crearPillStat("TIR", jugador.getTiro()));
        panelCentral.add(panelStatsMini);

        add(panelCentral, BorderLayout.CENTER);

        // 3. Botones inferiores
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 8, 0));
        panelBotones.setOpaque(false);

        // Boton para abrir la ventana modal de detalles
        btnDetalles = Estilos.crearBotonSecundario("Detalles");
        btnDetalles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDialogoDetalles();
            }
        });
        panelBotones.add(btnDetalles);

        // Boton para fichar o aviso si ya esta seleccionado
        if (yaEnEquipo) {
            btnAccion = new JButton("En equipo");
            btnAccion.setFont(Estilos.FUENTE_BOTON);
            btnAccion.setForeground(Color.WHITE);
            btnAccion.setBackground(new Color(35, 145, 65));
            btnAccion.setOpaque(true);
            btnAccion.setContentAreaFilled(true);
            btnAccion.setFocusPainted(false);
            btnAccion.setBorder(Estilos.crearBordeRelleno(new Color(46, 204, 113), 6, 8));
            btnAccion.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnAccion.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(ventanaMercado,
                            jugador.getNombreCompleto() + " ya forma parte de tu plantilla.",
                            "Jugador en plantilla", JOptionPane.INFORMATION_MESSAGE);
                }
            });
        } else {
            btnAccion = Estilos.crearBotonPrincipal("Fichar");
            btnAccion.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gestionarFichaje();
                }
            });
        }
        panelBotones.add(btnAccion);

        add(panelBotones, BorderLayout.SOUTH);
    }

    /**
     * Muestra una estadistica pequena con fondo oscuro y letra blanca legible.
     */
    private JPanel crearPillStat(String etiqueta, int valor) {
        JPanel pill = new JPanel(new BorderLayout());
        pill.setBackground(new Color(25, 28, 36));
        pill.setBorder(Estilos.crearBordeRelleno(new Color(55, 62, 78), 2, 6));

        JLabel lblE = new JLabel(etiqueta);
        lblE.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblE.setForeground(Estilos.COLOR_NARANJA);
        pill.add(lblE, BorderLayout.WEST);

        JLabel lblV = new JLabel(String.valueOf(valor));
        lblV.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblV.setForeground(Color.WHITE);
        pill.add(lblV, BorderLayout.EAST);

        return pill;
    }

    /**
     * Intenta fichar al jugador comprobando el limite de 3.
     */
    private void gestionarFichaje() {
        if (equipo.estaCompleto()) {
            JOptionPane.showMessageDialog(
                    ventanaMercado,
                    "Tu equipo ya tiene 3 jugadores.\nPara fichar a " + jugador.getNombreCompleto()
                            + ", elimina antes a un jugador en 'Mi Equipo'.",
                    "Equipo completo",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        boolean agregado = equipo.agregarJugador(jugador);
        if (agregado) {
            JOptionPane.showMessageDialog(
                    ventanaMercado,
                    "¡Has fichado a " + jugador.getNombreCompleto() + "!\n"
                            + "Llevas " + equipo.getCantidadJugadores() + " de 3 jugadores.",
                    "Fichaje realizado",
                    JOptionPane.INFORMATION_MESSAGE
            );
            // Actualizamos el mercado para refrescar los datos
            ventanaMercado.actualizarVista();
        }
    }

    /**
     * Muestra la informacion detallada del jugador en un JDialog.
     */
    private void mostrarDialogoDetalles() {
        final JDialog dialogo = new JDialog(ventanaMercado, "Detalles: " + jugador.getNombreCompleto(), true);
        dialogo.setSize(450, 600);
        dialogo.setResizable(false);
        dialogo.setLocationRelativeTo(ventanaMercado);
        dialogo.getContentPane().setBackground(Estilos.COLOR_FONDO_PRINCIPAL);

        JPanel panelContenedor = new JPanel(new BorderLayout(0, 12));
        panelContenedor.setBackground(Estilos.COLOR_FONDO_PRINCIPAL);
        panelContenedor.setBorder(new EmptyBorder(16, 20, 16, 20));

        // Cabecera del dialogo con foto y ficha tecnica
        JPanel panelCabecera = new JPanel(new BorderLayout(14, 0));
        panelCabecera.setOpaque(false);

        JLabel lblFotoGrande = new JLabel();
        lblFotoGrande.setPreferredSize(new Dimension(130, 140));
        lblFotoGrande.setHorizontalAlignment(SwingConstants.CENTER);
        Estilos.cargarImagen(jugador.getImagen(), 130, 140, lblFotoGrande);
        panelCabecera.add(lblFotoGrande, BorderLayout.WEST);

        JPanel panelBio = new JPanel();
        panelBio.setLayout(new BoxLayout(panelBio, BoxLayout.Y_AXIS));
        panelBio.setOpaque(false);

        JLabel lblNombreModal = new JLabel(jugador.getNombreCompleto());
        lblNombreModal.setFont(Estilos.FUENTE_TITULO);
        lblNombreModal.setForeground(Color.WHITE);
        panelBio.add(lblNombreModal);

        JLabel lblPosModal = new JLabel(jugador.getPosicion() + " | Dorsal #" + jugador.getNumeroCamiseta());
        lblPosModal.setFont(Estilos.FUENTE_TEXTO_NEGRITA);
        lblPosModal.setForeground(Estilos.COLOR_NARANJA);
        panelBio.add(lblPosModal);

        panelBio.add(Box.createVerticalStrut(4));

        JLabel lblEquipoModal = new JLabel("Equipo: " + jugador.getEquipoHistorico());
        lblEquipoModal.setFont(Estilos.FUENTE_TEXTO);
        lblEquipoModal.setForeground(Estilos.COLOR_TEXTO_GRIS);
        panelBio.add(lblEquipoModal);

        JLabel lblFisico = new JLabel("Altura: " + jugador.getAltura() + " m | Edad: " + jugador.getEdad() + " años");
        lblFisico.setFont(Estilos.FUENTE_TEXTO);
        lblFisico.setForeground(Estilos.COLOR_TEXTO_GRIS);
        panelBio.add(lblFisico);

        JLabel lblValoracion = new JLabel("Valoración: " + jugador.getValoracionGeneral() + " OVR");
        lblValoracion.setFont(Estilos.FUENTE_TEXTO_NEGRITA);
        lblValoracion.setForeground(Estilos.COLOR_DORADO);
        panelBio.add(lblValoracion);

        if (jugador instanceof JugadorLeyenda) {
            JugadorLeyenda leyenda = (JugadorLeyenda) jugador;
            panelBio.add(Box.createVerticalStrut(4));
            JLabel lblEpoca = new JLabel("Época: " + leyenda.getEpoca());
            lblEpoca.setFont(Estilos.FUENTE_PEQUENA);
            lblEpoca.setForeground(new Color(190, 195, 210));
            panelBio.add(lblEpoca);
        }

        panelCabecera.add(panelBio, BorderLayout.CENTER);
        panelContenedor.add(panelCabecera, BorderLayout.NORTH);

        // Estadisticas y distinciones en el centro
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setOpaque(false);

        if (jugador instanceof JugadorLeyenda) {
            JugadorLeyenda ley = (JugadorLeyenda) jugador;
            JPanel panelDist = new JPanel(new BorderLayout());
            panelDist.setBackground(Estilos.COLOR_FONDO_SECUNDARIO);
            panelDist.setBorder(Estilos.crearBordeRelleno(Estilos.COLOR_TARJETA_BORDE, 8, 10));

            JLabel lblTituloDist = new JLabel("PALMARÉS");
            lblTituloDist.setFont(new Font("Segoe UI", Font.BOLD, 11));
            lblTituloDist.setForeground(Estilos.COLOR_DORADO);
            panelDist.add(lblTituloDist, BorderLayout.NORTH);

            JLabel lblTxtDist = new JLabel("<html>" + ley.getDistinciones() + "</html>");
            lblTxtDist.setFont(Estilos.FUENTE_PEQUENA);
            lblTxtDist.setForeground(Color.WHITE);
            panelDist.add(lblTxtDist, BorderLayout.CENTER);

            panelCentral.add(panelDist);
            panelCentral.add(Box.createVerticalStrut(10));
        }

        JLabel lblTituloStats = new JLabel("ESTADÍSTICAS");
        lblTituloStats.setFont(Estilos.FUENTE_SUBTITULO);
        lblTituloStats.setForeground(Color.WHITE);
        panelCentral.add(lblTituloStats);
        panelCentral.add(Box.createVerticalStrut(6));

        // Reutilizamos el panel de estadisticas
        PanelEstadisticasJugador panelStats = new PanelEstadisticasJugador(jugador);
        panelCentral.add(panelStats);

        panelContenedor.add(panelCentral, BorderLayout.CENTER);

        // Boton para cerrar la ventana modal
        JPanel panelCerrar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelCerrar.setOpaque(false);
        JButton btnCerrar = Estilos.crearBotonSecundario("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogo.dispose();
            }
        });
        panelCerrar.add(btnCerrar);
        panelContenedor.add(panelCerrar, BorderLayout.SOUTH);

        dialogo.add(panelContenedor);
        dialogo.setVisible(true);
    }
}
