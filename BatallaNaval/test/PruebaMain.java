
import batallanaval.BatallaNaval;
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
        
        
        Tablero t= new Tablero(15,1,2,0,1,0);
        t.verTableroDePiezas();
        System.out.println("");
        t.insertarIslas();
        System.out.println("");
        t.verTableroDePiezas();
        t.barcosASalvo();
        t.barcosHundidos();
        
    }
}
