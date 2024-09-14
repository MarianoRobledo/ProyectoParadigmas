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
    private boolean sonar;
    private Nave nave;
    
    public Casilla(){
        this.agua=true;
        this.isla=false;
        this.hit=false;
        this.barcoAlLado=false;
        this.sonar=false;
    }

    public void setNave(Nave nave) {
        this.nave = nave;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public void setAgua(boolean agua) {
        this.agua = agua;
    }

    public void setIsla(boolean isla) {
        this.isla = isla;
    }

    public void setBarcoAlLado(boolean barcoAlLado) {
        this.barcoAlLado = barcoAlLado;
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

    public boolean isSonar() {
        return sonar;
    }

    public void setSonar(boolean sonar) {
        this.sonar = sonar;
    }
    
    
}
