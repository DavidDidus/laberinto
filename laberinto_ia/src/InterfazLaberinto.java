import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;

public class InterfazLaberinto extends JPanel   {
    private int[][] laberinto;
    private int filas, columnas;
    private int ladoCelda = 20; // Tamaño de cada celda en píxeles

    public InterfazLaberinto(int[][] laberinto) {
        this.laberinto = laberinto;
        this.filas = laberinto.length;
        this.columnas = laberinto[0].length;
        

        addMouseListener(new MouseAdapter(){
        @Override
        public void mousePressed(MouseEvent e) {
            int fila = e.getX()/ladoCelda;
            int columna = e.getY()/ladoCelda;
            if(fila > 10){
                fila =10;
            }         
            if(columna >10){
                columna =10;
            }   
            // Actualizar el estado de la celda seleccionada (por ejemplo, convertirla en pared)
            laberinto[fila][columna] = 0;
            
            repaint(); // Vuelve a pintar el panel para reflejar el cambio
        }
    });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (laberinto[i][j] == 1) {
                    g.setColor(Color.BLACK); // Pared
                } else if (laberinto[i][j] == 0) {
                    g.setColor(Color.WHITE); // Camino libre
                }
                g.drawRect((i)*ladoCelda, (j)*ladoCelda, ladoCelda, ladoCelda);
                
            }
        }
    }

}

