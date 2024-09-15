/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tablero;

import piezas.Nave;

/**
 * La clase Casilla representa una casilla en el tablero del juego de Batalla Naval.
 * Cada casilla puede contener información sobre si es agua, isla, ha sido impactada,
 * si hay un barco al lado, si se ha detectado con sonar, y si tiene una nave asignada.
 */
public class Casilla {
    private boolean agua;
    private boolean isla;
    private boolean hit;
    private boolean barcoAlLado;
    private boolean sonar;
    private Nave nave;
    
     /**
     * Constructor por defecto que inicializa la casilla con agua,
     * sin isla, sin impacto, sin barco al lado, y sin detección de sonar.
     * 
     * @author Mariano y Emiliano
     */
    public Casilla(){
        this.agua=true;
        this.isla=false;
        this.hit=false;
        this.barcoAlLado=false;
        this.sonar=false;
    }

        /**
     * Establece la nave en la casilla.
     * 
     * @param nave La nave a asignar a la casilla.
     */

    public void setNave(Nave nave) {
        this.nave = nave;
    }

    /**
     * Establece el estado de impacto de la casilla.
     * 
     * @param hit {@code true} si la casilla ha sido impactada, {@code false} en caso contrario.
     */
    public void setHit(boolean hit) {
        this.hit = hit;
    }

       /**
     * Establece el estado de agua de la casilla.
     * 
     * @param agua {@code true} si la casilla contiene agua, {@code false} en caso contrario.
     */
    public void setAgua(boolean agua) {
        this.agua = agua;
    }

   
    /**
     * Establece el estado de isla de la casilla.
     * 
     * @param isla {@code true} si la casilla contiene una isla, {@code false} en caso contrario.
     */ 
    public void setIsla(boolean isla) {
        this.isla = isla;
    }

      /**
     * Establece el estado de barco al lado de la casilla.
     * 
     * @param barcoAlLado {@code true} si hay un barco al lado de la casilla, {@code false} en caso contrario.
     */
    public void setBarcoAlLado(boolean barcoAlLado) {
        this.barcoAlLado = barcoAlLado;
    }
    
        /**
     * Devuelve {@code true} si la casilla contiene agua, {@code false} en caso contrario.
     * 
     * @return {@code true} si la casilla contiene agua.
     */
    public boolean isAgua() {
        return agua;
    }

     /**
     * Devuelve {@code true} si la casilla contiene una isla, {@code false} en caso contrario.
     * 
     * @return {@code true} si la casilla contiene una isla.
     */
    public boolean isIsla() {
        return isla;
    }

    /**
     * Devuelve {@code true} si la casilla ha sido impactada, {@code false} en caso contrario.
     * 
     * @return {@code true} si la casilla ha sido impactada.
     */
    public boolean isHit() {
        return hit;
    }

     /**
     * Devuelve {@code true} si hay un barco al lado de la casilla, {@code false} en caso contrario.
     * 
     * @return {@code true} si hay un barco al lado de la casilla.
     */
    public boolean isBarcoAlLado() {
        return barcoAlLado;
    }

     /**
     * Devuelve la nave que está ubicada en la casilla.
     * 
     * @return La nave en la casilla, o {@code null} si no hay ninguna nave.
     */
    public Nave getNave() {
        return nave;
    }

     /**
     * Devuelve {@code true} si la casilla ha sido detectada con sonar, {@code false} en caso contrario.
     * 
     * @return {@code true} si la casilla ha sido detectada con sonar.
     */
    public boolean isSonar() {
        return sonar;
    }

    
    /**
     * Establece el estado de detección de sonar de la casilla.
     * 
     * @param sonar {@code true} si la casilla ha sido detectada con sonar, {@code false} en caso contrario.
     */
    public void setSonar(boolean sonar) {
        this.sonar = sonar;
    }
    
    
}
