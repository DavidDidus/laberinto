public class Laberinto {
    private int[][] laberinto;
    private int filas, columnas;
    

    public Laberinto(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.laberinto = new int[filas][columnas];
    }

    public int[][] generarLaberinto() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) { 
                laberinto[i][j] = 1;
            }
        }
        return laberinto;
    }
}
