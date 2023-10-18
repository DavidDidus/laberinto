import java.awt.Point;

/**
 * Clase que representa puntos del camino para la implementacion del algoritmo
 */
public class Nodo {
    private Point coordenada;
    private Nodo padre;
    private int f,g,h;

    public Nodo(Point coordenada) {
        this.coordenada = coordenada;
    }
    public int getF() {
        return f;
    }
    public int getG() {
        return g;
    }
    public int getH() {
        return h;
    }
    public Point getCoordenada() {
        return coordenada;
    }
    public Nodo getPadre() {
        return padre;
    }
    public void setG(int g) {
        this.g = g;
    }
    public void setH(int h) {
        this.h = h;
    }
    public void setCoordenada(Point coordenada) {
        this.coordenada = coordenada;
    }
    public void setPadre(Nodo padre) {
        this.padre = padre;
    }
    public double getX() {
        return coordenada.getX() ;
    }
    public double getY() {
        return coordenada.getY() ;
    }
    public void setF(int f) {
        this.f = f;
    }
}