package Actividad4;

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

    public void agregarArista(int origen, int destino, int tiempo) {
        adjList.get(origen).add(new Arista(destino, tiempo));
        adjList.get(destino).add(new Arista(origen, tiempo)); // Si las carreteras son bidireccionales
    }

    public int[] dijkstra(int inicio) {
        int[] dist = new int[numVertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[inicio] = 0;
        PriorityQueue<AristaConDestino> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.tiempo));
        pq.add(new AristaConDestino(inicio, 0));

        while (!pq.isEmpty()) {
            AristaConDestino actual = pq.poll();
            int u = actual.destino;
            int tiempoActual = actual.tiempo;
            if (tiempoActual > dist[u]) continue;
            for (Arista arista : adjList.get(u)) {
                int v = arista.destino;
                int nuevoTiempo = tiempoActual + arista.tiempo;
                if (nuevoTiempo < dist[v]) {
                    dist[v] = nuevoTiempo;
                    pq.add(new AristaConDestino(v, nuevoTiempo));
                }
            }
        }
        return dist;
    }
}

class Arista {
    int destino, tiempo;
    public Arista(int destino, int tiempo) {
        this.destino = destino;
        this.tiempo = tiempo;
    }
}

class AristaConDestino {
    int destino, tiempo;
    public AristaConDestino(int destino, int tiempo) {
        this.destino = destino;
        this.tiempo = tiempo;
    }
}

public class Main {
    public static void main(String[] args) {
        int numCentros = 5;
        Grafo grafo = new Grafo(numCentros);

        // Ejemplo de conexiones (origen, destino, tiempo en minutos)
        grafo.agregarArista(0, 1, 12);
        grafo.agregarArista(0, 2, 7);
        grafo.agregarArista(1, 2, 3);
        grafo.agregarArista(1, 3, 8);
        grafo.agregarArista(2, 3, 5);
        grafo.agregarArista(2, 4, 10);
        grafo.agregarArista(3, 4, 2);

        int centroPrincipal = 0;
        int[] tiemposMinimos = grafo.dijkstra(centroPrincipal);

        System.out.println("Tiempo mínimo de entrega desde el centro principal:");
        for (int i = 0; i < numCentros; i++) {
            System.out.println("Centro " + centroPrincipal + " -> Centro " + i + ": " + tiemposMinimos[i] + " minutos");
        }
    }
}
