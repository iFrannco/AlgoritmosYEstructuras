package tp06.estructuras.implementacionMatriz;

import tp01.ejercicio2.estructuras.ListaEnlazadaGenerica;
import tp01.ejercicio2.estructuras.ListaGenerica;
import tp06.estructuras.Arista;
import tp06.estructuras.AristaImpl;
import tp06.estructuras.Grafo;
import tp06.estructuras.Vertice;

public class GrafoImplMatrizAdy<T> implements Grafo<T> {
    private int[][] matrizAdy;
    private ListaGenerica<Vertice<T>> vertices;
    private int maxVertices;
    // private boolean esDirigido;

    public GrafoImplMatrizAdy(int tamañoMatriz) {
        this.maxVertices = tamañoMatriz;
        this.matrizAdy = new int[maxVertices + 1][maxVertices + 1];
        this.vertices = new ListaEnlazadaGenerica<>();
    }

    @Override
    public void agregarVertice(Vertice<T> v) {
        if (!vertices.incluye(v)) {
            v.setPosicion(this.vertices.tamanio() + 1);
            this.vertices.agregarFinal(v);
        }
    }

    @Override
    public void eliminarVertice(Vertice<T> v) {
        // eliminar sus conexiones con otros vertices
        for (int i = 1; i <= maxVertices; i++) {
            matrizAdy[i][v.getPosicion()] = 0;
            matrizAdy[v.getPosicion()][i] = 0;
        }
        // eliminar vertice
        this.vertices.eliminar(v);
        // podria guardar esa pos en un vector de "lugares disponibles"
    }

    @Override
    public void conectar(Vertice<T> origen, Vertice<T> destino) {
        if (this.vertices.incluye(origen) && this.vertices.incluye(destino)) {
            matrizAdy[origen.getPosicion()][destino.getPosicion()] = 1;
            // si es no dirigido, descomentar lo de abajo.
            //matrizAdy[destino.getPosicion()][origen.getPosicion()] = 1;
        }
    }

    @Override
    public void conectar(Vertice<T> origen, Vertice<T> destino, int peso) {
        if (this.vertices.incluye(origen) && this.vertices.incluye(destino)) {
            matrizAdy[origen.getPosicion()][destino.getPosicion()] = peso;
            // si es no dirigido, descomentar lo de abajo.
            //matrizAdy[destino.getPosicion()][origen.getPosicion()] = peso;
        }
    }

    @Override
    public void desConectar(Vertice<T> origen, Vertice<T> destino) {
        if (this.vertices.incluye(origen) && this.vertices.incluye(destino)) {
            matrizAdy[origen.getPosicion()][destino.getPosicion()] = 0;
            // si es no dirigido, deberia desconectar el inverso.
        }
    }

    @Override
    public boolean esAdyacente(Vertice<T> origen, Vertice<T> destino) {
        return matrizAdy[origen.getPosicion()][destino.getPosicion()] != 0;
    }

    @Override
    public int peso(Vertice<T> origen, Vertice<T> destino) {
        return matrizAdy[origen.getPosicion()][destino.getPosicion()];
    }

    @Override
    public boolean esVacio() {
        return this.vertices.esVacia();
    }

    @Override
    public ListaGenerica<Vertice<T>> listaDeVertices() {
        return this.vertices;
    }

    @Override
    public Vertice<T> vetice(int posicion) {
        for (int i = 1; i <= this.vertices.tamanio(); i++) {
            if (vertices.elemento(i).getPosicion() == posicion) {
                return vertices.elemento(i);
            }
        }
        return null;
    }

    @Override
    public ListaGenerica<Arista<T>> listaDeAdyacentes(Vertice<T> v) {
        ListaGenerica<Arista<T>> resultado = new ListaEnlazadaGenerica<>();
        if (this.vertices.incluye(v)) {
            for (int i = 1; i <= this.maxVertices; i++) {
                if (matrizAdy[v.getPosicion()][i] != 0) {
                    Vertice<T> buscado = null;
                    this.vertices.comenzar();
                    while (!vertices.fin() && buscado == null) {
                        Vertice<T> temp = vertices.proximo();
                        if (temp.getPosicion() == i) {
                            buscado = temp;
                        }
                    }
                    resultado.agregarFinal(new AristaImpl<>(buscado, matrizAdy[v.getPosicion()][i]));
                }

            }
        }

        return resultado;
    }
}
