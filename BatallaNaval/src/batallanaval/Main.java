/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batallanaval;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import piezas.Nave;
import tablero.Tablero;

/**
 *
 * @author maria
 */
public class Main {
    public static void main(String[] args) {

        Scanner r = new Scanner(System.in);
        String res, fila;
        int selector, columna;
        boolean flag = true;
        boolean juego = true;
        boolean valor;
        int tirosAcertadosJ1 = 0;
        int tirosAcertadosJ2 = 0;
        int turnos = 0;
        String[] filas;

        BatallaNaval b1 = new BatallaNaval();

        System.out.println("");
        b1.settearParametros();

        Tablero t1 = new Tablero(b1.getTamaño(), b1.getPortaaviones(), b1.getBuques(), b1.getSubmarinos(), b1.getCruceros(), b1.getLanchas());
        Tablero t2 = new Tablero(b1.getTamaño(), b1.getPortaaviones(), b1.getBuques(), b1.getSubmarinos(), b1.getCruceros(), b1.getLanchas());

        System.out.println("Dar el nombre del primer jugador:");
        res = r.nextLine();
        Jugador j1 = new Jugador(res, t1);
        r.reset();

        System.out.println("Dar el nombre del segundo jugador:");
        res = r.nextLine();
        Jugador j2 = new Jugador(res, t2);

        b1.setJugador1(j1);
        b1.setJugador2(j2);

        System.out.println(j1.getNick() + " coloque las naves en el tablero");
        System.out.println("----------------------------------------------------");
        j1.colocarNave();
        System.out.println("");
        System.out.println("----------------------------------------------------");

        System.out.println(j2.getNick() + " coloque las naves en el tablero");
        j2.colocarNave();

        filas = b1.getJugador1().filas(b1.getTamaño());
        
        
        System.out.println("");
        System.out.println("----------------------------------------------------");
        System.out.println("Comienza el juego");
        System.out.println("----------------------------------------------------");
        
        boolean ataqueDisponible;
        while (juego) {
            System.out.println("----------------------------------------------------");
            System.out.println("Turno nº: " + ++turnos);
            System.out.println("Turno del jugador: "+b1.getJugador1().getNick());
            System.out.println("----------------------------------------------------");
            flag=true;
            //TURNO DEL JUGADOR 1
            while (flag) {
                ataqueDisponible=false;
                selector = verificadorDeNumero();
                if (selector == 1) {
                    while(!ataqueDisponible){
                       System.out.println("");
                        System.out.println("Dar las posicion a atacar");
                        System.out.println("");    
                        
                        fila = b1.getJugador1().darFila(filas, b1.getTamaño());
                        columna = b1.getJugador1().darColumna(b1.getTamaño());
                        //verifica si la posicion elegida fue atacada
                        ataqueDisponible=esAtaqueValido(b1.getJugador2(),fila,columna,filas);
                        //si la posicion esta permitida, realizar el ataque
                        if (ataqueDisponible){
                            valor = (b1.ataque(j2, fila, columna, filas));
                            if (valor) {
                                ++tirosAcertadosJ1;
                                //verifica si el tiro dado destruyo toda la flota enemiga
                                if(!quedanBarcosASalvo(j2)){
                                    System.out.println("¡Has destruido todas las naves enemigas!");
                                    flag=false;
                                    juego=false;
                                }
                                
                            } else {
                                flag = false;
                            }
                        }else{
                            System.out.println("No es posible atacar esa posicion, elija otra");
                        }
                         
                    }
                }else if(selector == 3){
                    b1.getJugador1().getTablero().barcosASalvo();
                }else if (selector == 4){
                    b1.getJugador1().getTablero().barcosHundidos();
                } else {
                    b1.getJugador1().getTablero().verTableroDePiezasEnJuego();
                    System.out.println("");
                    b1.getJugador1().getTablero().verTableroDeTiros(b1.getJugador2().getTablero());
                    System.out.println("");

                }
            }
            if (juego==true){ //Si el jugador 1 aun no destruyo todas las naves
                flag=true;
                System.out.println("----------------------------------------------------");
                System.out.println("Turno nº: " + ++turnos);
                System.out.println("Turno del jugador: "+b1.getJugador2().getNick());
                System.out.println("----------------------------------------------------");
            }
            
            //TURNO DEL JUGADOR 2
            while (flag) {
                ataqueDisponible=false;
                selector = verificadorDeNumero();
                if (selector == 1) {
                    while(!ataqueDisponible){
                        System.out.println("");
                        System.out.println("Dar las posicion a atacar");
                        System.out.println("");    
                        
                        fila = b1.getJugador2().darFila(filas, b1.getTamaño());
                        columna = b1.getJugador2().darColumna(b1.getTamaño());
                        //verifica si la posicion elegida fue atacada
                        ataqueDisponible=esAtaqueValido(b1.getJugador1(),fila,columna,filas);
                        //si la posicion esta permitida, realizar el ataque
                        if (ataqueDisponible){
                            valor = (b1.ataque(j1, fila, columna, filas));
                            if (valor) {
                                ++tirosAcertadosJ2;
                                //verifica si el tiro dado destruyo toda la flota enemiga
                                if(!quedanBarcosASalvo(j1)){
                                    System.out.println("¡Has destruido todas las naves enemigas!");
                                    flag=false;
                                    juego=false;
                                }
                            } else {
                                flag = false;
                            } 
                        }else{
                            System.out.println("No es posible atacar esa posicion, elija otra");
                        }
                    }
                }else if(selector == 3){
                    b1.getJugador2().getTablero().barcosASalvo();
                }else if (selector == 4){
                    b1.getJugador2().getTablero().barcosHundidos();    
                } else {
                    b1.getJugador2().getTablero().verTableroDePiezasEnJuego();
                    System.out.println("");
                    b1.getJugador2().getTablero().verTableroDeTiros(b1.getJugador1().getTablero());
                    System.out.println("");

                }
            }
            if(turnos>7 && juego==true){
                System.out.println("Se acabaron los turnos");
                juego=false;
            }

        }
        if(tirosAcertadosJ1>tirosAcertadosJ2){
            System.out.println("gano jugador1");
        }else if(tirosAcertadosJ2>tirosAcertadosJ1){
            System.out.println("gano jugador2");
        }else{
            System.out.println("Empate");
        }

    }

    public static int verificadorDeNumero() {
        Scanner r = new Scanner(System.in);
        int res = 0;
        boolean flag = true;

        while (flag) {
            
                System.out.println("Que quiere realizar:\n"
                        + "1: Atacar\n"
                        + "2: Ver Mapas\n"
                        + "3: Ver Barcos a Salvo\n" 
                        + "4: Ver Barcos Hundidos\n");
                try{
                    res = r.nextInt();
                    r.nextLine();
                    if (res == 1 || res == 2 || res == 3 || res == 4) {
                        flag = false;
                    }
                } catch (Exception e) {
                    System.out.println("Dar un numero valido");
                    r.nextLine();
                } finally {
                    r.reset();
                }
        }
        return res;
    }
    
    private static boolean esAtaqueValido(Jugador jugador, String i, int j, String[] filas) {// valida si la casilla ha sido atacada
        return (jugador.getTablero().getMapa()[Arrays.asList(filas).indexOf(i.toUpperCase())][j].isHit()==false);
    }
    
    private static boolean quedanBarcosASalvo(Jugador jugador) {
        boolean hayBarcosASalvo = false;

        // Obtener la lista de naves del tablero del jugadorEnemigo
        List<Nave> naves = jugador.getTablero().getNaves();

        // Iterar sobre la lista de naves
        for (Nave nave : naves) {
            // Comprobar si la nave tiene vida (es decir, está a salvo)
            if (nave.getVida() > 0) {
                hayBarcosASalvo = true;
                break; // No es necesario continuar si ya encontramos un barco a salvo
            }
        }

        return hayBarcosASalvo;
    }
}
