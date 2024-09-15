/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piezas;

import batallanaval.Jugador;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Clase que representa un portaaviones en el tablero.
 * @author Mariano y Emiliano
 */
public class Portaaviones extends Nave{
    private final static int MAX_VIDA=5; //Establece la vida inicial del portaaviones
    
    /**
     * Constructor por defecto que establece la vida máxima y el poder activo.
     */
    public Portaaviones() {
        super(MAX_VIDA, true);
    }
    
    /**
     * Devuelve la vida máxima del portaaviones.
     * 
     * @return La vida máxima del portaaviones.
     */
    public static int getMAX_VIDA() {
        return MAX_VIDA;
    }

    @Override
    public String toString() {
        return "Portaaviones{" + '}';
    }
    
    @Override
    public boolean usarPoder(Jugador j1, Jugador j2){
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
            filas=j1.filas(j1.getTablero().getMapa().length);
            fila = j2.darFila(filas, j1.getTablero().getMapa().length);
            columna = j2.darColumna(j1.getTablero().getMapa().length);
            i=Arrays.asList(filas).indexOf(fila.toUpperCase());
            res="";
            if (i<j1.getTablero().getMapa().length-2 && columna<j1.getTablero().getMapa().length-2){
                while (!res.equals("1") && !res.equals("2")){
                    System.out.println("1: Atacar hacia abajo\n" +
                                   "2: Atacar hacia la derecha");
                    res=r.nextLine();
                    if (res.equals("1")){
                        if (j2.getTablero().getMapa()[i][columna].isHit()==false && j2.getTablero().getMapa()[i][columna].getNave() != null){
                            acertoAtaque=true;
                            j1.incrementarTiros();
                            j2.getTablero().marcarAtaque(i, columna, j2.getTablero());
                        }else if(j2.getTablero().getMapa()[i][columna].isHit()==false){
                            j2.getTablero().marcarAtaque(i, columna, j2.getTablero());
                        }else{
                            System.out.println("Zona ya atacada");
                        }
                        
                        if (j2.getTablero().getMapa()[i+1][columna].isHit()==false && j2.getTablero().getMapa()[i+1][columna].getNave() != null){
                            acertoAtaque=true;
                            j1.incrementarTiros();
                            j2.getTablero().marcarAtaque(i+1, columna, j2.getTablero());
                        }else if(j2.getTablero().getMapa()[i+1][columna].isHit()==false){
                            j2.getTablero().marcarAtaque(i+1, columna, j2.getTablero());
                        }else{
                            System.out.println("Zona ya atacada");
                        }
                        
                        if (j2.getTablero().getMapa()[i+2][columna].isHit()==false && j2.getTablero().getMapa()[i+2][columna].getNave() != null){
                            acertoAtaque=true;
                            j1.incrementarTiros();
                            j2.getTablero().marcarAtaque(i+2, columna, j2.getTablero());
                        }else if(j2.getTablero().getMapa()[i+2][columna].isHit()==false){
                            j2.getTablero().marcarAtaque(i+2, columna, j2.getTablero());
                        }else{
                            System.out.println("Zona ya atacada");
                        }
                        
                        this.poder=false;
                    }else if(res.equals("2")){
                        if (j2.getTablero().getMapa()[i][columna].isHit()==false && j2.getTablero().getMapa()[i][columna].getNave() != null){
                            acertoAtaque=true;
                            j1.incrementarTiros();
                            j2.getTablero().marcarAtaque(i, columna, j2.getTablero());
                        }else if(j2.getTablero().getMapa()[i][columna].isHit()==false){
                            j2.getTablero().marcarAtaque(i, columna, j2.getTablero());
                        }else{
                            System.out.println("Zona ya atacada");
                        }
                        

                        if (j2.getTablero().getMapa()[i][columna+1].isHit()==false && j2.getTablero().getMapa()[i][columna+1].getNave() != null){
                            acertoAtaque=true;
                            j1.incrementarTiros();
                            j2.getTablero().marcarAtaque(i, columna+1, j2.getTablero());
                        }else if(j2.getTablero().getMapa()[i][columna+1].isHit()==false){
                            j2.getTablero().marcarAtaque(i, columna+1, j2.getTablero());
                        }else{
                            System.out.println("Zona ya atacada");
                        }

                        if (j2.getTablero().getMapa()[i][columna+2].isHit()==false && j2.getTablero().getMapa()[i][columna+2].getNave() != null){
                            acertoAtaque=true;
                            j1.incrementarTiros();
                            j2.getTablero().marcarAtaque(i, columna+2, j2.getTablero());
                        }else if(j2.getTablero().getMapa()[i][columna+2].isHit()==false){
                            j2.getTablero().marcarAtaque(i, columna+2, j2.getTablero());
                        }else{
                            System.out.println("Zona ya atacada");
                        }
                        
                        this.poder=false;
                    } 
                }
                flag=false;
            }else if (columna<j1.getTablero().getMapa().length-2){
                while (!res.equals("1")){
                    System.out.println("1: Atacar hacia la derecha");
                    res=r.nextLine();
                    if (res.equals("1")){
                        if (j2.getTablero().getMapa()[i][columna].isHit()==false && j2.getTablero().getMapa()[i][columna].getNave() != null){
                            acertoAtaque=true;
                            j1.incrementarTiros();
                            j2.getTablero().marcarAtaque(i, columna, j2.getTablero());
                        }else if(j2.getTablero().getMapa()[i][columna].isHit()==false){
                            j2.getTablero().marcarAtaque(i, columna, j2.getTablero());
                        }else{
                            System.out.println("Zona ya atacada");
                        }
                        

                        if (j2.getTablero().getMapa()[i][columna+1].isHit()==false && j2.getTablero().getMapa()[i][columna+1].getNave() != null){
                            acertoAtaque=true;
                            j1.incrementarTiros();
                            j2.getTablero().marcarAtaque(i, columna+1, j2.getTablero());
                        }else if(j2.getTablero().getMapa()[i][columna+1].isHit()==false){
                            j2.getTablero().marcarAtaque(i, columna+1, j2.getTablero());
                        }else{
                            System.out.println("Zona ya atacada");
                        }
                        

                        if (j2.getTablero().getMapa()[i][columna+2].isHit()==false && j2.getTablero().getMapa()[i][columna+2].getNave() != null){
                            acertoAtaque=true;
                            j1.incrementarTiros();
                            j2.getTablero().marcarAtaque(i, columna+2, j2.getTablero());
                        }else if(j2.getTablero().getMapa()[i][columna+2].isHit()==false){
                            j2.getTablero().marcarAtaque(i, columna+2, j2.getTablero());
                        }else{
                            System.out.println("Zona ya atacada");
                        }
                        
                        this.poder=false;
                    }
                }

                flag=false;
            }else if (i<j1.getTablero().getMapa().length-2){
                while (!res.equals("1")){
                    System.out.println("1: Atacar hacia abajo");
                    res=r.nextLine();
                    if(res.equals("1")){
                        if (j2.getTablero().getMapa()[i][columna].isHit()==false && j2.getTablero().getMapa()[i][columna].getNave() != null){
                            acertoAtaque=true;
                            j1.incrementarTiros();
                            j2.getTablero().marcarAtaque(i, columna, j2.getTablero());
                        }else if(j2.getTablero().getMapa()[i][columna].isHit()==false){
                            j2.getTablero().marcarAtaque(i, columna, j2.getTablero());
                        }else{
                            System.out.println("Zona ya atacada");
                        }
                        
                        if (j2.getTablero().getMapa()[i+1][columna].isHit()==false && j2.getTablero().getMapa()[i+1][columna].getNave() != null){
                            acertoAtaque=true;
                            j1.incrementarTiros();
                            j2.getTablero().marcarAtaque(i+1, columna, j2.getTablero());
                        }else if(j2.getTablero().getMapa()[i+1][columna].isHit()==false){
                            j2.getTablero().marcarAtaque(i+1, columna, j2.getTablero());
                        }else{
                            System.out.println("Zona ya atacada");
                        }
                        
                        if (j2.getTablero().getMapa()[i+2][columna].isHit()==false && j2.getTablero().getMapa()[i+2][columna].getNave() != null){
                            acertoAtaque=true;
                            j1.incrementarTiros();
                            j2.getTablero().marcarAtaque(i+2, columna, j2.getTablero());
                        }else if(j2.getTablero().getMapa()[i+2][columna].isHit()==false){
                            j2.getTablero().marcarAtaque(i+2, columna, j2.getTablero());
                        }else{
                            System.out.println("Zona ya atacada");
                        }
                        
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
