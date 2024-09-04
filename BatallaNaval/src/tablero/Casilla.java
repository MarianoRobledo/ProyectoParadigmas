/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tablero;

import piezas.Nave;

/**
 *
 * @author maria
 */
public class Casilla {
    private boolean agua;
    private boolean isla;
    private boolean hit;
    private boolean barcoAlLado;
    private Nave nave;
    
    public Casilla(){
        this.agua=true;
        this.isla=false;
        this.hit=false;
        this.barcoAlLado=false;
    }

    public void setNave(Nave nave) {
        this.nave = nave;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }
    
    public boolean isAgua() {
        return agua;
    }

    public boolean isIsla() {
        return isla;
    }

    public boolean isHit() {
        return hit;
    }

    public boolean isBarcoAlLado() {
        return barcoAlLado;
    }

    public Nave getNave() {
        return nave;
    }
    
    
}
