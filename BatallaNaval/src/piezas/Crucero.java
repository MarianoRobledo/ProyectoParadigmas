/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piezas;

import batallanaval.Jugador;

/**
 * Clase que representa un buque el tablero.
 * @author Mariano y Emiliano
 */
public class Crucero extends Nave{
    private final static int MAX_VIDA=2;//Establece la vida inicial del Crucero
    
    /**
     * Constructor por defecto que establece la vida máxima y el poder desabilitado (No posee poder).
     */
    public Crucero() {
        super(MAX_VIDA, false);
    }

    @Override
    public boolean usarPoder(Jugador j1, Jugador j2){
        return false; //valor por defecto
    }
    
}
