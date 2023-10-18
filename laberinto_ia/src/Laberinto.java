/**
 * Clase que crea el laberinto
 */
public class Laberinto {
    private int[][] laberinto;
    private int filas, columnas;
    
    /**
     * Crea un laberinto
     * @param filas
     * @param columnas
     */
    public Laberinto(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.laberinto = new int[filas][columnas];
    }
    /**
     * Genera el laberinto con las dimensiones en las que se instancio la clase
     * @return una matriz que representa el laberinto
     */
    public int[][] generarLaberinto() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) { 
                laberinto[i][j] = 1;
            }
        }
        return laberinto;
    }
}
