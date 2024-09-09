/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batallanaval;

import java.util.Scanner;
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
        
        
        while (juego) {
            System.out.println("----------------------------------------------------");
            System.out.println("Turno nº: " + ++turnos);
            System.out.println("Turno del jugador: "+b1.getJugador1().getNick());
            System.out.println("----------------------------------------------------");

            while (flag) {
                selector = verificadorDeNumero();
                if (selector == 1) {
                    System.out.println("");
                    System.out.println("Dar las posicion a atacar");
                    System.out.println("");    
                    
                    fila = b1.getJugador1().darFila(filas, b1.getTamaño());
                    columna = b1.getJugador1().darColumna(b1.getTamaño());
                    
                    valor = (b1.ataque(j2, fila, columna, filas));
                    if (valor) {
                        ++tirosAcertadosJ1;
                    } else {
                        flag = false;
                    }
                } else {
                    b1.getJugador1().getTablero().verTableroDePiezasEnJuego();
                    System.out.println("");
                    b1.getJugador1().getTablero().verTableroDeTiros(b1.getJugador2().getTablero());
                    System.out.println("");

                }
            }
            flag=true;
            System.out.println("----------------------------------------------------");
            System.out.println("Turno nº: " + ++turnos);
            System.out.println("Turno del jugador: "+b1.getJugador2().getNick());
            System.out.println("----------------------------------------------------");


            
            while (flag) {
                selector = verificadorDeNumero();
                if (selector == 1) {
                    System.out.println("");
                    System.out.println("Dar las posicion a atacar");
                    System.out.println("");    
                    
                    fila = b1.getJugador2().darFila(args, turnos);
                    columna = b1.getJugador2().darColumna(b1.getTamaño());
                    
                    valor = (b1.ataque(j1, fila, columna, filas));
                    if (valor) {
                        ++tirosAcertadosJ1;
                    } else {
                        flag = false;
                    }
                } else {
                    b1.getJugador1().getTablero().verTableroDePiezasEnJuego();
                    System.out.println("");
                    b1.getJugador1().getTablero().verTableroDeTiros(b1.getJugador2().getTablero());
                    System.out.println("");

                }
            }
            if(turnos<7){
                juego=false;
            }

        }
        if(tirosAcertadosJ1>tirosAcertadosJ2){
            System.out.println("gano jugador1");
        }else if(tirosAcertadosJ2>tirosAcertadosJ1){
            System.out.println("gano 2");
        }

    }

    public static int verificadorDeNumero() {
        Scanner r = new Scanner(System.in);
        int res = 0;
        boolean flag = true;

        while (flag) {
            try {
                System.out.println("Que quiere realizar:\n"
                        + "1: Atacar\n"
                        + "2: Ver Mapas");
                res = r.nextInt();

                if (res == 1 || res == 2) {
                    flag = false;
                }

            } catch (Exception e) {
                System.out.println("Dar un numero valido");
                r.reset();
            } finally {
                r.reset();
            }
        }
        return res;
    }

}
