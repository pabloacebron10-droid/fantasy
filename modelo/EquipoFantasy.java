package lib.modelo;

import java.util.ArrayList;

/**
 * Gestiona el equipo Fantasy del usuario (maximo 3 jugadores).
 */
public class EquipoFantasy {

    public static final int CAPACIDAD_MAXIMA = 3;

    private ArrayList<Jugador> jugadores;

    public EquipoFantasy() {
        this.jugadores = new ArrayList<Jugador>();
    }

    /**
     * Comprueba si el jugador ya esta en la plantilla.
     */
    public boolean contieneJugador(Jugador jugador) {
        if (jugador == null) {
            return false;
        }
        for (Jugador j : jugadores) {
            if (j.getNombreCompleto().equalsIgnoreCase(jugador.getNombreCompleto())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Anade un jugador si hay espacio y no esta repetido.
     */
    public boolean agregarJugador(Jugador jugador) {
        if (jugador == null) {
            return false;
        }
        if (estaCompleto()) {
            return false;
        }
        if (contieneJugador(jugador)) {
            return false;
        }
        jugadores.add(jugador);
        return true;
    }

    /**
     * Elimina a un jugador de la plantilla.
     */
    public boolean eliminarJugador(Jugador jugador) {
        if (jugador == null) {
            return false;
        }
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador j = jugadores.get(i);
            if (j.getNombreCompleto().equalsIgnoreCase(jugador.getNombreCompleto())) {
                jugadores.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public boolean estaCompleto() {
        return jugadores.size() >= CAPACIDAD_MAXIMA;
    }

    public int getCantidadJugadores() {
        return jugadores.size();
    }

    public int getCapacidadMaxima() {
        return CAPACIDAD_MAXIMA;
    }

    /**
     * Calcula la valoracion media del equipo.
     */
    public double calcularValoracionMedia() {
        if (jugadores.isEmpty()) {
            return 0.0;
        }
        double sumaValoraciones = 0.0;
        for (Jugador j : jugadores) {
            sumaValoraciones = sumaValoraciones + j.getValoracionGeneral();
        }
        double media = sumaValoraciones / jugadores.size();
        return Math.round(media * 10.0) / 10.0;
    }

    public void vaciarEquipo() {
        jugadores.clear();
    }
}
