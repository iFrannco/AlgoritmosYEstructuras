package Practica.parcial;

import tp01.ejercicio2.estructuras.ListaEnlazadaGenerica;
import tp01.ejercicio2.estructuras.ListaGenerica;
import tp03.estructuras.ArbolBinarioDeBusqueda;
import tp06.estructuras.Grafo;
import tp06.estructuras.Vertice;
import tp06.estructuras.implementacionMatriz.GrafoImplMatrizAdy;
import tp06.estructuras.implementacionMatriz.VerticeImplMatrizAdy;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Grafo<String> ubicaciones = new GrafoImplMatrizAdy<>(20);
        Vertice<String> parana = new VerticeImplMatrizAdy<>("parana");
        Vertice<String> avellaneda = new VerticeImplMatrizAdy<>("avellaneda");
        Vertice<String> caballito = new VerticeImplMatrizAdy<>("caballito");
        Vertice<String> retirno = new VerticeImplMatrizAdy<>("retirno");
        Vertice<String> almagro = new VerticeImplMatrizAdy<>("almagro");
        Vertice<String> velez = new VerticeImplMatrizAdy<>("velez");
        Vertice<String> guillotina = new VerticeImplMatrizAdy<>("sarmiento");
        Vertice<String> pasteleria = new VerticeImplMatrizAdy<>("pasteleria");

        ubicaciones.agregarVertice(parana);
        ubicaciones.agregarVertice(avellaneda);
        ubicaciones.agregarVertice(caballito);
        ubicaciones.agregarVertice(retirno);
        ubicaciones.agregarVertice(almagro);
        ubicaciones.agregarVertice(velez);
        ubicaciones.agregarVertice(guillotina);
        ubicaciones.agregarVertice(pasteleria);

        ubicaciones.conectar(parana, avellaneda);
        ubicaciones.conectar(parana, caballito);
        ubicaciones.conectar(parana, retirno);

        ubicaciones.conectar(avellaneda, almagro);
        ubicaciones.conectar(caballito, velez);
        ubicaciones.conectar(retirno, guillotina);

        ubicaciones.conectar(almagro, pasteleria);
        ubicaciones.conectar(velez, pasteleria);
        ubicaciones.conectar(guillotina, pasteleria);

        Ejercicio1 p = new Ejercicio1();
        ListaEnlazadaGenerica<String> obstaculos = new ListaEnlazadaGenerica<>();
        obstaculos.agregarFinal("almagro");

        System.out.println("Ejercicio 1:");
        ListaGenerica<String> resultado = p.algoritmo(ubicaciones, "parana", "pasteleria", obstaculos);
        resultado.comenzar();
        while (!resultado.fin()) {

            System.out.println(resultado.proximo());
        }


        System.out.println("--------------------------------------------");
        System.out.println("Ejercicio 2:");

        Jugador messi = new Jugador("Messi", 3, "Argentina");
        Jugador lucas = new Jugador("Lucas", 2, "Francia");
        Jugador mbappe = new Jugador("Mbappe", 5, "Francia");
        Jugador martin = new Jugador("Martin", 4, "Argentina");
        Jugador luciano = new Jugador("Luciano", 6, "Inglaterra");


        ArbolBinarioDeBusqueda<Jugador> nodoMessi = new ArbolBinarioDeBusqueda<>(messi);
        ArbolBinarioDeBusqueda<Jugador> nodoLucas = new ArbolBinarioDeBusqueda<>(lucas);
        ArbolBinarioDeBusqueda<Jugador> nodoMbape = new ArbolBinarioDeBusqueda<>(mbappe);
        ArbolBinarioDeBusqueda<Jugador> nodoMartin = new ArbolBinarioDeBusqueda<>(martin);
        ArbolBinarioDeBusqueda<Jugador> nodoLuciano = new ArbolBinarioDeBusqueda<>(luciano);

        nodoMessi.agregarHijoIzquierdo(nodoLucas);
        nodoMessi.agregarHijoDerecho(nodoMbape);
        nodoMbape.agregarHijoIzquierdo(nodoMartin);
        nodoMbape.agregarHijoDerecho(nodoLuciano);

        Ejercicio2 e2 = new Ejercicio2();
        HashMap<String, Integer> resultado2 = e2.paisesGoleadores(nodoMessi);

        System.out.println("fin");


    }
}
