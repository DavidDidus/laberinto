import java.util.Random;
import java.util.Stack;

public class LaberintoGenerador {
    private int[][] laberinto;
    private int filas, columnas;
    private Random rand = new Random();

    public LaberintoGenerador(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.laberinto = new int[filas][columnas];
    }

    public int[][] generarLaberinto() {
        // Inicializa el laberinto con paredes.
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if(i==0){
                    laberinto[i][j] = 0;
                }
                laberinto[i][j] = 1; // 1 representa una pared
                System.out.println("Pared en: [" + i + "] ["+j+"]");
            }
        }

        /* 
        // Elige una celda inicial aleatoria y marca como camino.
        int startRow = rand.nextInt(filas);
        int startCol = rand.nextInt(columnas);
        laberinto[startRow][startCol] = 0; // 0 representa un camino

        Stack<Cell> stack = new Stack<>();
        stack.push(new Cell(startRow, startCol));

        while (!stack.isEmpty()) {
            Cell currentCell = stack.pop();
            int currentRow = currentCell.row;
            int currentCol = currentCell.col;
            int[] neighborDirs = {0, 1, 2, 3}; // Direcciones: arriba, derecha, abajo, izquierda
            shuffleArray(neighborDirs);

            for (int dir : neighborDirs) {
                int newRow = currentRow + deltaRow[dir];
                int newCol = currentCol + deltaCol[dir];

                if (isValid(newRow, newCol) && laberinto[newRow][newCol] == 1) {
                    laberinto[newRow][newCol] = 0; // Marca la celda vecina como camino.
                    laberinto[(currentRow + newRow) / 2][(currentCol + newCol) / 2] = 0; // Rompe la pared entre las celdas.
                    stack.push(new Cell(newRow, newCol));
                }
            }
        }
        */
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

    private boolean isValid(int row, int col) {
        return row >= 0 && row < filas && col >= 0 && col < columnas;
    }

    private void shuffleArray(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
    }

    private static class Cell {
        int row, col;

        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static final int[] deltaRow = {-1, 0, 1, 0};
    private static final int[] deltaCol = {0, 1, 0, -1};
}
