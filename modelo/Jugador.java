package lib.modelo;

/**
 * Representa a un jugador de baloncesto con sus datos y estadisticas.
 */
public class Jugador {

    // Atributos del jugador
    private String nombre;
    private String apellido;
    private String equipoHistorico;
    private String posicion;
    private int numeroCamiseta;
    private int edad;
    private double altura;
    private String imagen;
    private int ataque;
    private int defensa;
    private int rebote;
    private int pase;
    private int tiro;

    public Jugador(String nombre, String apellido, String equipoHistorico, String posicion,
                   int numeroCamiseta, int edad, double altura, String imagen,
                   int ataque, int defensa, int rebote, int pase, int tiro) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.equipoHistorico = equipoHistorico;
        this.posicion = posicion;
        this.numeroCamiseta = numeroCamiseta;
        this.edad = edad;
        this.altura = altura;
        this.imagen = imagen;
        this.ataque = ataque;
        this.defensa = defensa;
        this.rebote = rebote;
        this.pase = pase;
        this.tiro = tiro;
    }

    /**
     * Calcula la valoracion media del jugador entre 0 y 100.
     */
    public int getValoracionGeneral() {
        return (ataque + defensa + rebote + pase + tiro) / 5;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEquipoHistorico() {
        return equipoHistorico;
    }

    public String getPosicion() {
        return posicion;
    }

    public int getNumeroCamiseta() {
        return numeroCamiseta;
    }

    public int getEdad() {
        return edad;
    }

    public double getAltura() {
        return altura;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getRebote() {
        return rebote;
    }

    public int getPase() {
        return pase;
    }

    public int getTiro() {
        return tiro;
    }

    @Override
    public String toString() {
        return getNombreCompleto() + " (" + posicion + " - " + getValoracionGeneral() + " OVR)";
    }
}
