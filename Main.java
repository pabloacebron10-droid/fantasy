package lib;

import javax.swing.SwingUtilities;
import lib.gui.VentanaInicio;
import lib.modelo.EquipoFantasy;
import lib.modelo.RepositorioJugadores;

/**
 * Clase principal que arranca el programa.
 */
public class Main {

    public static void main(String[] args) {
        // Lanzamos la interfaz grafica de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Creamos los datos del juego
                RepositorioJugadores repositorio = new RepositorioJugadores();
                EquipoFantasy equipo = new EquipoFantasy();

                // Abrimos la primera pantalla pasandole el repositorio y el equipo
                VentanaInicio ventanaInicio = new VentanaInicio(repositorio, equipo);
                ventanaInicio.setVisible(true);
            }
        });
    }
}