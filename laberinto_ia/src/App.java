public class App {
     public static void main(String[] args) {
        int [][] laberinto = new LaberintoGenerador(10, 10).generarLaberinto();
        VentanaLaberinto ventana = new VentanaLaberinto(laberinto);
        ventana.iniciarVentana();

    }
}