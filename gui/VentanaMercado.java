package lib.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import lib.modelo.EquipoFantasy;
import lib.modelo.Jugador;
import lib.modelo.RepositorioJugadores;

/**
 * Pantalla del mercado donde se consultan y fichan las leyendas de la NBA.
 */
public class VentanaMercado extends JFrame {

    private RepositorioJugadores repositorio;
    private EquipoFantasy equipo;

    private String posicionFiltroActual = "TODOS";
    private JPanel panelTarjetas;
    private JLabel lblContadorEquipo;
    private JLabel lblMediaEquipo;
    private JButton btnVerEquipo;
    private JPanel panelFiltros;

    public VentanaMercado(RepositorioJugadores repositorio, EquipoFantasy equipo) {
        this.repositorio = repositorio;
        this.equipo = equipo;

        configurarVentana();
        inicializarComponentes();
        cargarTarjetas();
        actualizarIndicadoresEquipo();
    }

    private void configurarVentana() {
        setTitle("Legends Fantasy - Mercado de Leyendas NBA");
        setSize(1100, 750);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Estilos.COLOR_FONDO_PRINCIPAL);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        // Cabecera superior
        JPanel panelSuperior = new JPanel(new BorderLayout(20, 0));
        panelSuperior.setBackground(Estilos.COLOR_FONDO_SECUNDARIO);
        panelSuperior.setBorder(new EmptyBorder(16, 24, 16, 24));

        // Titulos
        JPanel panelTitulos = new JPanel();
        panelTitulos.setLayout(new BoxLayout(panelTitulos, BoxLayout.Y_AXIS));
        panelTitulos.setOpaque(false);

        JLabel lblTitulo = new JLabel("MERCADO DE LEYENDAS");
        lblTitulo.setFont(Estilos.FUENTE_TITULO);
        lblTitulo.setForeground(Color.WHITE);
        panelTitulos.add(lblTitulo);

        JLabel lblSub = new JLabel("Selecciona tus 3 leyendas favoritas para formar el equipo definitivo");
        lblSub.setFont(Estilos.FUENTE_TEXTO);
        lblSub.setForeground(new Color(210, 215, 230));
        panelTitulos.add(lblSub);

        panelSuperior.add(panelTitulos, BorderLayout.WEST);

        // Bloque derecho con el estado del equipo y boton para ir a la plantilla
        JPanel panelResumenDerecho = new JPanel(new FlowLayout(FlowLayout.RIGHT, 14, 0));
        panelResumenDerecho.setOpaque(false);

        JPanel panelInfoPlantilla = new JPanel();
        panelInfoPlantilla.setLayout(new BoxLayout(panelInfoPlantilla, BoxLayout.Y_AXIS));
        panelInfoPlantilla.setOpaque(false);

        lblContadorEquipo = new JLabel("PLANTILLA: 0 / 3");
        lblContadorEquipo.setFont(Estilos.FUENTE_TEXTO_NEGRITA);
        lblContadorEquipo.setForeground(Estilos.COLOR_NARANJA);
        lblContadorEquipo.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panelInfoPlantilla.add(lblContadorEquipo);

        lblMediaEquipo = new JLabel("MEDIA: 0.0 OVR");
        lblMediaEquipo.setFont(Estilos.FUENTE_TEXTO_NEGRITA);
        lblMediaEquipo.setForeground(new Color(235, 240, 250));
        lblMediaEquipo.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panelInfoPlantilla.add(lblMediaEquipo);

        panelResumenDerecho.add(panelInfoPlantilla);

        // Boton para ver la ventana del equipo
        btnVerEquipo = Estilos.crearBotonPrincipal("MI EQUIPO (0/3)");
        btnVerEquipo.setPreferredSize(new Dimension(185, 42));
        btnVerEquipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrimos el equipo y cerramos el mercado
                VentanaEquipo ventanaEq = new VentanaEquipo(repositorio, equipo);
                ventanaEq.setVisible(true);
                dispose();
            }
        });
        panelResumenDerecho.add(btnVerEquipo);

        panelSuperior.add(panelResumenDerecho, BorderLayout.EAST);

        // Barra con los botones de filtro por posicion
        panelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelFiltros.setBackground(Estilos.COLOR_FONDO_PRINCIPAL);
        panelFiltros.setBorder(new EmptyBorder(6, 20, 6, 20));

        JLabel lblFiltroTag = new JLabel("POSICIÓN:");
        lblFiltroTag.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblFiltroTag.setForeground(Estilos.COLOR_NARANJA);
        panelFiltros.add(lblFiltroTag);

        agregarBotonFiltro("TODOS");
        agregarBotonFiltro("BASE");
        agregarBotonFiltro("ESCOLTA");
        agregarBotonFiltro("ALERO");
        agregarBotonFiltro("ALA-PÍVOT");
        agregarBotonFiltro("PÍVOT");

        JPanel panelCabeceraCompleta = new JPanel(new BorderLayout());
        panelCabeceraCompleta.add(panelSuperior, BorderLayout.NORTH);
        panelCabeceraCompleta.add(panelFiltros, BorderLayout.SOUTH);

        add(panelCabeceraCompleta, BorderLayout.NORTH);

        // Panel con scroll para colocar las tarjetas de los jugadores
        panelTarjetas = new JPanel();
        panelTarjetas.setBackground(Estilos.COLOR_FONDO_PRINCIPAL);
        panelTarjetas.setLayout(new FlowLayout(FlowLayout.CENTER, 18, 18));

        JScrollPane scroll = new JScrollPane(panelTarjetas);
        scroll.setBorder(null);
        scroll.setBackground(Estilos.COLOR_FONDO_PRINCIPAL);
        scroll.getViewport().setBackground(Estilos.COLOR_FONDO_PRINCIPAL);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        add(scroll, BorderLayout.CENTER);
    }

    private void agregarBotonFiltro(final String posicion) {
        boolean activo = posicion.equalsIgnoreCase(posicionFiltroActual);
        JButton btn = Estilos.crearBotonFiltro(posicion, activo);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                posicionFiltroActual = posicion;
                refrescarBotonesFiltro();
                cargarTarjetas();
            }
        });
        panelFiltros.add(btn);
    }

    private void refrescarBotonesFiltro() {
        panelFiltros.removeAll();
        JLabel lblFiltroTag = new JLabel("POSICIÓN:");
        lblFiltroTag.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblFiltroTag.setForeground(Estilos.COLOR_NARANJA);
        panelFiltros.add(lblFiltroTag);

        agregarBotonFiltro("TODOS");
        agregarBotonFiltro("BASE");
        agregarBotonFiltro("ESCOLTA");
        agregarBotonFiltro("ALERO");
        agregarBotonFiltro("ALA-PÍVOT");
        agregarBotonFiltro("PÍVOT");

        panelFiltros.revalidate();
        panelFiltros.repaint();
    }

    /**
     * Dibuja las tarjetas de los jugadores segun el filtro activo.
     */
    private void cargarTarjetas() {
        panelTarjetas.removeAll();

        ArrayList<Jugador> jugadores = repositorio.filtrarPorPosicion(posicionFiltroActual);
        for (Jugador j : jugadores) {
            TarjetaJugador tarjeta = new TarjetaJugador(j, equipo, this);
            panelTarjetas.add(tarjeta);
        }

        if (jugadores.isEmpty()) {
            JLabel lblVacio = new JLabel("No hay jugadores disponibles para esta posición.");
            lblVacio.setFont(Estilos.FUENTE_TEXTO);
            lblVacio.setForeground(Color.WHITE);
            lblVacio.setBorder(new EmptyBorder(40, 0, 0, 0));
            panelTarjetas.add(lblVacio);
        }

        panelTarjetas.revalidate();
        panelTarjetas.repaint();
    }

    /**
     * Actualiza el contador de jugadores y la valoracion media del equipo.
     */
    private void actualizarIndicadoresEquipo() {
        int cantidad = equipo.getCantidadJugadores();
        int maximo = equipo.getCapacidadMaxima();
        double media = equipo.calcularValoracionMedia();

        lblContadorEquipo.setText("PLANTILLA: " + cantidad + " / " + maximo);
        if (cantidad > 0) {
            lblMediaEquipo.setText("VALORACIÓN: " + media + " OVR");
        } else {
            lblMediaEquipo.setText("VALORACIÓN: ---");
        }

        btnVerEquipo.setText("MI EQUIPO (" + cantidad + "/" + maximo + ")");
        if (equipo.estaCompleto()) {
            btnVerEquipo.setBackground(new Color(35, 145, 65));
        } else {
            btnVerEquipo.setBackground(Estilos.COLOR_NARANJA);
        }
    }

    public void actualizarVista() {
        actualizarIndicadoresEquipo();
        cargarTarjetas();
    }
}
