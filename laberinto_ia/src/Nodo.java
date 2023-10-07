import java.awt.Point;

public class Nodo {
    Point nodo;
    Nodo padre;
    int f;

    public Nodo(Point nodo) {
        this.nodo = nodo;
    }
    public int getF() {
        return f;
    }
    public Point getNodo() {
        return nodo;
    }
    public Nodo getPadre() {
        return padre;
    }
    public void setF(int f) {
        this.f = f;
    }
    public void setNodo(Point nodo) {
        this.nodo = nodo;
    }
    public void setPadre(Nodo padre) {
        this.padre = padre;
    }
}