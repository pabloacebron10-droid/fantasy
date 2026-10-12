package lib.modelo;

/**
 * Subclase que representa a una leyenda historica de la NBA.
 * Hereda de la clase Jugador y anade su epoca y distinciones.
 */
public class JugadorLeyenda extends Jugador {

    // Atributos especificos de la leyenda
    private String epoca;
    private String distinciones;

    public JugadorLeyenda(String nombre, String apellido, String equipoHistorico, String posicion,
                          int numeroCamiseta, int edad, double altura, String imagen,
                          int ataque, int defensa, int rebote, int pase, int tiro,
                          String epoca, String distinciones) {
        super(nombre, apellido, equipoHistorico, posicion, numeroCamiseta, edad, altura, imagen,
              ataque, defensa, rebote, pase, tiro);
        this.epoca = epoca;
        this.distinciones = distinciones;
    }

    public String getEpoca() {
        return epoca;
    }

    public String getDistinciones() {
        return distinciones;
    }
}
