/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piezas;

import batallanaval.Jugador;

/**
 * Clase abstracta que representa una nave en el juego de batalla naval.
 * Contiene información común para todas las naves y métodos abstractos
 * que deben ser implementados por las subclases.
 * 
 * @author Mariano y Emiliano
 */
public abstract class Nave {
    
    protected int vida;//vida de la nave
    protected boolean poder;//indica si la nave puede usar su poder especial
    
    
    /**
     * Constructor de la nave con vida y podes
     * @param vida vida de la nave
     * @param poder indica si la nave puede usar su poder especial
     */
    public Nave(int vida, boolean poder){
        this.vida = vida;
        this.poder = poder;
    }
    
    /**
     * Reduce la vida de la nave en 1 y muestra un mensaje en caso que despues de recibir el daño
     * si esta averiado o si esta hundido
     */
    public void recibirDaño(){
        this.vida-=1;
        if(this.vida>0){
            System.out.println("");
            System.out.println("¡Averiado!");
            System.out.println("");
        }else{
            System.out.println("");
            System.out.println("¡Hundido!");
            System.out.println("");
        }
    }
    
    /**
     * Devuelve la vida restante de la nave.
     * 
     * @return La vida restante de la nave.
     */
    public int getVida() {
        return vida;
    }
    
    /**
     * Indica si la nave puede usar su poder especial.
     * 
     * @return True si la nave puede usar su poder, False en caso contrario.
     */
    public boolean isPoder() {
        return poder;
    }
    
    /**
     * Establece si la nave puede usar su poder especial.
     * 
     * @param poder True si la nave puede usar su poder, False en caso contrario.
     */    
    public void setPoder(boolean poder) {
        this.poder = poder;
    }
    
    /**
     * Método abstracto para usar el poder especial de la nave.
     * 
     * @param j1 El jugador que usa el poder.
     * @param j2 El jugador objetivo.
     * @return True si el ataque impacto sobre alguna nave, False en caso contrario.
     */
    public abstract boolean usarPoder(Jugador j1, Jugador j2);   
}
