package Practica.parcial;

import tp01.ejercicio2.estructuras.ListaEnlazadaGenerica;
import tp01.ejercicio2.estructuras.ListaGenerica;
import tp06.estructuras.Arista;
import tp06.estructuras.Grafo;
import tp06.estructuras.Vertice;

public class Ejercicio1 {

    public ListaGenerica<String> algoritmo(Grafo<String> ubicacion, String origen, String destino, ListaEnlazadaGenerica<String> obstaculos) {
        if (ubicacion == null || origen == null || destino == null || obstaculos == null || ubicacion.esVacio()) {
            return new ListaEnlazadaGenerica<>();
        }

        // Compruebo que el origen y el destino existan.
        // Y me guardo el vertice origen para empezar el recorrido dfs desde ese nodo.
        Vertice<String> verticeOrigen = null;

        ListaGenerica<Vertice<String>> vertices = ubicacion.listaDeVertices();
        int lugaresEncontrados = 0;
        vertices.comenzar();
        while (!vertices.fin() && !(lugaresEncontrados == 2)) {
            Vertice<String> verticeAct = vertices.proximo();
            if (verticeAct.dato().equals(origen)) {
                verticeOrigen = verticeAct;
                lugaresEncontrados++;
            } else if (verticeAct.dato().equals(destino)) {
                lugaresEncontrados++;
            }
        }

        // Si alguno de los dos (destino u origen) no existe en el grafo, retorno una lista vacia
        if (lugaresEncontrados != 2) return new ListaEnlazadaGenerica<>();

        // creo un vector para llevar la cuenta de los nodos que visito.
        // ademas creo una lista de caminos (que en este caso no era necesaria, pero que puede ser util en el caso
        // de que se pida un camino con restricciones y que si no se encuentra, se retorne uno que al menos llege al desitno)
        boolean[] enCamino = new boolean[vertices.tamanio() + 1];
        ListaGenerica<ListaGenerica<String>> caminos = new ListaEnlazadaGenerica<>();
        ListaGenerica<String> caminoPosible = new ListaEnlazadaGenerica<>();

        // llamo al algoritmo dfs desde el vertice origen.
        dfs(ubicacion, verticeOrigen, destino, enCamino, caminos, caminoPosible, obstaculos);

        if (caminos.esVacia()) {
            return new ListaEnlazadaGenerica<>();
        }
        caminos.comenzar();
        return caminos.proximo();

    }

    private boolean dfs(Grafo<String> ubicacion, Vertice<String> vertice, String destino, boolean[] enCamino, ListaGenerica<ListaGenerica<String>> caminos, ListaGenerica<String> caminoPosible, ListaGenerica<String> obstaculos) {

        // Marco como visitado el nodo actual y lo añado al camino actual que estoy armando
        enCamino[vertice.getPosicion()] = true;
        caminoPosible.agregarFinal(vertice.dato());

        // si el vertice representa uno de los lugares prohibidos por donde no puedo pasar, lo marco como no visitado
        // y lo saco de la lista
        // en este caso podria dejarlo marcado como visitado, para no volver a llegar a el por otro camino
        // pero en otros casos es util poder usar ese nodo como parte de otro camino que si puede ser valido.
        if (obstaculos.incluye(vertice.dato())) {
            enCamino[vertice.getPosicion()] = false;
            caminoPosible.eliminarEn(caminoPosible.tamanio());
            return false;
        }
        // si llegue al destino (asumo que destino != origen)
        // significa que encontre un camino valido desde el origen hasta el destino
        // por lo tanto, lo añado a la lista de caminos posibles
        // y lo desmarco de los visitados porque quizas este vertice forma parte de otro camino que tambien es valido
        else if (vertice.dato().equals(destino)) {
            caminos.agregarFinal(caminoPosible.clonar());
            enCamino[vertice.getPosicion()] = false;
            caminoPosible.eliminarEn(caminoPosible.tamanio());
            return true;
        }
        // en cualquier otro caso (basicamente, si no llegue al destino) sigo buscando el destino
        // pido los adyacentes del vertice actual, y si no estan visitados, los visito un dfs
        else {
            ListaGenerica<Arista<String>> adyacentes = ubicacion.listaDeAdyacentes(vertice);
            adyacentes.comenzar();
            while (!adyacentes.fin()) {
                Vertice<String> verticeAct = adyacentes.proximo().verticeDestino();
                if (!enCamino[verticeAct.getPosicion()]) {
                    dfs(ubicacion, verticeAct, destino, enCamino, caminos, caminoPosible, obstaculos);
                }
            }
        }

        // Si ya visite todos los adyacentes y no encontre el destino es porque este camino no me servia
        // por lo tanto quito el vertice actual como visitado
        // y lo saco de la lista que forma parte de mi camino actualq
        enCamino[vertice.getPosicion()] = false;
        caminoPosible.eliminarEn(caminoPosible.tamanio());
        return false;

    }

}
