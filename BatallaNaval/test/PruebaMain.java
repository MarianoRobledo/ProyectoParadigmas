
import batallanaval.*;
import java.util.*;
import piezas.Nave;
import tablero.Casilla;
import tablero.Tablero;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author maria
 */
public class PruebaMain {

    public static void main(String[] args) {

        BatallaNaval b1 = new BatallaNaval();

        System.out.println("");
   //    b1.settearParametros();

        Tablero t1 = new Tablero(b1.getTamaño(), b1.getPortaaviones(), b1.getBuques(), b1.getSubmarinos(), b1.getCruceros(), b1.getLanchas());

//        for (int i = 0; i < t1.getMapa().length; i++) {
//            Casilla c1= t1.getMapa()[i][i];
//            c1.setIsla(true);
//            t1.setCasillaIsla(i, i, true);
//        }
//        
//        System.out.println(t1.getMapa()[0][0].isIsla());
//
//        t1.verTableroDePiezas();
//        String[] fila = new String[]{"A", "B", "C", "D", "E"};
// 
//        System.out.println("1 " + t1.getMapa()[Arrays.asList(fila).indexOf("A")][1].isAgua());
//        System.out.println("2 " + t1.getMapa()[Arrays.asList(fila).indexOf("A")][1].isBarcoAlLado());


        Jugador j1 = new Jugador("mariano", t1);
        j1.colocarNave();
        System.out.println("");
        t1.verTableroDePiezas();

    }
}

//
//this.tamaño = 10;
//        this.portaaviones = 1;
//        this.buques = 1;
//        this.submarinos = 1;
//        this.cruceros = 1;
//        this.lanchas = 1;
//    }
