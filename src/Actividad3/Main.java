package Actividad3;// src/Actividad3/Grafo.java
import java.util.*;

class Grafo {
    private final int numVertices;
    private final List<List<Arista>> adjList;

    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        adjList = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void agregarArista(int origen, int destino, int costo) {
        adjList.get(origen).add(new Arista(destino, costo));
        adjList.get(destino).add(new Arista(origen, costo)); // Grafo no dirigido
    }

    public List<Conexion> primMST() {
        boolean[] visitado = new boolean[numVertices];
        PriorityQueue<AristaConOrigen> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.costo));
        List<Conexion> mst = new ArrayList<>();
        int inicio = 0;
        visitado[inicio] = true;
        for (Arista arista : adjList.get(inicio)) {
            pq.add(new AristaConOrigen(inicio, arista.destino, arista.costo));
        }
        while (!pq.isEmpty() && mst.size() < numVertices - 1) {
            AristaConOrigen actual = pq.poll();
            if (!visitado[actual.destino]) {
                visitado[actual.destino] = true;
                mst.add(new Conexion(actual.origen, actual.destino, actual.costo));
                for (Arista arista : adjList.get(actual.destino)) {
                    if (!visitado[arista.destino]) {
                        pq.add(new AristaConOrigen(actual.destino, arista.destino, arista.costo));
                    }
                }
            }
        }
        return mst;
    }
}

class Arista {
    int destino, costo;
    public Arista(int destino, int costo) {
        this.destino = destino;
        this.costo = costo;
    }
}

class AristaConOrigen {
    int origen, destino, costo;
    public AristaConOrigen(int origen, int destino, int costo) {
        this.origen = origen;
        this.destino = destino;
        this.costo = costo;
    }
}

class Conexion {
    int origen, destino, costo;
    public Conexion(int origen, int destino, int costo) {
        this.origen = origen;
        this.destino = destino;
        this.costo = costo;
    }
}

// src/Actividad3/Main.java
public class Main {
    public static void main(String[] args) {
        int numEstaciones = 5;
        Grafo grafo = new Grafo(numEstaciones);

        // Ejemplo de conexiones (origen, destino, costo)
        grafo.agregarArista(0, 1, 10);
        grafo.agregarArista(0, 2, 20);
        grafo.agregarArista(1, 2, 5);
        grafo.agregarArista(1, 3, 15);
        grafo.agregarArista(2, 3, 30);
        grafo.agregarArista(2, 4, 25);
        grafo.agregarArista(3, 4, 8);

        var mst = grafo.primMST();
        int costoTotal = 0;
        System.out.println("Conexiones del Árbol de Recubrimiento Mínimo:");
        for (Conexion c : mst) {
            System.out.println("Estación " + c.origen + " <-> Estación " + c.destino + " | Costo: " + c.costo);
            costoTotal += c.costo;
        }
        System.out.println("Costo total: " + costoTotal);
    }
}
