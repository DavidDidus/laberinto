import java.awt.Point;
import java.util.ArrayList;

import java.lang.Math;

public class AEstrella {
    private int[][] laberinto;
    private int filas, columnas;
    private Point[] direcciones = {new Point(-1, -1),new Point(-1,0),new Point(-1,1),new Point(0,1),
                                   new Point(1,1),new Point(1,0),new Point(1,-1),new Point(0,-1) };

    public AEstrella(int[][] laberinto) {
        this.laberinto = laberinto;
        this.filas = laberinto.length;
        this.columnas = laberinto[0].length;
    }

    public ArrayList<Point> encontrarCamino(Point inicio, Point destino) {
        ArrayList<Nodo> listaAbierta = new ArrayList<Nodo>();
        Nodo nodoInicio = new Nodo(inicio);
        return null;
    }

    private int heuristica(Point a, Point b) {
        int dx = Math.abs((int)a.getX() - (int)b.getX());
        int dy = Math.abs((int)a.getY() - (int)b.getY());
        return 1;
    }

}


