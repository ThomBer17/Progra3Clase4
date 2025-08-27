package Actividad2;
import java.util.*;

class GrafoMatriz {
    private final int[][] matriz;
    private final int numVertices;

    public GrafoMatriz(int numVertices) {
        this.numVertices = numVertices;
        matriz = new int[numVertices][numVertices];
    }

    public void agregarArista(int origen, int destino) {
        matriz[origen][destino] = 1;
    }

    public void eliminarArista(int origen, int destino) {
        matriz[origen][destino] = 0;
    }

    public boolean existeArista(int origen, int destino) {
        return matriz[origen][destino] == 1;
    }

    public List<Integer> adyacentes(int vertice) {
        List<Integer> adyacentes = new ArrayList<>();
        for (int j = 0; j < numVertices; j++) {
            if (matriz[vertice][j] == 1) {
                adyacentes.add(j);
            }
        }
        return adyacentes;
    }

    public int gradoSalida(int vertice) {
        int grado = 0;
        for (int j = 0; j < numVertices; j++) {
            if (matriz[vertice][j] == 1) grado++;
        }
        return grado;
    }

    public int gradoEntrada(int vertice) {
        int grado = 0;
        for (int i = 0; i < numVertices; i++) {
            if (matriz[i][vertice] == 1) grado++;
        }
        return grado;
    }
}

public class Main {
    public static void main(String[] args) {
        GrafoMatriz grafo = new GrafoMatriz(4);

        // Agregar aristas
        grafo.agregarArista(0, 1);
        grafo.agregarArista(0, 2);
        grafo.agregarArista(1, 2);
        grafo.agregarArista(2, 3);

        // Mostrar adyacentes de cada vértice
        for (int i = 0; i < 4; i++) {
            System.out.println("Adyacentes de " + i + ": " + grafo.adyacentes(i));
        }

        // Verificar existencia de arista
        System.out.println("¿Existe arista de 0 a 2? " + grafo.existeArista(0, 2));
        System.out.println("¿Existe arista de 1 a 3? " + grafo.existeArista(1, 3));

        // Eliminar arista y verificar
        grafo.eliminarArista(0, 2);
        System.out.println("¿Existe arista de 0 a 2 después de eliminar? " + grafo.existeArista(0, 2));

        // Grado de entrada y salida
        for (int i = 0; i < 4; i++) {
            System.out.println("Grado de salida de " + i + ": " + grafo.gradoSalida(i));
            System.out.println("Grado de entrada de " + i + ": " + grafo.gradoEntrada(i));
        }
    }
}
