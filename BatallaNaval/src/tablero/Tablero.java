/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tablero;

import java.util.*;
import piezas.*;

/**
 *
 * @author Mariano
 */
public class Tablero {

    private Casilla[][] mapa;
    private int cantidadNave;
    private List<Nave> naves;    
    private int barcosHundidos; 

    public Tablero() {
    }

    public Tablero(int tamaño, int portaavion, int buque, int submarino, int crucero, int lancha) {
        this.mapa = new Casilla[tamaño][tamaño];
        this.naves = new ArrayList<Nave>();
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                this.mapa[i][j] = new Casilla();
            }
        }
        // this.cantidadNave = cantidadNave;
        if (lancha > 0) {
            for (int i = 0; i < lancha; i++) {
                this.naves.add( new Lancha() );
            }
        }
        
        if (crucero > 0) {
            for (int i = 0; i < crucero; i++) {
                this.naves.add( new Crucero() );
            }
        }
        
        if (submarino > 0) {
            for (int i = 0; i < submarino; i++) {
                this.naves.add( new Submarino() );
            }
        }
        
        if (buque > 0) {
            for (int i = 0; i < buque; i++) {
                this.naves.add( new Buque() );
            }
        }
        
        if (portaavion > 0) {
            for (int i = 0; i < portaavion; i++) {
                this.naves.add( new Portaaviones() );
            }
        }        

   //     this.barcosHundidos = barcosHundidos;
    }

    public void verTableroDePiezas() { //en ves de pasar el tamaño usar el getmapa.length
        int tamanio = this.mapa.length;
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) { //Recorro mi tablero para que muestre las posiciones de los barcos e islas
                if (this.mapa[i][j].isAgua()){ //Si agua es True
                    System.out.print("[ ]");
                }else if(this.mapa[i][j].isIsla()){ //Si isla es True
                    System.out.print("[I]");
                }else if(this.mapa[i][j].isBarcoAlLado()){ //Si esta al lado de una nave
                    System.out.print("[-]");
                }else{//Si hay una nave
                    System.out.print("[N]");
                }
            }
            System.out.println("");
        }
    }

    public void verTableroDeTiros(Tablero tableroEnemigo) {
        int tamanio = this.mapa.length;
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) { //Recorro el tablero del enemigo para ver donde he dado tiros
                if (tableroEnemigo.getMapa()[i][j].isHit()){ //Si hit es True
                    if (tableroEnemigo.getMapa()[i][j].isAgua()){ //Si agua es True
                    System.out.print("[O]");
                    }else if(tableroEnemigo.getMapa()[i][j].isIsla()){ //Si isla es True
                        System.out.print("[O]");
                    }else{ //Si hay una nave
                        System.out.print("[X]");
                    }
                }else{ //Si no se disparo en esa casilla
                    System.out.print("[ ]");
                }
            }
            System.out.println("");
        }
    }

    public void marcarAtaque(int i, int j, Tablero tableroEnemigo) {
        tableroEnemigo.getMapa()[i][j].setHit(true); //Ingreso a la casilla elegida en el tablero enemigo y marco hit = true
        tableroEnemigo.getMapa()[i][j].getNave().RecibirDaño(); //Ingreso a la referencia de la nave de esa casilla y utilizo RecibirDaño()
    }

//    public void barcosASalvo(){ //Muestra en pantalla la cantidad de barcos de cada tipo aun a salvo
//        if (this.portaviones.length>0){
//            System.out.println("Portaviones: "+ this.portaviones.length);
//        }
//        if (this.buques.length>0){
//            System.out.println("Buques: "+ this.buques.length);
//        }
//        if (this.submarinos.length>0){
//            System.out.println("Submarinos: "+ this.submarinos.length);
//        }
//        if (this.cruceros.length>0){
//            System.out.println("Cruceros: "+ this.cruceros.length);
//        }
//        if (this.lanchas.length>0){
//            System.out.println("Lanchas: "+ this.lanchas.length);
//        }  
//    }

    public List<Nave> getNaves() {
        return naves;
    }

    public void setNaves(List<Nave> naves) {
        this.naves = naves;
    }

    public void setMapa(Casilla[][] mapa) {
        this.mapa = mapa;
    }

    public Casilla[][] getMapa() {
        return mapa;
    }

    public void setCasillaIsla(int i, int j, boolean t){
        this.mapa[i][j].setIsla(t);
    }
    
        

}
