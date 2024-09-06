
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
        b1.settearParametros();

        Tablero t1 = new Tablero(b1.getTamaño(), b1.getPortaaviones(), b1.getBuques(), b1.getSubmarinos(), b1.getCruceros(), b1.getLanchas());

        for (int i = 0; i < 9; i++) {
            Casilla c1= t1.getMapa()[i][i];
            c1.setIsla(true);
            t1.setCasillaIsla(i, i, true);
        }
        
        System.out.println(t1.getMapa()[0][0].isIsla());

        t1.verTableroDePiezas();
    }
}
