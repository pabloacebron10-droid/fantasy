package lib.modelo;

import java.util.ArrayList;

/**
 * Almacen en memoria con las 12 leyendas de la NBA disponibles para fichar.
 */
public class RepositorioJugadores {

    private ArrayList<Jugador> listaJugadores;

    public RepositorioJugadores() {
        this.listaJugadores = new ArrayList<Jugador>();
        cargarColeccionLeyendas();
    }

    /**
     * Inicializamos las leyendas con sus datos historicos e imagenes.
     */
    private void cargarColeccionLeyendas() {
        // Michael Jordan - Chicago Bulls (imagen local)
        listaJugadores.add(new JugadorLeyenda(
                "Michael", "Jordan", "Chicago Bulls", "Escolta",
                23, 35, 1.98, "/images/jordan.jpg",
                99, 95, 82, 88, 98,
                "Años 90 (1984-2003)",
                "6x Campeón NBA, 5x MVP Temporada, 6x MVP Finales, 10x Máximo Anotador"
        ));

        // Kobe Bryant - Los Angeles Lakers (imagen local)
        listaJugadores.add(new JugadorLeyenda(
                "Kobe", "Bryant", "Los Angeles Lakers", "Escolta",
                24, 34, 1.98, "/images/kobee.jpg",
                98, 92, 80, 85, 96,
                "Años 2000 (1996-2016)",
                "5x Campeón NBA, 1x MVP Temporada, 2x MVP Finales, 18x All-Star, Mamba Mentality"
        ));

        // LeBron James - Los Angeles Lakers (imagen local)
        listaJugadores.add(new JugadorLeyenda(
                "LeBron", "James", "Los Angeles Lakers", "Alero",
                23, 38, 2.06, "/images/lebron.jpg",
                97, 88, 92, 97, 92,
                "2000s - Presente (2003-act.)",
                "4x Campeón NBA, 4x MVP Temporada, 4x MVP Finales, Máximo anotador histórico NBA"
        ));

        // Stephen Curry - Golden State Warriors (imagen local)
        listaJugadores.add(new JugadorLeyenda(
                "Stephen", "Curry", "Golden State Warriors", "Base",
                30, 35, 1.88, "/images/curry.jpg",
                96, 75, 72, 94, 99,
                "Años 2010 (2009-act.)",
                "4x Campeón NBA, 2x MVP (1x Unánime), 1x MVP Finales, Récord histórico de triples"
        ));

        // Magic Johnson - Los Angeles Lakers (imagen local)
        listaJugadores.add(new JugadorLeyenda(
                "Magic", "Johnson", "Los Angeles Lakers", "Base",
                32, 32, 2.06, "/images/magic.jpg",
                92, 82, 88, 99, 86,
                "Años 80 (1979-1996)",
                "5x Campeón NBA, 3x MVP Temporada, 3x MVP Finales, Líder histórico Showtime"
        ));

        // Larry Bird - Boston Celtics (imagen local)
        listaJugadores.add(new JugadorLeyenda(
                "Larry", "Bird", "Boston Celtics", "Alero",
                33, 34, 2.06, "/images/bird_larry.jpg",
                94, 85, 90, 93, 97,
                "Años 80 (1979-1992)",
                "3x Campeón NBA, 3x MVP Consecutivos, 2x MVP Finales, Leyenda del tiro exterior"
        ));

        // Shaquille O'Neal - Los Angeles Lakers / Orlando Magic (imagen local)
        listaJugadores.add(new JugadorLeyenda(
                "Shaquille", "O'Neal", "Los Angeles Lakers", "Pívot",
                34, 30, 2.16, "/images/shaq.jpg",
                99, 90, 98, 68, 62,
                "Años 90 - 2000 (1992-2011)",
                "4x Campeón NBA, 1x MVP Temporada, 3x MVP Finales, El pívot más dominante"
        ));

        // Allen Iverson - Philadelphia 76ers (imagen remota por URL web)
        listaJugadores.add(new JugadorLeyenda(
                "Allen", "Iverson", "Philadelphia 76ers", "Base",
                3, 29, 1.83, "https://share.google/BSyf9TqlwhsMOA1Ls",
                96, 80, 65, 87, 92,
                "Años 2000 (1996-2010)",
                "1x MVP Temporada, 4x Máximo Anotador NBA, 11x All-Star, Icono cultural 'The Answer'"
        ));

        // Tim Duncan - San Antonio Spurs (imagen local)
        listaJugadores.add(new JugadorLeyenda(
                "Tim", "Duncan", "San Antonio Spurs", "Ala-Pívot",
                21, 35, 2.11, "/images/duncan.jpg",
                92, 97, 96, 80, 85,
                "Años 2000 (1997-2016)",
                "5x Campeón NBA, 2x MVP Temporada, 3x MVP Finales, 'The Big Fundamental'"
        ));

        // Kevin Durant - Golden State Warriors / OKC (imagen remota por URL web)
        listaJugadores.add(new JugadorLeyenda(
                "Kevin", "Durant", "Golden State Warriors", "Alero",
                35, 33, 2.08, "https://share.google/487dDt3Uo8yKHSvAy",
                97, 85, 84, 86, 98,
                "Años 2010 (2007-act.)",
                "2x Campeón NBA, 1x MVP Temporada, 2x MVP Finales, 4x Máximo Anotador NBA"
        ));

        // Kareem Abdul-Jabbar - Los Angeles Lakers / Bucks (imagen remota por URL web)
        listaJugadores.add(new JugadorLeyenda(
                "Kareem", "Abdul-Jabbar", "Los Angeles Lakers", "Pívot",
                33, 38, 2.18, "https://share.google/AmHgsaeClUSvBXuay",
                98, 93, 97, 76, 88,
                "Años 70 - 80 (1969-1989)",
                "6x Campeón NBA, 6x MVP Temporada (Récord), Creador del 'Skyhook' imparable"
        ));

        // Wilt Chamberlain - Philadelphia 76ers / Lakers (imagen local)
        listaJugadores.add(new JugadorLeyenda(
                "Wilt", "Chamberlain", "Philadelphia 76ers", "Pívot",
                13, 32, 2.16, "/images/Wilt_Chamberlain.jpg",
                99, 93, 99, 72, 75,
                "Años 60 (1959-1973)",
                "2x Campeón NBA, 4x MVP Temporada, Récord histórico de 100 puntos en un partido"
        ));
    }

    public ArrayList<Jugador> getTodosLosJugadores() {
        return listaJugadores;
    }

    /**
     * Filtra los jugadores por su posicion (Base, Escolta, Alero, Ala-Pivot, Pivot).
     */
    public ArrayList<Jugador> filtrarPorPosicion(String posicion) {
        if (posicion == null || posicion.equalsIgnoreCase("TODOS")) {
            return listaJugadores;
        }

        ArrayList<Jugador> filtrados = new ArrayList<Jugador>();
        for (Jugador j : listaJugadores) {
            if (j.getPosicion().equalsIgnoreCase(posicion)) {
                filtrados.add(j);
            }
        }
        return filtrados;
    }
}
