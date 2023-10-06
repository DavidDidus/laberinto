public class Laberinto {
    private int[][] laberinto;
    private int filas, columnas;
    

    public Laberinto(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.laberinto = new int[filas][columnas];
    }

    public int[][] generarLaberinto() {
        // Inicializa el laberinto con paredes.
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) { 
                laberinto[i][j] = 1; // 1 representa una pared
            }
        }

        verLaberinto();

        return laberinto;
    }
    private void verLaberinto(){
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.println("Valor " + laberinto[i][j] + " en: [" + i + "] ["+j+"]");
            }
        }
    }

}
