/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package batallanaval;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Mariano
 */
public class BatallaNaval {

    Scanner r = new Scanner(System.in);
    private static final int MAX_CANTIDAD_CASILLAS = 20;
    private static final int MIN_CANTIDAD_CASILLAS = 15;

    private Jugador jugador1;
    private Jugador jugador2;
    private int turnos;
    private int casillas;
    private int tamaño;
    private int portaaviones;
    private int buques;
    private int submarinos;
    private int cruceros;
    private int lanchas;

    public BatallaNaval() {
  //      this.casillas = MIN_CANTIDAD_CASILLAS;
        this.turnos = 10;
        this.tamaño = 5;
        this.portaaviones = 1;
        this.buques = 0;
        this.submarinos = 0;
        this.cruceros = 0;
        this.lanchas = 0;
    }

    public boolean ataque(Jugador jugador, String fila, int columna, String[] filas) {
        return jugador.getTablero().marcarAtaque(Arrays.asList(jugador.filas(jugador.getTablero().getMapa().length)).indexOf(fila), columna, jugador.getTablero());
    }

    public void settearParametros() {
        int res;
        boolean flag = true;

        while (flag) {
            try {
                System.out.println("Que quieres modificar\n"
                        + "1: Tamaño del mapa\n"
                        + "2: Disposicion de barcos,\n"
                        + "3: Ver las naves actuales\n"
                        + "4: Salir");

                res = r.nextInt();
                r.nextLine();//para que no haya problema con el buffer

                switch (res) {
                    case 1:
                        settTamaño();
                        r.reset();
                        break;
                    case 2:
                        settNaves();
                        r.reset();
                        break;
                    case 3:
                        System.out.println("");
                        System.out.println(mostrarParametros());
                        System.out.println("");
                        r.reset();
                        break;
                    case 4:
                        flag=false;  
                        break;
                    default:
                        System.out.println("Dar un numero correcto");
                        System.out.println("");
                        r.reset();
                        break;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Dar un numero valido");
                System.out.println("");
                r.nextLine();
                r.reset();
            }
        }

    }

 
    private void settTamaño() {
        boolean flag = true;
        String res;
        while (flag) {
            r.reset();
            try {
                System.out.println("Elegir entre la opcion A= 5x5, B= 10x10, C= 15x15 ");
                res = r.nextLine();
                switch (res.toLowerCase()) {
                    case "a":
                        this.turnos = 10;
                        this.tamaño = 5;
                        settNaves();
                        flag = false;
                        break;
                    case "b":
                        this.turnos = 15;
                        this.tamaño = 10;
                        settNaves();
                        flag = false;
                        break;
                    case "c":
                        this.turnos = 20;
                        this.tamaño = 15;
                        settNaves();
                        flag = false;
                        break;
                    default:
                        System.out.println("Intentar con una letra correcta");
                        System.out.println("");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Dar una letra valida");
                System.out.println("");
            }
        }
        
    }

    private void settNaves() {
        boolean flag = true;
        String res;
        while (flag) {
            try {
                if (this.tamaño == 5) {
                    System.out.println("Elegir combinacion de barcos:\n"
                            + "A: 1 Portaavion, 1 Crucero;\n"
                            + "B: 1 Buque, 1 Crucero;\n"
                            + "C: 1 Submarino, 1 Lancha");
                    res = r.nextLine();
                    switch (res.toLowerCase()) {
                        case "a":
                            this.portaaviones = 1;
                            this.buques = 0;
                            this.submarinos = 0;
                            this.cruceros = 1;
                            this.lanchas = 0;
                            flag=false;
                            break;
                        case "b":
                            this.portaaviones = 0;
                            this.buques = 1;
                            this.submarinos = 0;
                            this.cruceros = 1;
                            this.lanchas = 0;
                            flag=false;
                            break;
                        case "c":
                            this.portaaviones = 0;
                            this.buques = 0;
                            this.submarinos = 1;
                            this.cruceros = 0;
                            this.lanchas = 1;
                            flag=false;
                            break;
                        default:
                            System.out.println("Dar una letra correcta");
                            System.out.println("");
                    }
                } else if(this.tamaño==10){
                    System.out.println("Elegir combinacion de barcos:\n"
                            + "A: 1 Portaavion, 1 Buque, 1 Submarino, 1 Crucero, 1 Lancha;\n"
                            + "B: 2 Portaaviones, 2 Submarinos, 2 Lanchas;\n"
                            + "C: 2 Buques, 1 Submarino, 1 Crucero, 2 Lanchas");
                    res = r.nextLine();
                    switch (res.toLowerCase()) {
                        case "a":
                            this.portaaviones = 1;
                            this.buques = 1;
                            this.submarinos = 1;
                            this.cruceros = 1;
                            this.lanchas = 1;
                            flag=false;
                            break;
                        case "b":
                            this.portaaviones = 2;
                            this.buques = 0;
                            this.submarinos = 2;
                            this.cruceros = 0;
                            this.lanchas = 2;
                            flag=false;
                            break;
                        case "c":
                            this.portaaviones = 0;
                            this.buques = 2;
                            this.submarinos = 1;
                            this.cruceros = 1;
                            this.lanchas = 2;
                            flag=false;
                            break;
                        default:
                            System.out.println("Dar una letra correcta");
                            System.out.println("");
                    }
                    
                }else{
                    System.out.println("Elegir combinacion de barcos:\n"
                            + "A: 2 Portaaviones, 1 Buque, 2 Submarinos, 1 Crucero, 2 Lanchas;\n"
                            + "B: 1 Portaavion, 2 Buques; 2 Submarinos, 2 Lanchas;\n"
                            + "C: 2 Buques, 1 Submarino, 3 Cruceros, 2 Lanchas");
                    res = r.nextLine();
                    switch (res.toLowerCase()) {
                        case "a":
                            this.portaaviones = 2;
                            this.buques = 1;
                            this.submarinos = 2;
                            this.cruceros = 1;
                            this.lanchas = 2;
                            flag=false;
                            break;
                        case "b":
                            this.portaaviones = 1;
                            this.buques = 2;
                            this.submarinos = 2;
                            this.cruceros = 0;
                            this.lanchas = 2;
                            flag=false;
                            break;
                        case "c":
                            this.portaaviones = 0;
                            this.buques = 2;
                            this.submarinos = 1;
                            this.cruceros = 3;
                            this.lanchas = 2;
                            flag=false;
                            break;
                        default:
                            System.out.println("Dar una letra correcta");
                    }
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Dar una letra valida");
                System.out.println("");
            }
        }
        
    }

    public String mostrarParametros(){
        return "Tamaño del mapa: "+this.tamaño+"\n"
                + "Cantidad de Portaaviones: "+this.portaaviones+"\n"
                + "Cantidad de Buques: "+this.buques+"\n"
                + "Cantidad de Submarinos: "+this.submarinos+"\n"
                + "Cantidad de Cruceros: "+this.cruceros+"\n"
                + "Cantidad de Lanchas: "+this.lanchas;
    }
    

    public Jugador getJugador1() {
        return jugador1;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;

    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public int getPortaaviones() {
        return portaaviones;
    }

    public void setPortaaviones(int portaaviones) {
        this.portaaviones = portaaviones;
    }

    public int getBuques() {
        return buques;
    }

    public void setBuques(int buques) {
        this.buques = buques;
    }

    public int getSubmarinos() {
        return submarinos;
    }

    public void setSubmarinos(int submarinos) {
        this.submarinos = submarinos;
    }

    public int getCruceros() {
        return cruceros;
    }

    public void setCruceros(int cruceros) {
        this.cruceros = cruceros;
    }

    public int getLanchas() {
        return lanchas;
    }

    public void setLanchas(int lanchas) {
        this.lanchas = lanchas;
    }

    public int getTurnos() {
        return turnos;
    }
    
    
}
