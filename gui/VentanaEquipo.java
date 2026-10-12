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
 * Pantalla que muestra el equipo del usuario con sus 3 espacios disponibles.
 */
public class VentanaEquipo extends JFrame {

    private RepositorioJugadores repositorio;
    private EquipoFantasy equipo;

    private JLabel lblProgresoPlantilla;
    private JLabel lblMediaValoracion;
    private JLabel lblEstadoEquipo;
    private JPanel panelSlots;

    public VentanaEquipo(RepositorioJugadores repositorio, EquipoFantasy equipo) {
        this.repositorio = repositorio;
        this.equipo = equipo;

        configurarVentana();
        inicializarComponentes();
        actualizarVista();
    }

    private void configurarVentana() {
        setTitle("Legends Fantasy - Mi Trío Legendario");
        setSize(1050, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Estilos.COLOR_FONDO_PRINCIPAL);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        // Cabecera con datos del equipo
        JPanel panelSuperior = new JPanel(new BorderLayout(20, 0));
        panelSuperior.setBackground(Estilos.COLOR_FONDO_SECUNDARIO);
        panelSuperior.setBorder(new EmptyBorder(16, 26, 16, 26));

        JPanel panelTitulos = new JPanel();
        panelTitulos.setLayout(new BoxLayout(panelTitulos, BoxLayout.Y_AXIS));
        panelTitulos.setOpaque(false);

        JLabel lblTitulo = new JLabel("MI EQUIPO FANTASY 3 VS 3");
        lblTitulo.setFont(Estilos.FUENTE_TITULO);
        lblTitulo.setForeground(Color.WHITE);
        panelTitulos.add(lblTitulo);

        JLabel lblSub = new JLabel("Plantilla oficial: exactamente 3 leyendas de la historia del baloncesto");
        lblSub.setFont(Estilos.FUENTE_TEXTO);
        lblSub.setForeground(new Color(210, 215, 230));
        panelTitulos.add(lblSub);

        panelSuperior.add(panelTitulos, BorderLayout.WEST);

        // Indicadores a la derecha
        JPanel panelMetricas = new JPanel(new FlowLayout(FlowLayout.RIGHT, 14, 0));
        panelMetricas.setOpaque(false);

        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
        panelDatos.setOpaque(false);

        lblProgresoPlantilla = new JLabel("PLANTILLA: 0 / 3");
        lblProgresoPlantilla.setFont(Estilos.FUENTE_TEXTO_NEGRITA);
        lblProgresoPlantilla.setForeground(Estilos.COLOR_NARANJA);
        lblProgresoPlantilla.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panelDatos.add(lblProgresoPlantilla);

        lblMediaValoracion = new JLabel("MEDIA: 0.0 OVR");
        lblMediaValoracion.setFont(Estilos.FUENTE_VALORACION);
        lblMediaValoracion.setForeground(Estilos.COLOR_DORADO);
        lblMediaValoracion.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panelDatos.add(lblMediaValoracion);

        panelMetricas.add(panelDatos);

        lblEstadoEquipo = new JLabel("INCOMPLETO");
        lblEstadoEquipo.setFont(Estilos.FUENTE_BOTON);
        lblEstadoEquipo.setForeground(Color.WHITE);
        lblEstadoEquipo.setOpaque(true);
        lblEstadoEquipo.setBackground(Estilos.COLOR_ROJO);
        lblEstadoEquipo.setBorder(new EmptyBorder(8, 12, 8, 12));
        panelMetricas.add(lblEstadoEquipo);

        panelSuperior.add(panelMetricas, BorderLayout.EAST);
        add(panelSuperior, BorderLayout.NORTH);

        // Panel con los 3 huecos para jugadores
        panelSlots = new JPanel(new GridLayout(1, 3, 20, 0));
        panelSlots.setBackground(Estilos.COLOR_FONDO_PRINCIPAL);
        panelSlots.setBorder(new EmptyBorder(24, 26, 24, 26));
        add(panelSlots, BorderLayout.CENTER);

        // Barra inferior con los botones
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 18, 16));
        panelInferior.setBackground(Estilos.COLOR_FONDO_SECUNDARIO);
        panelInferior.setBorder(new EmptyBorder(6, 20, 6, 20));

        // Boton para volver al mercado
        JButton btnVolverMercado = Estilos.crearBotonPrincipal("VOLVER AL MERCADO");
        btnVolverMercado.setPreferredSize(new Dimension(220, 42));
        btnVolverMercado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaMercado mercado = new VentanaMercado(repositorio, equipo);
                mercado.setVisible(true);
                dispose();
            }
        });
        panelInferior.add(btnVolverMercado);

        // Boton para vaciar el equipo
        JButton btnVaciar = Estilos.crearBotonSecundario("VACIAR EQUIPO");
        btnVaciar.setPreferredSize(new Dimension(160, 42));
        btnVaciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (equipo.getCantidadJugadores() == 0) {
                    JOptionPane.showMessageDialog(VentanaEquipo.this,
                            "El equipo ya se encuentra vacío.",
                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                int confirmacion = JOptionPane.showConfirmDialog(VentanaEquipo.this,
                        "¿Estás seguro de que deseas eliminar todas las leyendas de tu plantilla?",
                        "Confirmar vaciado", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    equipo.vaciarEquipo();
                    actualizarVista();
                }
            }
        });
        panelInferior.add(btnVaciar);

        // Boton para evaluar el trio
        JButton btnEvaluar = Estilos.crearBotonSecundario("EVALUAR QUIMICA");
        btnEvaluar.setPreferredSize(new Dimension(180, 42));
        btnEvaluar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarEvaluacionEquipo();
            }
        });
        panelInferior.add(btnEvaluar);

        add(panelInferior, BorderLayout.SOUTH);
    }

    /**
     * Dibuja los 3 huecos del equipo (ocupados o vacios).
     */
    public void actualizarVista() {
        panelSlots.removeAll();

        ArrayList<Jugador> jugadores = equipo.getJugadores();
        int totalJugadores = jugadores.size();
        int maximo = equipo.getCapacidadMaxima();

        for (int i = 0; i < maximo; i++) {
            if (i < totalJugadores) {
                Jugador j = jugadores.get(i);
                panelSlots.add(crearSlotOcupado(j, i + 1));
            } else {
                panelSlots.add(crearSlotVacio(i + 1));
            }
        }

        lblProgresoPlantilla.setText("PLANTILLA: " + totalJugadores + " / " + maximo);
        if (totalJugadores > 0) {
            lblMediaValoracion.setText(equipo.calcularValoracionMedia() + " OVR");
        } else {
            lblMediaValoracion.setText("--- OVR");
        }

        if (equipo.estaCompleto()) {
            lblEstadoEquipo.setText("EQUIPO COMPLETO");
            lblEstadoEquipo.setBackground(new Color(35, 145, 65));
        } else {
            int faltantes = maximo - totalJugadores;
            lblEstadoEquipo.setText("FALTAN " + faltantes + " JUGADORES");
            lblEstadoEquipo.setBackground(Estilos.COLOR_NARANJA);
        }

        panelSlots.revalidate();
        panelSlots.repaint();
    }

    /**
     * Tarjeta de un jugador que ya forma parte del equipo.
     */
    private JPanel crearSlotOcupado(final Jugador jugador, int numeroSlot) {
        JPanel tarjeta = new JPanel(new BorderLayout(0, 8));
        tarjeta.setBackground(Estilos.COLOR_TARJETA);
        tarjeta.setBorder(Estilos.crearBordeRelleno(Estilos.COLOR_TARJETA_BORDE, 12, 12));

        // Cabecera del hueco
        JPanel cabecera = new JPanel(new BorderLayout());
        cabecera.setOpaque(false);

        JLabel lblSlotTag = new JLabel("TITULAR #" + numeroSlot);
        lblSlotTag.setFont(Estilos.FUENTE_PEQUENA);
        lblSlotTag.setForeground(Estilos.COLOR_NARANJA);
        cabecera.add(lblSlotTag, BorderLayout.WEST);

        JLabel lblOvr = new JLabel(jugador.getValoracionGeneral() + " OVR");
        lblOvr.setFont(Estilos.FUENTE_TEXTO_NEGRITA);
        lblOvr.setForeground(Estilos.COLOR_DORADO);
        cabecera.add(lblOvr, BorderLayout.EAST);

        tarjeta.add(cabecera, BorderLayout.NORTH);

        // Centro con foto, nombre y barras
        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setOpaque(false);

        JLabel lblFoto = new JLabel();
        lblFoto.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblFoto.setPreferredSize(new Dimension(170, 150));
        lblFoto.setMaximumSize(new Dimension(170, 150));
        lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
        Estilos.cargarImagen(jugador.getImagen(), 170, 150, lblFoto);
        centro.add(lblFoto);

        centro.add(Box.createVerticalStrut(6));

        JLabel lblNombre = new JLabel(jugador.getNombreCompleto());
        lblNombre.setFont(Estilos.FUENTE_SUBTITULO);
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(lblNombre);

        JLabel lblPos = new JLabel(jugador.getPosicion() + " · " + jugador.getEquipoHistorico());
        lblPos.setFont(Estilos.FUENTE_PEQUENA);
        lblPos.setForeground(Estilos.COLOR_TEXTO_GRIS);
        lblPos.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(lblPos);

        centro.add(Box.createVerticalStrut(10));

        // Panel de estadisticas reutilizado
        PanelEstadisticasJugador panelStats = new PanelEstadisticasJugador(jugador);
        centro.add(panelStats);

        tarjeta.add(centro, BorderLayout.CENTER);

        // Boton para quitar el jugador de la plantilla
        JButton btnEliminar = Estilos.crearBotonPeligro("Eliminar del equipo");
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equipo.eliminarJugador(jugador);
                JOptionPane.showMessageDialog(VentanaEquipo.this,
                        jugador.getNombreCompleto() + " ha sido eliminado de tu plantilla.",
                        "Jugador eliminado", JOptionPane.INFORMATION_MESSAGE);
                actualizarVista();
            }
        });
        tarjeta.add(btnEliminar, BorderLayout.SOUTH);

        return tarjeta;
    }

    /**
     * Hueco vacio cuando todavia no se han elegido los 3 jugadores.
     */
    private JPanel crearSlotVacio(int numeroSlot) {
        JPanel vacio = new JPanel(new BorderLayout());
        vacio.setBackground(new Color(24, 26, 32));
        vacio.setBorder(BorderFactory.createDashedBorder(Estilos.COLOR_TARJETA_BORDE, 4, 4));

        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setOpaque(false);
        contenido.setBorder(new EmptyBorder(60, 20, 60, 20));

        JLabel lblSlot = new JLabel("ESPACIO VACÍO #" + numeroSlot);
        lblSlot.setFont(Estilos.FUENTE_SUBTITULO);
        lblSlot.setForeground(new Color(220, 225, 235));
        lblSlot.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenido.add(lblSlot);

        contenido.add(Box.createVerticalStrut(20));

        JLabel lblIcono = new JLabel("+");
        lblIcono.setFont(new Font("Segoe UI", Font.BOLD, 54));
        lblIcono.setForeground(Estilos.COLOR_NARANJA);
        lblIcono.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenido.add(lblIcono);

        contenido.add(Box.createVerticalStrut(20));

        JLabel lblInstruccion = new JLabel("Accede al mercado para fichar una leyenda.");
        lblInstruccion.setFont(Estilos.FUENTE_PEQUENA);
        lblInstruccion.setForeground(new Color(200, 205, 215));
        lblInstruccion.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenido.add(lblInstruccion);

        contenido.add(Box.createVerticalStrut(15));

        JButton btnFichar = Estilos.crearBotonPrincipal("Fichar Leyenda");
        btnFichar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnFichar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaMercado mercado = new VentanaMercado(repositorio, equipo);
                mercado.setVisible(true);
                dispose();
            }
        });
        contenido.add(btnFichar);

        vacio.add(contenido, BorderLayout.CENTER);
        return vacio;
    }

    /**
     * Dialogo modal que muestra el analisis del trio.
     */
    private void mostrarEvaluacionEquipo() {
        final JDialog dialogo = new JDialog(this, "Informe del Equipo", true);
        dialogo.setSize(430, 360);
        dialogo.setResizable(false);
        dialogo.setLocationRelativeTo(this);
        dialogo.getContentPane().setBackground(Estilos.COLOR_FONDO_PRINCIPAL);

        JPanel panel = new JPanel(new BorderLayout(0, 15));
        panel.setBackground(Estilos.COLOR_FONDO_PRINCIPAL);
        panel.setBorder(new EmptyBorder(20, 24, 20, 24));

        JLabel lblTituloDialogo = new JLabel("ANÁLISIS DEL TRÍO");
        lblTituloDialogo.setFont(Estilos.FUENTE_TITULO);
        lblTituloDialogo.setForeground(Color.WHITE);
        panel.add(lblTituloDialogo, BorderLayout.NORTH);

        JPanel cuerpo = new JPanel();
        cuerpo.setLayout(new BoxLayout(cuerpo, BoxLayout.Y_AXIS));
        cuerpo.setOpaque(false);

        int cantidad = equipo.getCantidadJugadores();
        double media = equipo.calcularValoracionMedia();

        JLabel lblCantidad = new JLabel("- Jugadores seleccionados: " + cantidad + " / 3");
        lblCantidad.setFont(Estilos.FUENTE_TEXTO);
        lblCantidad.setForeground(Color.WHITE);
        cuerpo.add(lblCantidad);
        cuerpo.add(Box.createVerticalStrut(8));

        JLabel lblMedia = new JLabel("- Valoración media: " + media + " OVR");
        lblMedia.setFont(Estilos.FUENTE_TEXTO);
        lblMedia.setForeground(Estilos.COLOR_DORADO);
        cuerpo.add(lblMedia);
        cuerpo.add(Box.createVerticalStrut(8));

        String evaluacion;
        if (cantidad < 3) {
            evaluacion = "El equipo todavía está incompleto. Ficha " + (3 - cantidad) + " leyenda(s) más para poder evaluar su química completa.";
        } else if (media >= 96.0) {
            evaluacion = "¡EQUIPO DE ENSUEÑO! Tu trío tiene un nivel histórico insuperable en todas las facetas del juego.";
        } else if (media >= 93.0) {
            evaluacion = "¡TRÍO CANDIDATO AL ANILLO! Un equipo muy equilibrado y competitivo.";
        } else {
            evaluacion = "¡BUEN TRÍO! Puedes probar combinaciones alternativas para maximizar tu OVR medio.";
        }

        JLabel lblEval = new JLabel("<html><b>Diagnóstico:</b><br>" + evaluacion + "</html>");
        lblEval.setFont(Estilos.FUENTE_TEXTO);
        lblEval.setForeground(new Color(220, 225, 235));
        cuerpo.add(lblEval);

        panel.add(cuerpo, BorderLayout.CENTER);

        JButton btnEntendido = Estilos.crearBotonPrincipal("Aceptar");
        btnEntendido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogo.dispose();
            }
        });
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.setOpaque(false);
        panelBoton.add(btnEntendido);
        panel.add(panelBoton, BorderLayout.SOUTH);

        dialogo.add(panel);
        dialogo.setVisible(true);
    }
}
