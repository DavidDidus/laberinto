import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


public class AEstrella {
    private int[][] laberinto;
    private int filas, columnas;
    private List<Point> direcciones = Arrays.asList(
            new Point(-1, 0), new Point(0, 1), new Point(1, 0), new Point(0, -1)
    );

    public AEstrella(int[][] laberinto) {
        this.laberinto = laberinto;
        this.filas = laberinto.length;
        this.columnas = laberinto[0].length;
    }

    public List<Point> encontrarCamino(Point inicio, Point destino) {
        PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>(Comparator.comparingInt(n -> n.f));
        Map<Point, Point> padres = new HashMap<>();
        Map<Point, Integer> gScore = new HashMap<>();
        Map<Point, Integer> fScore = new HashMap<>();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Point punto = new Point(i, j);
                gScore.put(punto, Integer.MAX_VALUE);
                fScore.put(punto, Integer.MAX_VALUE);
            }
        }

        gScore.put(inicio, 0);
        fScore.put(inicio, heuristica(inicio, destino));
        colaPrioridad.add(new Nodo(inicio, fScore.get(inicio)));

        while (!colaPrioridad.isEmpty()) {
            Point nodoActual = colaPrioridad.poll().nodo;

            if (nodoActual.equals(destino)) {
                return reconstruirCamino(padres, nodoActual);
            }

            for (Point direccion : direcciones) {
                Point vecino = new Point(nodoActual.x + direccion.x, nodoActual.y + direccion.y);

                if (esValido(vecino) && laberinto[vecino.x][vecino.y] == 0) {
                    int tentativa_gScore = gScore.get(nodoActual) + 1;

                    if (tentativa_gScore < gScore.get(vecino)) {
                        padres.put(vecino, nodoActual);
                        gScore.put(vecino, tentativa_gScore);
                        fScore.put(vecino, tentativa_gScore + heuristica(vecino, destino));
                        colaPrioridad.add(new Nodo(vecino, fScore.get(vecino)));
                    }
                }
            }
        }

        return null; // No se encontró un camino
    }

    private int heuristica(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    private boolean esValido(Point punto) {
        return punto.x >= 0 && punto.x < filas && punto.y >= 0 && punto.y < columnas;
    }

    private List<Point> reconstruirCamino(Map<Point, Point> padres, Point nodo) {
        List<Point> camino = new ArrayList<>();
        camino.add(nodo);

        while (padres.containsKey(nodo)) {
            nodo = padres.get(nodo);
            camino.add(nodo);
        }

        Collections.reverse(camino);
        return camino;
    }
}


