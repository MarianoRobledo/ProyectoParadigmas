/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piezas;

/**
 *
 * @author Mariano
 */
public abstract class Nave {

    protected int vida;

    public Nave() {
    }

    public Nave(int vida) {
        this.vida = vida;
    }
    
    

    public void RecibirDaño(){
        this.vida-=1;
    }
}
