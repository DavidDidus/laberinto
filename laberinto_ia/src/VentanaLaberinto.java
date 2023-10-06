import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaLaberinto {
    private Color color;
    private int[][] laberinto;
    private PanelLaberinto panelLaberinto;
    private boolean iniciar = false;
    private List<Point> camino;
    private JFrame frame;
    
    public VentanaLaberinto(int[][] laberinto){
        this.laberinto = laberinto;

    }
    public void iniciarVentana(){
        frame = new JFrame("Laberinto");
        frame.setSize(716, 490); // Tamaño del frame
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        panelLaberinto = new PanelLaberinto(laberinto);
        frame.getContentPane().add(panelLaberinto,BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize( new Dimension(250, frame.getWidth()));
        panel.setBackground(Color.gray);
        frame.getContentPane().add(panel,BorderLayout.EAST);   

        JButton botonInicio = new JButton("Seleccionar punto de partida");
        botonInicio.setBounds(1, 1,250,50);
        botonInicio.setBackground(color = new Color(44,47,51));
        botonInicio.setForeground(color = new Color(255,255,255));
        panel.add(botonInicio);
        botonInicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                panelLaberinto.setValor(2);
            }
        });

        JButton botonFinal = new JButton("Seleccionar punto final");
        botonFinal.setBounds(1, 75,250,50);
        botonFinal.setBackground(color = new Color(44,47,51));
        botonFinal.setForeground(color = new Color(255,255,255));
        panel.add(botonFinal);
        botonFinal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                panelLaberinto.setValor(3);
            }
        });
        JButton botonLimpiar = new JButton("Limpiar");
        botonLimpiar.setBounds(1, 400,250,50);
        botonLimpiar.setBackground(color = new Color(44,47,51));
        botonLimpiar.setForeground(color = new Color(255,255,255));
        panel.add(botonLimpiar);
        botonLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                panelLaberinto.limpiar();
            }
        });


        JButton botonIniciar = new JButton("Iniciar algoritmo");
        botonIniciar.setBounds(1, 150,250,50);
        botonIniciar.setBackground(color = new Color(44,47,51));
        botonIniciar.setForeground(color = new Color(255,255,255));
        panel.add(botonIniciar);
        botonIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                laberinto = panelLaberinto.getLaberinto();
                AEstrella aEstrella = new AEstrella(laberinto);
                camino = aEstrella.encontrarCamino(panelLaberinto.getPosComienzo(), panelLaberinto.getPosFinal());
                insertarCamino();
            }
            
        });
        frame.setVisible(true);  
        

    }
    public int [][] getLaberinto(){
        return panelLaberinto.getLaberinto();
    }
    public void insertarCamino(){
        for(Point coordenada : camino){
            laberinto[(int)coordenada.getX()][(int)coordenada.getY()] = 4;
        }
        panelLaberinto = new PanelLaberinto(laberinto);
        frame.getContentPane().add(panelLaberinto,BorderLayout.CENTER);
    }
}
