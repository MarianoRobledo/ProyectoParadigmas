/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package batallanaval;

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
    private int casillas;
    private int tamaño;
    private int portaaviones;
    private int buques;
    private int submarinos;
    private int cruceros;
    private int lanchas;

    public BatallaNaval() {
  //      this.casillas = MIN_CANTIDAD_CASILLAS;
        this.tamaño = 10;
        this.portaaviones = 1;
        this.buques = 1;
        this.submarinos = 1;
        this.cruceros = 1;
        this.lanchas = 1;
    }

    public void iniciarJuego() {

    }

    public void settearParametros() {
        int res;
        boolean flag = true;

        while (flag) {
            try {
                System.out.println("Que quieres modificar\n"
                        + "1: Tamaño del mapa\n"
                        + "2: Disposicion de barcos,\n"
                        + "3: Salir");

                res = r.nextInt();
                r.nextLine();//para que no haya problema con el buffer

                switch (res) {
                    case 1:
                        this.tamaño = settTamaño();
                        break;
                    case 2:
                        settNaves();
                        break;
                    case 3:
                        flag=false;
                        
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Dar un número valido");
                System.out.println("");
            } finally {
                System.out.println("Fin de menu de parametros");
                System.out.println("");
            }

        }

    }

    /* //decidir despues
    private int settCasillas() {
        boolean flag = true;
        int casillas = 0;
        while (flag) {
            try {
                System.out.println("Dar un valor entre 15 y 20 ");
                casillas = r.nextInt();
                r.nextLine();
                if (casillas >= 15 && casillas <= 20) {
                    flag = false;
                } else {
                    System.out.println("Intentar con un numero correcto");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Dar un número valido");
                System.out.println("");
            }
        }
        return casillas;
    }
     */
    private int settTamaño() {
        boolean flag = true;
        int tamaño = 0;
        String res;
        while (flag) {
            try {
                System.out.println("Elegir entre la opcion A= 5x5, B= 10x10, C= 15x15 ");
                res = r.nextLine();
                switch (res.toLowerCase()) {
                    case "a":
                        tamaño = 5;
                        flag = false;
                        break;
                    case "b":
                        tamaño = 10;
                        flag = false;
                        break;
                    case "c":
                        tamaño = 15;
                        flag = false;
                        break;
                    default:
                        System.out.println("Intentar con una letra correcta");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Dar una letra valida");
                System.out.println("");
            }
        }
        return tamaño;
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
    
    public void settJugador1(Jugador jugador){
        this.jugador1=jugador;
    }
    
    public void settJugador2(Jugador jugador){
        this.jugador2=jugador;
    }
}
