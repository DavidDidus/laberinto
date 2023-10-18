/**
 * @author David Jesus Rodriguez Serey
 *         21.220.555-9
 *         david.rodriguez03@alumnos.ucn.cl
 * Carrera: ITI
 * Profesor: Patricio Rojas Carrasco        
 * 
 * ======================================
 * 
 * En un principio el laberinto comienza vacio, al hacer click
 * 
 */

public class App {
     public static void main(String[] args) {
        int [][] laberinto = new Laberinto(10, 10).generarLaberinto();
        VentanaLaberinto ventana = new VentanaLaberinto(laberinto);
        ventana.iniciarVentana();       
    }
}