import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;

public class AEstrella {
    private int[][] laberinto;
    private int filas, columnas;
    private Point[] direcciones = {new Point(-1, -1),new Point(1,1),new Point(-1,1),new Point(1,-1),
                                   new Point(-1,0),new Point(0,1),new Point(1,0),new Point(0,-1)};

    public AEstrella(int[][] laberinto) {
        this.laberinto = laberinto;
        this.filas = laberinto.length;
        this.columnas = laberinto[0].length;
    }
    /**
     * Algoritmo a*, busca el camino mas corto entre un punto y un punto b
     * @param inicio Punto de inicio
     * @param destino Punto de destino
     * @return El camino entre los dos puntos o null si el laberinto no tiene solucion
     */
    public ArrayList<Point> encontrarCamino(Point inicio, Point destino) {
        ArrayList<Nodo> listaAbierta = new ArrayList<Nodo>();
        ArrayList<Nodo> listaCerrada = new ArrayList<Nodo>();

        // Añadir el nodo de inicio a la lista abierta 
        Nodo nodoInicio = new Nodo(inicio);                 
        nodoInicio.setG(0);                               
        nodoInicio.setH(calcularH(inicio, destino));        
        listaAbierta.add(nodoInicio);

        // Mientras la lista abierta no este vacia
        while(!listaAbierta.isEmpty()){
            Nodo actual = obtenerNodoMenorCosto(listaAbierta);          // Obtener el nodo con el valor f(n) más bajo
            listaAbierta.remove(actual);                                // Se remueve el nodo obtenido de la lista abierta y se agrega a la lista cerrada
            listaCerrada.add(actual);

            if(actual.getCoordenada().equals(destino)){                 // Si le coordenada del nodo actual es la misma que la coordenada del destino
                return construirCamino(actual);                         // Se llego al final del recorrido
            }

            for(Point direccion : direcciones){                         // Por cada direccion almacenada
                int x = (int) (actual.getX() + direccion.getX());       // Se calcula la posicion X del nuevo nodo
                int y = (int) (actual.getY() + direccion.getY());       // Se calcula la posicion Y del nuevo nodo
            
                try { 
                    // Si la posicion calculada no es una pared y no esta dentro de la lista cerrada
                    if(laberinto[x][y] != 0 && !buscarNodo(listaCerrada, new Point(x, y))) {    
                        Nodo sucesor = new Nodo(new Point(x, y));       // Se crea un nuevo nodo sucesor del actual
                        sucesor.setPadre(actual);                       // Se asigna el nodo actual como padre del nodo sucesor
            
                        int costo;          
                        if(direccion.getX() == 0 || direccion.getY() == 0) {    // Se calcula el valor de g() segun si es un movimiento vertical, horizontal o diagonal
                            costo = 10;                                         // Si es vertical o horizontal el valor de g() es de 10
                        }else {
                            costo = 14;                                         // Si es diagonal el valor de g() es de 14
                        }
                        sucesor.setG(actual.getG() + costo); 
                        sucesor.setH(calcularH(sucesor.getCoordenada(), destino));  // Se calcula el valor de h()
                        sucesor.setF(sucesor.getG() + sucesor.getH());              // Se calcula el valor de f()
            
                        if(!buscarNodo(listaAbierta, sucesor)) {                    // Si el nodo no esta dentro de la lista abierta se almacena en ella
                            listaAbierta.add(sucesor);
                        }else {                                                     // Si el nodo esta dentro de la lista abierta se compara el nuevo nodo con el almacenado
                            Nodo nodoEnListaAbierta = getNodo(listaAbierta, sucesor);   
                            if(sucesor.getG() < nodoEnListaAbierta.getG()) {        // En caso de que el valor de g() sea menor al que esta almacenada en la lista
                                nodoEnListaAbierta.setG(sucesor.getG());            // Se cambian los atributos del nodo almacenado en la lista por los del nodo nuevo
                                nodoEnListaAbierta.setPadre(actual);                
                            }
                        }
                    }
                }catch (Exception e) {
                    // Manejar excepciones
                }
            }
        }

        return null; // Si llegamos aquí, no se encontró una solución
    }
    /**
     * 
     * @param lista Lista con
     * @return
     */
    private Nodo obtenerNodoMenorCosto(ArrayList<Nodo> lista) {
        Nodo nodoMenorCosto = lista.get(0);
        for (Nodo nodo : lista) {
            if (nodo.getF() < nodoMenorCosto.getF()) {
                nodoMenorCosto = nodo;
            }
        }
        return nodoMenorCosto;
    }

    private ArrayList<Point> construirCamino(Nodo nodoFinal) {
        ArrayList<Point> camino = new ArrayList<>();
        Nodo actual = nodoFinal;
        while (actual != null) {
            camino.add(actual.getCoordenada());
            actual = actual.getPadre();
        }
        Collections.reverse(camino);
        return camino;
    }

    private Nodo getNodo(ArrayList<Nodo> lista ,Nodo buscado){
        for(Nodo nodo : lista){
            if(nodo.getCoordenada().equals(buscado.getCoordenada())){
                return nodo;
            }
        }
        return buscado;
    }
    
    private boolean buscarNodo(ArrayList<Nodo> lista ,Nodo buscado){
        for(Nodo nodo : lista){
            if(nodo.getCoordenada().equals(buscado.getCoordenada())){
                return true;
            }
        }
        return false;
    }
     private boolean buscarNodo(ArrayList<Nodo> lista ,Point buscado){
        for(Nodo nodo : lista){
            if(nodo.getCoordenada().equals(buscado)){
                return true;
            }
        }
        return false;
    }

    private int calcularH(Point punto, Point meta) {
        int dx = Math.abs((int)punto.getX() - (int)meta.getX());
        int dy = Math.abs((int)punto.getY() - (int)meta.getY());
        return 10*(dx+dy);
    }

}