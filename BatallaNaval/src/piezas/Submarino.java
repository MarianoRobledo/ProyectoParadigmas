/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piezas;

import batallanaval.Jugador;
import java.util.Arrays;

/**
 * Clase que representa un submarino el tablero.
 * @author Mariano y Emiliano
 */
public class Submarino extends Nave{
    private final static int MAX_VIDA=3;//Establece la vida inicial del submarino
    
    /**
     * Constructor por defecto que establece la vida máxima y el poder activo.
     */
    public Submarino() {
        super(MAX_VIDA, true);
    }

    @Override
    public String toString() {
        return "Submarino{" + '}';
    }
    
    @Override
    public boolean usarPoder(Jugador j1, Jugador j2){
        String fila;
        String [] filas;
        int i;
        int columna;
        boolean flag=true;
        while(flag){
            System.out.println("");
            System.out.println("Dar las posicion a atacar");
            System.out.println("");    
            filas=j1.filas(j1.getTablero().getMapa().length);
            fila = j2.darFila(filas, j1.getTablero().getMapa().length);
            columna = j2.darColumna(j1.getTablero().getMapa().length);
            i=Arrays.asList(filas).indexOf(fila.toUpperCase());
            if (i == j1.getTablero().getMapa().length - 1) {
                if (columna == j1.getTablero().getMapa().length - 1) {
                    j2.getTablero().getMapa()[i][columna].setSonar(true);
                    j2.getTablero().getMapa()[i - 1][columna].setSonar(true);
                    j2.getTablero().getMapa()[i][columna - 1].setSonar(true);
                    j2.getTablero().getMapa()[i - 1][columna - 1].setSonar(true);
                } else {
                    j2.getTablero().getMapa()[i][columna].setSonar(true);
                    j2.getTablero().getMapa()[i - 1][columna].setSonar(true);
                    j2.getTablero().getMapa()[i][columna + 1].setSonar(true);
                    j2.getTablero().getMapa()[i - 1][columna + 1].setSonar(true);
                }
            } else {
                if (columna == j1.getTablero().getMapa().length - 1) {
                    j2.getTablero().getMapa()[i][columna].setSonar(true);
                    j2.getTablero().getMapa()[i + 1][columna].setSonar(true);
                    j2.getTablero().getMapa()[i][columna - 1].setSonar(true);
                    j2.getTablero().getMapa()[i + 1][columna - 1].setSonar(true);
                } else {
                    j2.getTablero().getMapa()[i][columna].setSonar(true);
                    j2.getTablero().getMapa()[i + 1][columna].setSonar(true);
                    j2.getTablero().getMapa()[i][columna + 1].setSonar(true);
                    j2.getTablero().getMapa()[i + 1][columna + 1].setSonar(true);
                }
            }
            this.poder=false;
            flag=false;
        }
        return false;
    }
    
}
