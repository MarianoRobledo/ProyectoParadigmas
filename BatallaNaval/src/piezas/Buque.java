/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piezas;

import batallanaval.Jugador;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Mariano
 */
public class Buque extends Nave{
    private final static int MAX_VIDA=4;

    public Buque() {
        super(MAX_VIDA, true);
    }

    @Override
    public String toString() {
        return "Buque{" + '}';
    }
    
    public boolean usarPoder(Jugador jugador1, Jugador jugadorEnemigo){
        Scanner r = new Scanner(System.in);
        String res;
        String fila;
        String [] filas;
        int i;
        int columna;
        boolean acertoAtaque=false;
        boolean flag=true;
        while(flag){
            System.out.println("");
            System.out.println("Dar las posicion a atacar");
            System.out.println("");    
            filas=jugador1.filas(jugador1.getTablero().getMapa().length);
            fila = jugadorEnemigo.darFila(filas, jugador1.getTablero().getMapa().length);
            columna = jugadorEnemigo.darColumna(jugador1.getTablero().getMapa().length);
            i=Arrays.asList(filas).indexOf(fila.toUpperCase());
            res="";
            if (i<jugador1.getTablero().getMapa().length-1 && columna<jugador1.getTablero().getMapa().length-1){
                while (!res.equals("1") && !res.equals("2")){
                    System.out.println("1: Atacar hacia abajo\n" +
                                   "2: Atacar hacia la derecha");
                    res=r.nextLine();
                    if (res.equals("1")){
                        if (jugadorEnemigo.getTablero().getMapa()[i][columna].isHit()==false && jugadorEnemigo.getTablero().getMapa()[i][columna].getNave() != null){
                            acertoAtaque=true;
                            jugador1.incrementarTiros();
                        }
                        jugadorEnemigo.getTablero().marcarAtaque(i, columna, jugadorEnemigo.getTablero());
                        if (jugadorEnemigo.getTablero().getMapa()[i+1][columna].isHit()==false && jugadorEnemigo.getTablero().getMapa()[i+1][columna].getNave() != null){
                            acertoAtaque=true;
                            jugador1.incrementarTiros();
                            
                        }
                        jugadorEnemigo.getTablero().marcarAtaque(i+1, columna, jugadorEnemigo.getTablero());
                        
                        this.poder=false;
                    }else if(res.equals("2")){
                        if (jugadorEnemigo.getTablero().getMapa()[i][columna].isHit()==false && jugadorEnemigo.getTablero().getMapa()[i][columna].getNave() != null){
                            acertoAtaque=true;
                            jugador1.incrementarTiros();
                            
                        }
                        jugadorEnemigo.getTablero().marcarAtaque(i, columna, jugadorEnemigo.getTablero());

                        if (jugadorEnemigo.getTablero().getMapa()[i][columna+1].isHit()==false && jugadorEnemigo.getTablero().getMapa()[i][columna+1].getNave() != null){
                            acertoAtaque=true;
                            jugador1.incrementarTiros();
                            
                        }
                        jugadorEnemigo.getTablero().marcarAtaque(i, columna+1, jugadorEnemigo.getTablero());

                        
                        this.poder=false;
                    } 
                }
                flag=false;
            }else if (columna<jugador1.getTablero().getMapa().length-1){
                while (!res.equals("1")){
                    System.out.println("1: Atacar hacia la derecha");
                    res=r.nextLine();
                    if (res.equals("1")){
                        if (jugadorEnemigo.getTablero().getMapa()[i][columna].isHit()==false && jugadorEnemigo.getTablero().getMapa()[i][columna].getNave() != null){
                            acertoAtaque=true;
                            jugador1.incrementarTiros();
                            
                        }
                        jugadorEnemigo.getTablero().marcarAtaque(i, columna, jugadorEnemigo.getTablero());

                        if (jugadorEnemigo.getTablero().getMapa()[i][columna+1].isHit()==false && jugadorEnemigo.getTablero().getMapa()[i][columna+1].getNave() != null){
                            acertoAtaque=true;
                            jugador1.incrementarTiros();
                            
                        }
                        jugadorEnemigo.getTablero().marcarAtaque(i, columna+1, jugadorEnemigo.getTablero());

                        
                        this.poder=false;
                    }
                }

                flag=false;
            }else if (i<jugador1.getTablero().getMapa().length-1){
                while (!res.equals("1")){
                    System.out.println("1: Atacar hacia abajo");
                    res=r.nextLine();
                    if(res.equals("1")){
                        if (jugadorEnemigo.getTablero().getMapa()[i][columna].isHit()==false && jugadorEnemigo.getTablero().getMapa()[i][columna].getNave() != null){
                            acertoAtaque=true;
                            jugador1.incrementarTiros();
                            
                        }
                        jugadorEnemigo.getTablero().marcarAtaque(i, columna, jugadorEnemigo.getTablero());
                        if (jugadorEnemigo.getTablero().getMapa()[i+1][columna].isHit()==false && jugadorEnemigo.getTablero().getMapa()[i+1][columna].getNave() != null){
                            acertoAtaque=true;
                            jugador1.incrementarTiros();
                            
                        }
                        jugadorEnemigo.getTablero().marcarAtaque(i+1, columna, jugadorEnemigo.getTablero());
                        
                        this.poder=false;
                    }
                }

                flag=false;
            }else{
                System.out.println("");
                System.out.println("POSICION NO VALIDA");
                System.out.println("");
            }
        }
        return acertoAtaque;
    }
}
