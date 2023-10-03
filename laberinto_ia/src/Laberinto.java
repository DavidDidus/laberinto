public class Laberinto {
    private int[][] matrizLaberinto;

    public static final int PARED = 1;
    public static final int CAMINO_LIBRE = 0;
    public static final int PUNTO_INICIO = 2;
    public static final int PUNTO_DESTINO = 3;

    public Laberinto(int filas, int columnas) {
        matrizLaberinto = new int[filas][columnas];
    }

    public void setPared(int fila, int columna) {
        matrizLaberinto[fila][columna] = PARED;
    }

    public void setCaminoLibre(int fila, int columna) {
        matrizLaberinto[fila][columna] = CAMINO_LIBRE;
    }

    public void setPuntoInicio(int fila, int columna) {
        matrizLaberinto[fila][columna] = PUNTO_INICIO;
    }

    public void setPuntoDestino(int fila, int columna) {
        matrizLaberinto[fila][columna] = PUNTO_DESTINO;
    }

    public int getTipoCelda(int fila, int columna) {
        return matrizLaberinto[fila][columna];
    }
}

