import java.awt.BorderLayout;
import javax.swing.JFrame;


public class App {
     public static void main(String[] args) {
        LaberintoGenerador laberinto = new LaberintoGenerador(10, 10);
        int [][] laberintO = laberinto.generarLaberinto();

        JFrame frame = new JFrame("Laberinto");
        InterfazLaberinto panelLaberinto = new InterfazLaberinto(laberintO);

    
        frame.getContentPane().add(panelLaberinto, BorderLayout.CENTER);;
        frame.setSize(300, 300); // Tamaño del frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);  
    }
}