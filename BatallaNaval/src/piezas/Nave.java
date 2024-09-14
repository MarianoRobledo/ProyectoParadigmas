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
public abstract class Nave {

    protected int vida;
    protected boolean poder;
    
    public Nave() {
    }

    public Nave(int vida) {
        this.vida = vida;
    }
    
    public Nave(int vida, boolean poder){
        this.vida = vida;
        this.poder = poder;
    }
    

    public void RecibirDaño(){
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

    public int getVida() {
        return vida;
    }

    public boolean isPoder() {
        return poder;
    }

    public void setPoder(boolean poder) {
        this.poder = poder;
    }
    
    public abstract boolean usarPoder(Jugador jugador1, Jugador jugadorEnemigo);   
}
