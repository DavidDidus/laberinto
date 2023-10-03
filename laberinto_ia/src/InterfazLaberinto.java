import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazLaberinto extends JPanel implements ActionListener {
    private int[][] laberinto;
    private int filas, columnas;
    private int ladoCelda = 20; // Tamaño de cada celda en píxeles

    public InterfazLaberinto(int[][] laberinto) {
        this.laberinto = laberinto;
        this.filas = laberinto.length;
        this.columnas = laberinto[0].length;

        Timer timer = new Timer(1000, this); // Temporizador para la animación (cada segundo)
        timer.start();
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
               // g.fillRect(j * ladoCelda, i * ladoCelda, 1, 1);

                g.drawRect((i+1)*ladoCelda, (j+1)*ladoCelda, (i)*ladoCelda, (j)*ladoCelda);
                
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Lógica de animación (si es necesario)
        repaint();
    }
}

