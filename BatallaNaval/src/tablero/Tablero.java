/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tablero;

import piezas.*;

/**
 *
 * @author Mariano
 */
public class Tablero {

    private Casilla[][] mapa;
    private int cantidadNave;
    private Portaaviones[] portaviones;
    private Buque[] buques;
    private Submarino[] submarinos;
    private Crucero[] cruceros;
    private Lancha[] lanchas;
    private int barcosHundidos; 

    public Tablero() {
    }

    public Tablero(int tamaño, int portaavion, int buque, int submarino, int crucero, int lancha) {
        this.mapa = new Casilla[tamaño][tamaño];
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                this.mapa[i][j] = new Casilla();
            }
        }
        // this.cantidadNave = cantidadNave;
        if (portaavion > 0) {
            this.portaviones = new Portaaviones[portaavion];
            for (int i = 0; i < portaavion; i++) {
                this.portaviones[i] = new Portaaviones();
            }
        }

        if (buque > 0) {
            this.buques = new Buque[buque];
            for (int i = 0; i < buque; i++) {
                this.buques[i] = new Buque();
            }
        }
        
        if (submarino > 0) {
            this.submarinos = new Submarino[submarino];
            for (int i = 0; i < submarino; i++) {
                this.submarinos[i] = new Submarino();
            }
        }
        
        if (crucero > 0) {
            this.cruceros = new Crucero[crucero];
            for (int i = 0; i < crucero; i++) {
                this.cruceros[i] = new Crucero();
            }
        }
        
        if (lancha > 0) {
            this.lanchas = new Lancha[lancha];
            for (int i = 0; i < lancha; i++) {
                this.lanchas[i] = new Lancha();
            }
        }

   //     this.barcosHundidos = barcosHundidos;
    }

    public Casilla[][] getMapa() {
        return mapa;
    }

    public void verTableroDePiezas(int tamanio) { 
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) { //Recorro mi tablero para que muestre las posiciones de los barcos e islas
                if (this.mapa[i][j].isAgua()){ //Si agua es True
                    System.out.print("[ ]");
                }else if(this.mapa[i][j].isIsla()){ //Si isla es True
                    System.out.print("[I]");
                }else{ //Si hay una nave
                    System.out.print("[N]");
                }
            }
            System.out.println("");
        }
    }

    public void verTableroDeTiros(int tamanio, Tablero tableroEnemigo) {
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

    public void barcosASalvo(){ //Muestra en pantalla la cantidad de barcos de cada tipo aun a salvo
        if (this.portaviones.length>0){
            System.out.println("Portaviones: "+ this.portaviones.length);
        }
        if (this.buques.length>0){
            System.out.println("Buques: "+ this.buques.length);
        }
        if (this.submarinos.length>0){
            System.out.println("Submarinos: "+ this.submarinos.length);
        }
        if (this.cruceros.length>0){
            System.out.println("Cruceros: "+ this.cruceros.length);
        }
        if (this.lanchas.length>0){
            System.out.println("Lanchas: "+ this.lanchas.length);
        }  
    }

}
