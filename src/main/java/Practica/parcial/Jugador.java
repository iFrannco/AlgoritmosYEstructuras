package Practica.parcial;

public class Jugador implements Comparable<Jugador> {

    private String nombre;
    private String pais;
    private int goles;

    public Jugador(String nombre, int goles, String pais) {
        this.nombre = nombre;
        this.goles = goles;
        this.pais = pais;
    }

    public int getGoles() {
        return this.goles;
    }

    public String getPais() {
        return this.pais;
    }

    @Override
    public int compareTo(Jugador o) {
        return Integer.compare(this.goles, o.goles);
    }
}
