/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tablero;

import batallanaval.piezas.*;

/**
 *
 * @author Mariano
 */
public class Tablero {
    private int[][] mapa;//por el momento lo dejamos como un int pero deberia de ser algo relacionados con las piezas por polimorfismo
    private int cantidadNave;
    private Portaaviones[] portaviones;
    private Buque[] buques;
    private Submarino[] submarino;
    private Crucero[] crucero;
    private Lancha[] lancha;
    private int barcosHundidos; // es un contador

    public Tablero() {
    }

    public Tablero(int[][] mapa, int cantidadNave, Portaaviones[] portaviones, Buque[] buques, Submarino[] submarino, Crucero[] crucero, Lancha[] lancha, int barcosHundidos) {
        this.mapa = mapa;
        this.cantidadNave = cantidadNave;
        this.portaviones = portaviones;
        this.buques = buques;
        this.submarino = submarino;
        this.crucero = crucero;
        this.lancha = lancha;
        this.barcosHundidos = barcosHundidos;
    }

    public int[][] getMapa() {
        return mapa;
    }

    public void setMapa(int[][] mapa) {
        this.mapa = mapa;
    }

    public int getCantidadNave() {
        return cantidadNave;
    }

    public void setCantidadNave(int cantidadNave) {
        this.cantidadNave = cantidadNave;
    }

    public Portaaviones[] getPortaviones() {
        return portaviones;
    }

    public void setPortaviones(Portaaviones[] portaviones) {
        this.portaviones = portaviones;
    }

    public Buque[] getBuques() {
        return buques;
    }

    public void setBuques(Buque[] buques) {
        this.buques = buques;
    }

    public Submarino[] getSubmarino() {
        return submarino;
    }

    public void setSubmarino(Submarino[] submarino) {
        this.submarino = submarino;
    }

    public Crucero[] getCrucero() {
        return crucero;
    }

    public void setCrucero(Crucero[] crucero) {
        this.crucero = crucero;
    }

    public Lancha[] getLancha() {
        return lancha;
    }

    public void setLancha(Lancha[] lancha) {
        this.lancha = lancha;
    }

    public int getBarcosHundidos() {
        return barcosHundidos;
    }

    public void setBarcosHundidos(int barcosHundidos) {
        this.barcosHundidos = barcosHundidos;
    }
    
    public void colocarNave(){
        
    }
    
    public void tiroEnemigo(){
        
    }
    
    public void marcarAtaque(){
        
    }
}
