/**
 * @author David Jesus Rodriguez Serey
 *         21.220.555-9
 *         david.rodriguez03@alumnos.ucn.cl
 * Carrera: ITI
 * Profesor: Patricio Rojas Carrasco        
 * 
 * ======================================
 * 
 * En un principio el laberinto comienza vacio, al hacer click se inserta una pared en el laberinto,
 * si presiona pared esta se quita, para poner el punto de inicio hay que presionar el boton que dice 
 * "seleccionar punto de partida " y luego presionar una casilla donde se quiera poner el punto,
 * si quiere cambiar el punto solo tiene que apretar nuevamente el boton y presionar la casilla deseada
 * para poner el punto de meta funciona igual, se presiona el boton "seleccionar punto de meta" y luego se
 * presiona la casilla deseada.
 * Luego de tener el laberinto deseado se presiona el boton "iniciar algoritmo " para que el algoritmo inicie
 * y el camino se dibuje, luego de dibujado no se podra volver a interactuar con el laberinto hasta que se
 * presione el boton "limpiar".
 * 
 */

public class App {
     public static void main(String[] args) {
        int [][] laberinto = new Laberinto(10, 10).generarLaberinto();
        VentanaLaberinto ventana = new VentanaLaberinto(laberinto);
        ventana.iniciarVentana();       
    }
}