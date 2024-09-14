/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piezas;

import batallanaval.Jugador;

/**
 *
 * @author Mariano
 */
public class Crucero extends Nave{
    private final static int MAX_VIDA=2;

    public Crucero() {
        super(MAX_VIDA, false);
    }

    @Override
    public String toString() {
        return "Crucero{" + '}';
    }
    
    public boolean usarPoder(Jugador jugador1, Jugador jugadorEnemigo){
        return false; //valor por defecto
    }
    
}
