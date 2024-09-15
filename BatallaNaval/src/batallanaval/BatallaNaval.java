/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package batallanaval;

import java.util.Arrays;
import java.util.Scanner;

/**
 * La clase BatallaNaval representa el juego de Batalla Naval. Gestiona los jugadores,
 * el tamaño del mapa, el número de turnos, y la cantidad de barcos disponibles para cada tipo.
 * 
 * Esta clase también proporciona métodos para modificar los parámetros del juego, atacar, y mostrar 
 * los parámetros actuales.
 * 
 * @author Mariano y Emiliano
 */
public class BatallaNaval {

    Scanner r = new Scanner(System.in);

    private Jugador jugador1;
    private Jugador jugador2;
    private int turnos;
    private int tamaño;
    private int portaaviones;
    private int buques;
    private int submarinos;
    private int cruceros;
    private int lanchas;

    /**
     * Constructor por defecto que inicializa los parámetros del juego con
     * valores predeterminados: - Turnos: 16 - Tamaño del mapa: 10x10 - 1
     * Portaaviones, 1 Buque, 1 Submarino, 1 Crucero y 1 Lancha
     */
    public BatallaNaval() {
        this.turnos = 16;
        this.tamaño = 10;
        this.portaaviones = 1;
        this.buques = 1;
        this.submarinos = 1;
        this.cruceros = 1;
        this.lanchas = 1;
    }

    /**
     * Realiza un ataque a una posición en el tablero de un jugador.
     *
     * @param jugador El jugador al que se le realiza el ataque.
     * @param fila La fila en la que se realizará el ataque.
     * @param columna La columna en la que se realizará el ataque.
     * @param filas El arreglo de filas del tablero.
     * @return true si el ataque fue exitoso, false en caso contrario.
     */
    public boolean ataque(Jugador jugador, String fila, int columna, String[] filas) {
        return jugador.getTablero().marcarAtaque(Arrays.asList(jugador.filas(jugador.getTablero().getMapa().length)).indexOf(fila), columna, jugador.getTablero());
    }

    /**
     * Permite al usuario configurar parámetros del juego, como el tamaño del
     * mapa o la disposición de barcos.
     */
    public void settearParametros() {
        int res;
        boolean flag = true;

        while (flag) {
            try {
                System.out.println("Que quieres modificar\n"
                        + "1: Tamaño del mapa\n"
                        + "2: Disposicion de barcos,\n"
                        + "3: Ver las naves actuales\n"
                        + "4: Volver al Menu");

                res = r.nextInt();
                r.nextLine();//para que no haya problema con el buffer

                switch (res) {
                    case 1:
                        System.out.println("");
                        settTamaño();
                        r.reset();
                        System.out.println("");
                        break;
                    case 2:
                        System.out.println("");
                        settNaves();
                        r.reset();
                        System.out.println("");
                        break;
                    case 3:
                        System.out.println("");
                        System.out.println("");
                        System.out.println(mostrarParametros());
                        System.out.println("");
                        System.out.println("");
                        r.reset();
                        break;
                    case 4:
                        flag = false;
                        break;
                    default:
                        System.out.println("Dar un numero correcto");
                        System.out.println("");
                        r.reset();
                        break;
                }

            } catch (Exception e) {
                System.out.println("");
                System.out.println("Dar un numero valido");
                System.out.println("");
                r.nextLine();
                r.reset();
            }
        }

    }

    /**
     * Configura el tamaño del mapa del juego en base a opciones predefinidas.
     */
    private void settTamaño() {
        boolean flag = true;
        String res;
        while (flag) {
            r.reset();
            try {
                System.out.println("");
                System.out.println("Elegir entre la opcion A= 5x5, B= 10x10, C= 15x15 ");
                res = r.nextLine();
                switch (res.toLowerCase()) {
                    case "a":
                        this.turnos = 10;
                        this.tamaño = 5;
                        settNaves();
                        flag = false;
                        break;
                    case "b":
                        this.turnos = 16;
                        this.tamaño = 10;
                        settNaves();
                        flag = false;
                        break;
                    case "c":
                        this.turnos = 20;
                        this.tamaño = 15;
                        settNaves();
                        flag = false;
                        break;
                    default:
                        System.out.println("Intentar con una letra correcta");
                        System.out.println("");
                        break;

                }
                System.out.println("");
            } catch (Exception e) {
                System.out.println("");
                System.out.println("Dar una letra valida");
                System.out.println("");
            }
        }

    }

    /**
     * Configura la disposición de las naves en función del tamaño del mapa.
     */
    private void settNaves() {
        boolean flag = true;
        String res;
        while (flag) {
            try {
                System.out.println("");
                if (this.tamaño == 5) {
                    System.out.println("Elegir combinacion de barcos:\n"
                            + "A: 1 Portaavion, 1 Crucero;\n"
                            + "B: 1 Buque, 1 Crucero;\n"
                            + "C: 1 Submarino, 1 Lancha");
                    res = r.nextLine();
                    switch (res.toLowerCase()) {
                        case "a":
                            this.portaaviones = 1;
                            this.buques = 0;
                            this.submarinos = 0;
                            this.cruceros = 1;
                            this.lanchas = 0;
                            flag = false;
                            break;
                        case "b":
                            this.portaaviones = 0;
                            this.buques = 1;
                            this.submarinos = 0;
                            this.cruceros = 1;
                            this.lanchas = 0;
                            flag = false;
                            break;
                        case "c":
                            this.portaaviones = 0;
                            this.buques = 0;
                            this.submarinos = 1;
                            this.cruceros = 0;
                            this.lanchas = 1;
                            flag = false;
                            break;
                        default:
                            System.out.println("Dar una letra correcta");
                            System.out.println("");
                    }
                } else if (this.tamaño == 10) {
                    System.out.println("Elegir combinacion de barcos:\n"
                            + "A: 1 Portaavion, 1 Buque, 1 Submarino, 1 Crucero, 1 Lancha;\n"
                            + "B: 2 Portaaviones, 1 Submarinos, 2 Lanchas;\n"
                            + "C: 1 Buques, 1 Submarino, 1 Crucero, 2 Lanchas");
                    res = r.nextLine();
                    switch (res.toLowerCase()) {
                        case "a":
                            this.portaaviones = 1;
                            this.buques = 1;
                            this.submarinos = 1;
                            this.cruceros = 1;
                            this.lanchas = 1;
                            flag = false;
                            break;
                        case "b":
                            this.portaaviones = 2;
                            this.buques = 0;
                            this.submarinos = 1;
                            this.cruceros = 0;
                            this.lanchas = 2;
                            flag = false;
                            break;
                        case "c":
                            this.portaaviones = 0;
                            this.buques = 1;
                            this.submarinos = 1;
                            this.cruceros = 1;
                            this.lanchas = 2;
                            flag = false;
                            break;
                        default:
                            System.out.println("Dar una letra correcta");
                            System.out.println("");
                    }

                } else {
                    System.out.println("Elegir combinacion de barcos:\n"
                            + "A: 1 Portaaviones, 1 Buque, 2 Submarinos, 1 Crucero, 2 Lanchas;\n"
                            + "B: 1 Portaavion, 2 Buques; 2 Submarinos, 2 Lanchas;\n"
                            + "C: 2 Buques, 1 Submarino, 2 Cruceros, 2 Lanchas");
                    res = r.nextLine();
                    switch (res.toLowerCase()) {
                        case "a":
                            this.portaaviones = 1;
                            this.buques = 1;
                            this.submarinos = 2;
                            this.cruceros = 1;
                            this.lanchas = 2;
                            flag = false;
                            break;
                        case "b":
                            this.portaaviones = 1;
                            this.buques = 2;
                            this.submarinos = 2;
                            this.cruceros = 0;
                            this.lanchas = 2;
                            flag = false;
                            break;
                        case "c":
                            this.portaaviones = 0;
                            this.buques = 2;
                            this.submarinos = 1;
                            this.cruceros = 2;
                            this.lanchas = 2;
                            flag = false;
                            break;
                        default:
                            System.out.println("Dar una letra correcta");
                    }
                }
                System.out.println("");
            } catch (Exception e) {
                System.out.println("");
                System.out.println("Dar una letra valida");
                System.out.println("");
            }
        }

    }

    /**
     * Muestra los parámetros actuales del juego, incluyendo el tamaño del mapa
     * y la cantidad de cada tipo de nave.
     *
     * @return Una cadena de texto con los parámetros del juego, incluyendo el
     * tamaño del mapa, la cantidad de portaaviones, buques, submarinos,
     * cruceros y lanchas.
     */
    public String mostrarParametros() {
        return "Tamaño del mapa: " + this.tamaño + "x" + this.tamaño + "\n"
                + "Cantidad de Portaaviones: " + this.portaaviones + "\n"
                + "Cantidad de Buques: " + this.buques + "\n"
                + "Cantidad de Submarinos: " + this.submarinos + "\n"
                + "Cantidad de Cruceros: " + this.cruceros + "\n"
                + "Cantidad de Lanchas: " + this.lanchas;
    }

    /**
     * Obtiene el jugador 1.
     *
     * @return El objeto Jugador correspondiente al jugador 1.
     */
    public Jugador getJugador1() {
        return jugador1;
    }

    /**
     * Establece el jugador 1.
     *
     * @param jugador1 El objeto Jugador que será asignado como jugador 1.
     */
    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;

    }

    /**
     * Obtiene el jugador 2.
     *
     * @return El objeto Jugador correspondiente al jugador 2.
     */
    public Jugador getJugador2() {
        return jugador2;
    }

    /**
     * Establece el jugador 2.
     *
     * @param jugador2 El objeto Jugador que será asignado como jugador 2.
     */
    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }

    /**
     * Obtiene el tamaño actual del mapa del juego.
     *
     * @return Un entero que representa el tamaño del mapa.
     */
    public int getTamaño() {
        return tamaño;
    }

    /**
     * Establece el tamaño del mapa del juego.
     *
     * @param tamaño Un entero que representa el nuevo tamaño del mapa.
     */
    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    /**
     * Obtiene la cantidad de portaaviones en el juego.
     *
     * @return Un entero que representa la cantidad de portaaviones.
     */
    public int getPortaaviones() {
        return portaaviones;
    }

    /**
     * Establece la cantidad de portaaviones en el juego.
     *
     * @param portaaviones Un entero que representa la nueva cantidad de
     * portaaviones.
     */
    public void setPortaaviones(int portaaviones) {
        this.portaaviones = portaaviones;
    }

    /**
     * Obtiene la cantidad de buques en el juego.
     *
     * @return Un entero que representa la cantidad de buques.
     */
    public int getBuques() {
        return buques;
    }

    /**
     * Establece la cantidad de buques en el juego.
     *
     * @param buques Un entero que representa la nueva cantidad de buques.
     */
    public void setBuques(int buques) {
        this.buques = buques;
    }

    /**
     * Obtiene la cantidad de submarinos en el juego.
     *
     * @return Un entero que representa la cantidad de submarinos.
     */
    public int getSubmarinos() {
        return submarinos;
    }

    /**
     * Establece la cantidad de submarinos en el juego.
     *
     * @param submarinos Un entero que representa la nueva cantidad de
     * submarinos.
     */
    public void setSubmarinos(int submarinos) {
        this.submarinos = submarinos;
    }

    /**
     * Obtiene la cantidad de cruceros en el juego.
     *
     * @return Un entero que representa la cantidad de cruceros.
     */
    public int getCruceros() {
        return cruceros;
    }

    /**
     * Establece la cantidad de cruceros en el juego.
     *
     * @param cruceros Un entero que representa la nueva cantidad de cruceros.
     */
    public void setCruceros(int cruceros) {
        this.cruceros = cruceros;
    }

    /**
     * Obtiene la cantidad de lanchas en el juego.
     *
     * @return Un entero que representa la cantidad de lanchas.
     */
    public int getLanchas() {
        return lanchas;
    }

    /**
     * Establece la cantidad de lanchas en el juego.
     *
     * @param lanchas Un entero que representa la nueva cantidad de lanchas.
     */
    public void setLanchas(int lanchas) {
        this.lanchas = lanchas;
    }

    /**
     * Obtiene el número de turnos disponibles en el juego.
     *
     * @return Un entero que representa el número de turnos.
     */
    public int getTurnos() {
        return turnos;
    }

}
