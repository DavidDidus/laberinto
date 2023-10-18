import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Clase que representa la ventana en la que se mostrara el laberinto
 */
public class VentanaLaberinto {
    private Color color;
    private int[][] laberinto;
    private PanelLaberinto panelLaberinto;
    private List<Point> camino;
    private JFrame frame;
    private Timer timer;
    
    public VentanaLaberinto(int[][] laberinto){
        this.laberinto = laberinto;

        timer = new Timer(500, new ActionListener() { // delay para que la insercion del camino sea mas estetica
            private int indice = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (indice < camino.size()-1) {
                    Point coordenada = camino.get(indice);
                    panelLaberinto.insertarCamino((int)coordenada.getX(), (int)coordenada.getY());
                    indice++;
                } else {
                    indice=0;
                    ((Timer) timer).stop();
                }
            }
        });

    }
    /**
     * Metodo que inicia la ventana con el laberinto
     */
    public void iniciarVentana(){
        /* Creacion del frame */
        frame = new JFrame("Laberinto");
        frame.setSize(716, 490); // Tamaño del frame
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        /* Creacion del panel laberinto */
        panelLaberinto = new PanelLaberinto(laberinto);
        frame.getContentPane().add(panelLaberinto,BorderLayout.CENTER);
        
        /* Creacion de panel de los botones */
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize( new Dimension(250, frame.getWidth()));
        panel.setBackground(Color.gray);
        frame.getContentPane().add(panel,BorderLayout.EAST);   
        
        /* Creacion de label que indica que el laberinto no tiene solucion */
        JLabel mensajeNoSolucion = new JLabel("El laberinto no tiene solución.");
        mensajeNoSolucion.setBounds(1, 300, 250, 50);
        mensajeNoSolucion.setForeground(color = new Color(255,255,255));
        mensajeNoSolucion.setVisible(false);
        panel.add(mensajeNoSolucion);

        /* Creacion de label que indica presionar limpiar */
        JLabel textoLimpiar = new JLabel("Presione limpiar para continuar");
        textoLimpiar.setBounds(1, 350, 250, 50);
        textoLimpiar.setForeground(color = new Color(255,255,255));
        textoLimpiar.setVisible(false);
        panel.add(textoLimpiar);

        /* Creacion del boton para seleccionar el punto de partida */
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

        /* Creacion del boton para seleccionar la meta */
        JButton botonFinal = new JButton("Seleccionar punto de meta");
        botonFinal.setBounds(1, 75,250,50);
        botonFinal.setBackground(color = new Color(44,47,51));
        botonFinal.setForeground(color = new Color(255,255,255));
        panel.add(botonFinal);
        botonFinal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                panelLaberinto.setValor(3);
            }
        });
        
        /* Creacion del boton para limpiar el laberinto */
        JButton botonLimpiar = new JButton("Limpiar");
        botonLimpiar.setBounds(1, 400,250,50);
        botonLimpiar.setBackground(color = new Color(44,47,51));
        botonLimpiar.setForeground(color = new Color(255,255,255));
        panel.add(botonLimpiar);
        botonLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                panelLaberinto.limpiar();
                panelLaberinto.setDetener(true);
                textoLimpiar.setVisible(false);
            }
        });

        /* Creacion del boton para iniciar el algoritmo */
        JButton botonIniciar = new JButton("Iniciar algoritmo");
        botonIniciar.setBounds(1, 150,250,50);
        botonIniciar.setBackground(color = new Color(44,47,51));
        botonIniciar.setForeground(color = new Color(255,255,255));
        panel.add(botonIniciar);
        botonIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                laberinto = panelLaberinto.getLaberinto();      // obtiene el laberinto
                AEstrella aEstrella = new AEstrella(laberinto); // instancia la clase que realiza el algoritmo
                camino = aEstrella.encontrarCamino(panelLaberinto.getPosComienzo(), panelLaberinto.getPosFinal()); // inicio del algoritmo
                if(camino != null){
                    mensajeNoSolucion.setVisible(false);
                    textoLimpiar.setVisible(true);
                    insertarCamino();                           // se dibuja el camino en el panel
                    panelLaberinto.setDetener(false);
                }else{
                    mensajeNoSolucion.setVisible(true);  
                }
            }            
        });
        
        frame.setVisible(true);  
    }
    /**
     * Retorna el laberinto
     * @return la matriz que representa el laberinto
     */
    public int [][] getLaberinto(){
        return panelLaberinto.getLaberinto();
    }
    /**
     * Inserta el camino dentro del laberino
     */
    public void insertarCamino() {
        timer.start();      // Delay para que el camino sea insertado de forma mas estetica
        
    }
}
