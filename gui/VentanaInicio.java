package lib.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import lib.modelo.EquipoFantasy;
import lib.modelo.RepositorioJugadores;

/**
 * Pantalla de bienvenida del juego.
 */
public class VentanaInicio extends JFrame {

    private RepositorioJugadores repositorio;
    private EquipoFantasy equipo;

    public VentanaInicio(RepositorioJugadores repositorio, EquipoFantasy equipo) {
        this.repositorio = repositorio;
        this.equipo = equipo;

        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Legends Fantasy - Build Your 3");
        setSize(850, 620);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Estilos.COLOR_FONDO_PRINCIPAL);
        setLayout(new BorderLayout(0, 16));
    }

    private void inicializarComponentes() {
        // Titulo y subtitulo
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));
        panelSuperior.setOpaque(false);
        panelSuperior.setBorder(new EmptyBorder(25, 20, 10, 20));

        JLabel lblTitulo = new JLabel("LEGENDS FANTASY");
        lblTitulo.setFont(Estilos.FUENTE_TITULO_GRANDE);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelSuperior.add(lblTitulo);

        panelSuperior.add(Box.createVerticalStrut(6));

        JLabel lblSubtitulo = new JLabel("BUILD YOUR 3 · THE ULTIMATE NBA TRIO");
        lblSubtitulo.setFont(Estilos.FUENTE_SUBTITULO);
        lblSubtitulo.setForeground(Estilos.COLOR_NARANJA);
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelSuperior.add(lblSubtitulo);

        add(panelSuperior, BorderLayout.NORTH);

        // Imagen central y descripcion
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setOpaque(false);
        panelCentral.setBorder(new EmptyBorder(0, 40, 0, 40));

        // Imagen destacada cargada de resources
        JLabel lblHero = new JLabel();
        lblHero.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblHero.setPreferredSize(new Dimension(740, 240));
        lblHero.setMaximumSize(new Dimension(740, 240));
        lblHero.setBorder(Estilos.crearBordeRelleno(Estilos.COLOR_TARJETA_BORDE, 2, 2));
        Estilos.cargarImagen("/images/hero_basketball.png", 740, 240, lblHero);
        panelCentral.add(lblHero);

        panelCentral.add(Box.createVerticalStrut(20));

        // Tres tarjetas resumen con las instrucciones
        JPanel panelReglas = new JPanel(new GridLayout(1, 3, 16, 0));
        panelReglas.setOpaque(false);
        panelReglas.setMaximumSize(new Dimension(740, 85));

        panelReglas.add(crearCajaCaracteristica("1. EXPLORA EL MERCADO", "Consulta las estadisticas reales de las 12 mayores leyendas de la NBA."));
        panelReglas.add(crearCajaCaracteristica("2. FICHA TU TRÍO", "Selecciona exactamente 3 jugadores para formar tu equipo definitivo."));
        panelReglas.add(crearCajaCaracteristica("3. GESTIONA TU EQUIPO", "Compara posiciones, maximiza la valoracion media y compite."));

        panelCentral.add(panelReglas);

        add(panelCentral, BorderLayout.CENTER);

        // Boton para entrar al mercado
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 25));
        panelInferior.setOpaque(false);

        JButton btnEntrar = Estilos.crearBotonPrincipal("ENTRAR AL MERCADO");
        btnEntrar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnEntrar.setPreferredSize(new Dimension(340, 48));
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrimos el mercado y cerramos la pantalla de inicio
                VentanaMercado mercado = new VentanaMercado(repositorio, equipo);
                mercado.setVisible(true);
                dispose();
            }
        });
        panelInferior.add(btnEntrar);

        add(panelInferior, BorderLayout.SOUTH);
    }

    private JPanel crearCajaCaracteristica(String titulo, String descripcion) {
        JPanel caja = new JPanel(new BorderLayout(0, 4));
        caja.setBackground(Estilos.COLOR_TARJETA);
        caja.setBorder(Estilos.crearBordeRelleno(Estilos.COLOR_TARJETA_BORDE, 8, 10));

        JLabel lblTit = new JLabel(titulo);
        lblTit.setFont(Estilos.FUENTE_TEXTO_NEGRITA);
        lblTit.setForeground(Estilos.COLOR_NARANJA);
        caja.add(lblTit, BorderLayout.NORTH);

        JLabel lblDesc = new JLabel("<html>" + descripcion + "</html>");
        lblDesc.setFont(Estilos.FUENTE_PEQUENA);
        lblDesc.setForeground(new Color(220, 225, 235));
        caja.add(lblDesc, BorderLayout.CENTER);

        return caja;
    }
}
