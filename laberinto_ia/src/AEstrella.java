import java.awt.Point;
import java.util.ArrayList;
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

    public ArrayList<Point> encontrarCamino(Point inicio, Point destino) {
        ArrayList<Nodo> listaAbierta = new ArrayList<Nodo>();
        ArrayList<Nodo> listaCerrada = new ArrayList<Nodo>();
        boolean run = true;
        
        int x,y;
        Nodo inicioNodo = new Nodo(inicio);
        listaCerrada.add(inicioNodo);
        listaAbierta.add(inicioNodo);
        while(run){
            if (listaAbierta.isEmpty()) {
                break; // No hay más nodos para explorar, terminar el bucle
            }
            Nodo actual = listaCerrada.get(listaCerrada.size() - 1);
            System.out.println(actual.getCoordenada());
            Nodo costoMasBajo = new Nodo(new Point(0, 0));
            costoMasBajo.setH(99999);
            listaAbierta.remove(inicioNodo);

            for(int i=0;i<8;i++){
                x =(int)(actual.getX() + direcciones[i].getX());
                y = (int)(actual.getY() + direcciones [i].getY());
                try {
                    if(laberinto[x][y] != 0 && !buscarNodo(listaCerrada, new Point(x, y))){
                        Nodo nodoNuevo = new Nodo(new Point((int)(actual.getX() + direcciones[i].getX()),
                                                            (int)(actual.getY() + direcciones [i].getY())));
                        
                        if(!buscarNodo(listaAbierta,nodoNuevo)){
                            if(i<4){
                                nodoNuevo.setG(14);
                            }else{
                                nodoNuevo.setG(10);
                            }
                            nodoNuevo.setH(calcularH(nodoNuevo.getCoordenada(), destino));
                            nodoNuevo.setPadre(actual);
                            
                        }else{
                            nodoNuevo = getNodo(listaAbierta,nodoNuevo);
                            if(nodoNuevo.getG() == 14 && i>=4){
                                nodoNuevo.setG(10);
                                nodoNuevo.setH(calcularH(nodoNuevo.getCoordenada(), destino));
                                nodoNuevo.setPadre(actual);
                            }
                        }
                        if(nodoNuevo.getF()<costoMasBajo.getF()){
                            costoMasBajo=nodoNuevo;
                        }
                        listaAbierta.add(nodoNuevo);
                        
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            listaAbierta.remove(costoMasBajo);
            listaCerrada.add(costoMasBajo);
            
            if (actual.getCoordenada().equals(destino)) {
                run = false; // Si se alcanzó el destino, salir del bucle
            }

        }
        return convertirLista(listaCerrada);
        
    }
    private ArrayList<Point> convertirLista(ArrayList<Nodo> lista){
        ArrayList<Point> listaNueva = new ArrayList<Point>();
        for(Nodo nodo : lista){
            listaNueva.add(nodo.getCoordenada());
        }
        return listaNueva;
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


