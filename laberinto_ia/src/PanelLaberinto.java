import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;

public class PanelLaberinto extends JPanel   {
    private int[][] laberinto;
    private int filas, columnas;
    private int ladoCelda = 45; // Tamaño de cada celda en píxeles
    private int valorAlmacenado = 0;
    private Point posComienzo;
    private Point posFinal;
    private Color color;
    

    public PanelLaberinto(int[][] laberinto) {
        this.laberinto = laberinto;
        this.filas = laberinto.length;
        this.columnas = laberinto[0].length;
        

        addMouseListener(new MouseAdapter(){
        @Override
        public void mousePressed(MouseEvent e) {
            try {
                int fila = e.getX()/ladoCelda;
                int columna = e.getY()/ladoCelda;
                
                if(valorAlmacenado == 2){
                    if(posComienzo == null){
                        laberinto[fila][columna] = valorAlmacenado;
                        posComienzo = new Point(fila, columna);
                        valorAlmacenado =0;
                    }else{
                        laberinto[(int)posComienzo.getX()][(int)posComienzo.getY()] = 1;
                        laberinto[fila][columna] = valorAlmacenado;
                        posComienzo = new Point(fila, columna);
                        valorAlmacenado =0;
                    }
                }else if( valorAlmacenado == 3){
                    if(posFinal == null){
                        laberinto[fila][columna] = valorAlmacenado;
                        posFinal = new Point(fila, columna);
                        valorAlmacenado =0;
                    }else{
                        laberinto[(int)posFinal.getX()][(int)posFinal.getY()] = 1;
                        laberinto[fila][columna] = valorAlmacenado;
                        posFinal = new Point(fila, columna);
                        valorAlmacenado =0;
                    }
                }else{
                    if(laberinto[fila][columna] == 0){
                        laberinto[fila][columna] = 1;
                    }else{
                        if(laberinto[fila][columna] == 2 || laberinto[fila][columna] == 3){
                        }else{
                            laberinto[fila][columna] = valorAlmacenado;
                        }
                    }
                }
                
                
            } catch (Exception excepcion) {
                // TODO: handle exception
            }
             
            // Actualizar el estado de la celda seleccionada (por ejemplo, convertirla en pared)
            
            repaint(); // Vuelve a pintar el panel para reflejar el cambio
        }
    });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                g.setColor(Color.BLACK); // Pared
                if (laberinto[i][j] == 1) {
                    g.drawRect(i*ladoCelda, j*ladoCelda, ladoCelda, ladoCelda);
                } else if (laberinto[i][j] == 0) {
                     // Camino libre
                    g.fillRect(i*ladoCelda, j*ladoCelda, ladoCelda, ladoCelda);
                }else if (laberinto[i][j] == 2){
                    g.setColor(color = new Color(114,137,218));
                    g.fillRect(i*ladoCelda, j*ladoCelda, ladoCelda, ladoCelda);
                }else if (laberinto[i][j] == 3){
                    g.setColor(color = new Color(255,87,123));
                    g.fillRect(i*ladoCelda, j*ladoCelda, ladoCelda, ladoCelda);
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

}

