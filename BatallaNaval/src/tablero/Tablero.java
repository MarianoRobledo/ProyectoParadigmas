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

    private Casilla[][] mapa;//por el momento lo dejamos como un int pero deberia de ser algo relacionados con las piezas por polimorfismo
    private int cantidadNave;
    private Portaaviones[] portaviones;
    private Buque[] buques;
    private Submarino[] submarinos;
    private Crucero[] cruceros;
    private Lancha[] lanchas;
    private int barcosHundidos; // es un contador

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

    

    public void colocarNave() {

    }

    public void tiroEnemigo() {

    }

    public void marcarAtaque() {

    }
}
