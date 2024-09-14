/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piezas;

import batallanaval.Jugador;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Mariano
 */
public class Submarino extends Nave{
    private final static int MAX_VIDA=3;

    public Submarino() {
        super(MAX_VIDA, true);
    }

    @Override
    public String toString() {
        return "Submarino{" + '}';
    }
    
    public boolean usarPoder(Jugador jugador1, Jugador jugadorEnemigo){
        String fila;
        String [] filas;
        int i;
        int columna;
        boolean flag=true;
        while(flag){
            System.out.println("");
            System.out.println("Dar las posicion a atacar");
            System.out.println("");    
            filas=jugador1.filas(jugador1.getTablero().getMapa().length);
            fila = jugadorEnemigo.darFila(filas, jugador1.getTablero().getMapa().length);
            columna = jugadorEnemigo.darColumna(jugador1.getTablero().getMapa().length);
            i=Arrays.asList(filas).indexOf(fila.toUpperCase());
            if (i == jugador1.getTablero().getMapa().length - 1) {
                if (columna == jugador1.getTablero().getMapa().length - 1) {
                    jugadorEnemigo.getTablero().getMapa()[i][columna].setSonar(true);
                    jugadorEnemigo.getTablero().getMapa()[i - 1][columna].setSonar(true);
                    jugadorEnemigo.getTablero().getMapa()[i][columna - 1].setSonar(true);
                    jugadorEnemigo.getTablero().getMapa()[i - 1][columna - 1].setSonar(true);
                } else {
                    jugadorEnemigo.getTablero().getMapa()[i][columna].setSonar(true);
                    jugadorEnemigo.getTablero().getMapa()[i - 1][columna].setSonar(true);
                    jugadorEnemigo.getTablero().getMapa()[i][columna + 1].setSonar(true);
                    jugadorEnemigo.getTablero().getMapa()[i - 1][columna + 1].setSonar(true);
                }
            } else {
                if (columna == jugador1.getTablero().getMapa().length - 1) {
                    jugadorEnemigo.getTablero().getMapa()[i][columna].setSonar(true);
                    jugadorEnemigo.getTablero().getMapa()[i + 1][columna].setSonar(true);
                    jugadorEnemigo.getTablero().getMapa()[i][columna - 1].setSonar(true);
                    jugadorEnemigo.getTablero().getMapa()[i + 1][columna - 1].setSonar(true);
                } else {
                    jugadorEnemigo.getTablero().getMapa()[i][columna].setSonar(true);
                    jugadorEnemigo.getTablero().getMapa()[i + 1][columna].setSonar(true);
                    jugadorEnemigo.getTablero().getMapa()[i][columna + 1].setSonar(true);
                    jugadorEnemigo.getTablero().getMapa()[i + 1][columna + 1].setSonar(true);
                }
            }
            this.poder=false;
            flag=false;
        }
        return false;
    }
    
}
