import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;

/**
 * Clase que representa el panel donde se dibuja el laberinto
 */
public class PanelLaberinto extends JPanel   {
    private int[][] laberinto;
    private int filas, columnas;
    private int ladoCelda = 45; // Tamaño de cada celda en píxeles
    private int valorAlmacenado = 0; // valor que representa lo que se va a insertar en la casilla
    private Point posComienzo;
    private Point posFinal;
    private Color color;
    private boolean detener = true;
    

    public PanelLaberinto(int[][] laberinto) {
        this.laberinto = laberinto;
        this.filas = laberinto.length;
        this.columnas = laberinto[0].length;
        
        /* Listener que pinta el laberinto segun la casilla que es presionada desde la ventana */
        addMouseListener(new MouseAdapter(){
        @Override
        public void mousePressed(MouseEvent e) {
            try {
                /* Se obtienen las coordenadas del click */
                int fila = e.getY() / ladoCelda;        
                int columna = e.getX() / ladoCelda;
                
                /* Si el valor almacenado es 2 este representa el comienzo del laberinto */
                if(valorAlmacenado == 2){
                    if(posComienzo == null){                            // si no hay un punto de inicio definido aun, se define
                        laberinto[fila][columna] = valorAlmacenado;
                        posComienzo = new Point(fila, columna);
                        valorAlmacenado =0;
                    }else{                                              // si no hay un punto de inicio definido aun, se define
                        laberinto[(int)posComienzo.getX()][(int)posComienzo.getY()] = 1;
                        laberinto[fila][columna] = valorAlmacenado;
                        posComienzo = new Point(fila, columna);
                        valorAlmacenado =0;
                    }
                /* Si el valor almacenado es 3 este representa el final del laberinto */
                }else if( valorAlmacenado == 3){
                    if(posFinal == null){                               // si no hay un punto de meta definido aun, se define
                        laberinto[fila][columna] = valorAlmacenado;
                        posFinal = new Point(fila, columna);    
                        valorAlmacenado =0;
                    }else{                                              // si ya hay definido un punto de meta, este se reemplaza
                        laberinto[(int)posFinal.getX()][(int)posFinal.getY()] = 1;
                        laberinto[fila][columna] = valorAlmacenado;
                        posFinal = new Point(fila, columna);
                        valorAlmacenado =0;
                    }
                }else{
                    if(laberinto[fila][columna] == 0){                  // si el valor en el punto es 0 representa una pared
                        laberinto[fila][columna] = 1;                   // en este caso si una pared se presiona esta se convierte en una casilla libre
                    }else{
                        if(laberinto[fila][columna] == 2 || laberinto[fila][columna] == 3){ // Si el punto seleccionado es el inicio o la meta este punto no cambia
                        }else{
                            laberinto[fila][columna] = valorAlmacenado; // De lo contrario el punto se convierte en una pared
                        }
                    }
                }
            } catch (Exception excepcion) {
                // TODO: handle exception
            }
            // Actualizar el estado de la celda seleccionada (por ejemplo, convertirla en pared)
            if(detener){
                repaint(); // Vuelve a pintar el panel para reflejar el cambio
            }
        }
    });
    }

    /**
     * Pinta el laberinto en el panel
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                g.setColor(Color.BLACK);   
                if (laberinto[i][j] == 1) { // Camino libre
                    g.drawRect(j*ladoCelda, i*ladoCelda, ladoCelda, ladoCelda);
                } else if (laberinto[i][j] == 0){ // Pared
                    g.fillRect(j*ladoCelda, i*ladoCelda, ladoCelda, ladoCelda);
                }else if (laberinto[i][j] == 2){ // Punto de inicio
                    g.drawRect(j*ladoCelda, i*ladoCelda, ladoCelda, ladoCelda);
                    g.setColor(color = new Color(114,137,218));
                    g.fillRect(j*ladoCelda, i*ladoCelda, ladoCelda, ladoCelda);
                }else if (laberinto[i][j] == 3){ // Punto de meta
                    g.drawRect(j*ladoCelda, i*ladoCelda, ladoCelda, ladoCelda);
                    g.setColor(color = new Color(255,87,123));
                    g.fillRect(j*ladoCelda, i*ladoCelda, ladoCelda, ladoCelda);
                }else if (laberinto[i][j] == 4){ // Camino mas corto
                    g.drawRect(j*ladoCelda, i*ladoCelda, ladoCelda, ladoCelda);
                    g.setColor(color = new Color(77,101,77));
                    g.fillRect(j*ladoCelda, i*ladoCelda, ladoCelda, ladoCelda);
                }
            }
        }
    }
    public void setValor(int valor){
        this.valorAlmacenado =valor;
    }
    public Point getPosComienzo(){  
        return posComienzo;
    }
    public Point getPosFinal(){
        return posFinal;
    }
    public int[][] getLaberinto() {
        return laberinto;
    }
    public void setDetener(boolean detener) {
        this.detener = detener;
    }
    /**
     * Limpia el laberinto
     */
    public void limpiar(){
        for(int i=0;i<filas;i++){
            for(int j=0;j<columnas;j++){
                laberinto[i][j] =1;
            }
        }
        repaint();
    }
    /**
     * Inserta el camino mas corto dentro del laberinto
     * @param x posicion x de la matriz que representa el laberinto
     * @param y posicion y de la matriz que representa el laberinto
     */
    public void insertarCamino(int x, int y) {
        if(x == posComienzo.getX() && y == posComienzo.getY()){}
        else if((x == posFinal.getX() && y == posFinal.getY())){}
        else{
           
            laberinto[x][y] = 4;
        }
        repaint();
        
    }
}

