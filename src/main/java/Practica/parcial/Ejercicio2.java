package Practica.parcial;

import tp03.estructuras.ArbolBinarioDeBusqueda;

import java.util.HashMap;

public class Ejercicio2 {
    // Creo que interprete mal el enunciado. Lo que habia entendido es que se pedia retornar los paises que mas goles
    // habian realizado en un torneo. Pero creo que lo que se pedia era la nacionalidad con mas jugadores goleadores.
    // Y en caso de empatar, mostrar todas las nacionalidades que tenian el mismo numero de goleadores.


    public HashMap<String, Integer> paisesGoleadores(ArbolBinarioDeBusqueda<Jugador> jugadores) {
        // Creo un mapa para relacionar al pais, con la cantidad de goles.
        HashMap<String, Integer> golesYPaises = new HashMap<>();

        algoritmo(jugadores, golesYPaises);

        // aca faltaria retornar una lista con los paises que mas goles realizaron. En el parcial no llegue a implementarlo.

        return golesYPaises;
    }

    private void algoritmo(ArbolBinarioDeBusqueda<Jugador> jugadores, HashMap<String, Integer> golesYPaises) {
        // En el caso de que la estructura este vacia, no hago nada
        if (!jugadores.esVacio()) {
            // realizo un recorrido en pre orden sobre el arbol
            // pregunto si en mi mapa ya añadi al pais del jugador actual
            // si no lo añadi, lo añado, junto con la cantidad de goles que realizo ese jugador
            // si ya lo añadi, sumo la cantidad de goles actuales, mas la cantidad de goles que realizo el jugador actual.
            // y continuo procesando los hijos con el mismo algoritmo hasta recorrer todo el arbol.
            if (!golesYPaises.containsKey(jugadores.getDato().getPais())) {
                golesYPaises.put(jugadores.getDato().getPais(), jugadores.getDato().getGoles());
            } else {
                String pais = jugadores.getDato().getPais();
                golesYPaises.put(pais, golesYPaises.get(pais) + jugadores.getDato().getGoles());
            }


            if (jugadores.tieneHijoIzquierdo()) {
                algoritmo(jugadores.getHijoIzquierdo(), golesYPaises);
            }
            if (jugadores.tieneHijoDerecho()) {
                algoritmo(jugadores.getHijoDerecho(), golesYPaises);
            }
        }
    }
}
