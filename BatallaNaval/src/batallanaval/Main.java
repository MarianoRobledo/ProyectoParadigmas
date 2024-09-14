/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batallanaval;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import piezas.Nave;
import tablero.Tablero;

/**
 *
 * @author maria
 */
public class Main {
    public static void main(String[] args) {
        Scanner r = new Scanner(System.in);
        String res,opc;
        BatallaNaval b1 = new BatallaNaval();
        opc="";
        System.out.println("                    BATALLA NAVAL");
        while (!opc.equals("4")){
            System.out.println("1: Iniciar Juego");
            System.out.println("2: Configuracion");
            System.out.println("3: Informacion Del Juego");
            System.out.println("4: Salir");
            opc=r.nextLine();
            if (opc.equals("1")){ //TODA LA LOGICA DE EJECUCION DEL JUEGO

                String fila;
                int selector, columna;
                boolean flag = true;
                boolean juego = true;
                boolean valor;
                int turnos = 0;
                String[] filas;

                Tablero t1 = new Tablero(b1.getTamaño(), b1.getPortaaviones(), b1.getBuques(), b1.getSubmarinos(), b1.getCruceros(), b1.getLanchas());
                Tablero t2 = new Tablero(b1.getTamaño(), b1.getPortaaviones(), b1.getBuques(), b1.getSubmarinos(), b1.getCruceros(), b1.getLanchas());

                System.out.println("Dar el nombre del primer jugador:");
                res = r.nextLine();
                Jugador j1 = new Jugador(res, t1);
                r.reset();

                System.out.println("Dar el nombre del segundo jugador:");
                res = r.nextLine();
                Jugador j2 = new Jugador(res, t2);

                b1.setJugador1(j1);
                b1.setJugador2(j2);

                System.out.println(j1.getNick() + " coloque las naves en el tablero");
                System.out.println("----------------------------------------------------");
                j1.colocarNave();
                System.out.println("");
                System.out.println("----------------------------------------------------");

                System.out.println(j2.getNick() + " coloque las naves en el tablero");
                j2.colocarNave();

                filas = b1.getJugador1().filas(b1.getTamaño());


                System.out.println("");
                System.out.println("----------------------------------------------------");
                System.out.println("Comienza el juego");
                System.out.println("----------------------------------------------------");

                boolean ataqueDisponible;
                boolean tiroFinal=false; //CASO ESPECIAL GANE EL JUGADOR 1 Y TENGA SU ULTIMO TIRO EL JUGADOR 2
                while (juego) {
                    System.out.println("----------------------------------------------------");
                    System.out.println("Turno nº: " + ++turnos);
                    System.out.println("Turno del jugador: "+b1.getJugador1().getNick());
                    System.out.println("Cantidad de aciertos "+j1.getTiros());
                    System.out.println("----------------------------------------------------");
                    flag=true;
                    //TURNO DEL JUGADOR 1
                    while (flag) {
                        res="";
                        ataqueDisponible=false;
                        selector = verificadorDeNumero();
                        if (selector == 1) {

                            if (poderDisponible(j1)){
                                while (!res.equals("1") && !res.equals("2")){
                                    System.out.println("1: Utilizar poder\n"+
                                                       "2: Ataque");
                                    res=r.nextLine();
                                    if(res.equals("1")){
                                        ataqueDisponible=true;
                                        flag=mostrarPoderes(j1,j2);
                                        if(!quedanBarcosASalvo(j2)){
                                            System.out.println("¡Has destruido todas las naves enemigas!");
                                            flag=false;
                                            juego=false;
                                            tiroFinal=true;
                                        }
                                    }else if (!res.equals("1") && !res.equals("2")){
                                        System.out.println("Ingrese un numero valido");
                                    }
                                }

                            }
                            while(!ataqueDisponible){
                                System.out.println("");
                                System.out.println("Dar las posicion a atacar");
                                System.out.println("");    

                                fila = b1.getJugador1().darFila(filas, b1.getTamaño());
                                columna = b1.getJugador1().darColumna(b1.getTamaño());
                                //verifica si la posicion elegida fue atacada
                                ataqueDisponible=esAtaqueValido(b1.getJugador2(),fila,columna,filas);
                                //si la posicion esta permitida, realizar el ataque
                                if (ataqueDisponible){
                                    valor = (b1.ataque(j2, fila, columna, filas));
                                    if (valor) {
                                        j1.incrementarTiros();
                                        //verifica si el tiro dado destruyo toda la flota enemiga
                                        if(!quedanBarcosASalvo(j2)){
                                            System.out.println("¡Has destruido todas las naves enemigas!");
                                            flag=false;
                                            juego=false;
                                            tiroFinal=true;
                                        }

                                    } else {
                                        flag = false;
                                    }
                                }else{
                                    System.out.println("No es posible atacar esa posicion, elija otra");
                                }

                            }
                        }else if(selector == 3){
                            b1.getJugador1().getTablero().barcosASalvo();
                        }else if (selector == 4){
                            b1.getJugador1().getTablero().barcosHundidos();
                        } else {
                            b1.getJugador1().getTablero().verTableroDePiezasEnJuego();
                            System.out.println("");
                            b1.getJugador1().getTablero().verTableroDeTiros(b1.getJugador2().getTablero());
                            System.out.println("");

                        }
                    }
                    if (juego==true || tiroFinal==true){ //Si el jugador 1 aun no destruyo todas las naves
                        flag=true;
                        System.out.println("----------------------------------------------------");
                        System.out.println("Turno nº: " + ++turnos);
                        System.out.println("Turno del jugador: "+b1.getJugador2().getNick());
                        System.out.println("Cantidad de aciertos " +j2.getTiros());
                        System.out.println("----------------------------------------------------");
                        if (tiroFinal==true){
                            System.out.println("");
                            System.out.println("EL JUGADOR "+ j1.getNick() +" HA DESTRUIDO TODAS LAS NAVES DE "+ j2.getNick());
                            System.out.println("ESTA SERA LA ULTIMA JUGADA DEL JUGADOR "+j2.getNick());
                            System.out.println("");
                        }
                    }

                    //TURNO DEL JUGADOR 2
                    while (flag) {
                        res="";
                        ataqueDisponible=false;
                        selector = verificadorDeNumero();
                        if (selector == 1) {
                            if (poderDisponible(j2)){
                                while (!res.equals("1") && !res.equals("2")){
                                    System.out.println("1: Utilizar poder\n"+
                                                       "2: Ataque");
                                    res=r.nextLine();
                                    if(res.equals("1")){
                                        ataqueDisponible=true;
                                        flag=mostrarPoderes(j2,j1);
                                        if(!quedanBarcosASalvo(j1)){
                                            System.out.println("¡Has destruido todas las naves enemigas!");
                                            flag=false;
                                            juego=false;
                                        }  
                                    }else if (!res.equals("1") && !res.equals("2")){
                                        System.out.println("Ingrese un numero valido");
                                    }
                                }

                            }
                            while(!ataqueDisponible){
                                System.out.println("");
                                System.out.println("Dar las posicion a atacar");
                                System.out.println("");    

                                fila = b1.getJugador2().darFila(filas, b1.getTamaño());
                                columna = b1.getJugador2().darColumna(b1.getTamaño());
                                //verifica si la posicion elegida fue atacada
                                ataqueDisponible=esAtaqueValido(b1.getJugador1(),fila,columna,filas);
                                //si la posicion esta permitida, realizar el ataque
                                if (ataqueDisponible){
                                    valor = (b1.ataque(j1, fila, columna, filas));
                                    if (valor) {
                                        j2.incrementarTiros();
                                        //verifica si el tiro dado destruyo toda la flota enemiga
                                        if(!quedanBarcosASalvo(j1)){
                                            System.out.println("¡Has destruido todas las naves enemigas!");
                                            flag=false;
                                            juego=false;
                                        }
                                    } else {
                                        flag = false;
                                    } 
                                }else{
                                    System.out.println("No es posible atacar esa posicion, elija otra");
                                }
                            }
                        }else if(selector == 3){
                            b1.getJugador2().getTablero().barcosASalvo();
                        }else if (selector == 4){
                            b1.getJugador2().getTablero().barcosHundidos();    
                        } else {
                            b1.getJugador2().getTablero().verTableroDePiezasEnJuego();
                            System.out.println("");
                            b1.getJugador2().getTablero().verTableroDeTiros(b1.getJugador1().getTablero());
                            System.out.println("");

                        }
                    }
                    if(turnos>b1.getTurnos() && juego==true){
                        System.out.println("Se acabaron los turnos");
                        juego=false;
                    }

                }
                if(j1.getTiros()>j2.getTiros()){
                    System.out.println("Gano " + j1.getNick());
                }else if(j2.getTiros()>j1.getTiros()){
                    System.out.println("Gano "+ j2.getNick());
                }else{
                    System.out.println("Empate");
                }
                
                
            }else if(opc.equals("2")){ //OPCION PARA PODER CONFIGURAR LOS PARAMETROS
                System.out.println("");
                b1.settearParametros();
                System.out.println("");
            }else if(opc.equals("3")){ //INFORMACION QUE SE REQUIERA PARA ENTENDER EL JUEGO
                System.out.println("");
                System.out.println("ACA VA LA INFORMACION DEL JUEGO");
                System.out.println("");
            }else{
                System.out.println("");
                System.out.println("Ingrese un numero valido");
                System.out.println("");
            }
        }
        

    }
    
    //FUNCION MENU INTERACTIVO DE JUGADOR
    public static int verificadorDeNumero() {
        Scanner r = new Scanner(System.in);
        int res = 0;
        boolean flag = true;

        while (flag) {
            
                System.out.println("Que quiere realizar:\n"
                        + "1: Atacar\n"
                        + "2: Ver Mapas\n"
                        + "3: Ver Barcos a Salvo\n" 
                        + "4: Ver Barcos Hundidos\n");
                try{
                    res = r.nextInt();
                    r.nextLine();
                    if (res == 1 || res == 2 || res == 3 || res == 4) {
                        flag = false;
                    }
                } catch (Exception e) {
                    System.out.println("Dar un numero valido");
                    r.nextLine();
                } finally {
                    r.reset();
                }
        }
        return res;
    }
    
    //FUNCION QUE VERIFICA SI LA POSICION ELEGIDA PARA ATACAR FUE ATACADA ANTERIORMENTE
    private static boolean esAtaqueValido(Jugador jugador, String i, int j, String[] filas) {// valida si la casilla ha sido atacada
        return (jugador.getTablero().getMapa()[Arrays.asList(filas).indexOf(i.toUpperCase())][j].isHit()==false);
    }
    
    //FUNCION QUE VERIFICA SI QUEDA ALGUN BARCO A SALVO DEL JUGADOR
    private static boolean quedanBarcosASalvo(Jugador jugador) {
        boolean hayBarcosASalvo = false;

        // Obtener la lista de naves del tablero del jugadorEnemigo
        List<Nave> naves = jugador.getTablero().getNaves();

        // Iterar sobre la lista de naves
        for (Nave nave : naves) {
            // Comprobar si la nave tiene vida (es decir, está a salvo)
            if (nave.getVida() > 0) {
                hayBarcosASalvo = true;
                break; // No es necesario continuar si ya encontramos un barco a salvo
            }
        }

        return hayBarcosASalvo;
    }
    
    //FUNCION QUE VERIFICA SI EXISTE ALGUN PODER PARA USAR
    private static boolean poderDisponible(Jugador jugador) {
        boolean hayBarcosASalvo = false;

        // Obtener la lista de naves del tablero del jugadorEnemigo
        List<Nave> naves = jugador.getTablero().getNaves();

        // Iterar sobre la lista de naves
        for (Nave nave : naves) {
            // Comprobar si la nave tiene vida (es decir, está a salvo)
            if (nave.isPoder()==true) {
                hayBarcosASalvo = true;
                break; // No es necesario continuar si ya encontramos un barco a salvo
            }
        }

        return hayBarcosASalvo;
    }
    
    //FUNCION QUE SE ENCARGA DE EJECUTAR LOS PODERES
    public static boolean mostrarPoderes(Jugador jugador1, Jugador jugadorEnemigo) {
        boolean acertoAtaque=false;
        Iterator<Nave> it = jugador1.getTablero().getNaves().iterator();
        int portaaviones = 0;
        int buque = 0;
        int submarino = 0;
        Nave portaavionTemp=null;
        Nave buqueTemp=null;
        Nave submarinoTemp=null;
        Scanner r = new Scanner(System.in);
        String res;
        
        while (it.hasNext()) {
            Nave nave = it.next();
            if (nave.getClass().getSimpleName().equals("Portaaviones") && nave.isPoder() == true) {
                portaaviones++;
                portaavionTemp=nave;
            } else if (nave.getClass().getSimpleName().equals("Buque") && nave.isPoder() == true) {
                buque++;
                buqueTemp=nave;
            } else if (nave.getClass().getSimpleName().equals("Submarino") && nave.isPoder() == true) {
                submarino++;
                submarinoTemp=nave;
            }

        }
        if (portaaviones > 0) {
            System.out.println("Tienes " + portaaviones + " de portaaviones disponibles (Opcion P)");
        }
        if (buque > 0) {
            System.out.println("Tienes " + buque + " de buques disponibles (Opcion B)");
        }
        if (submarino > 0) {
            System.out.println("Tienes " + submarino + " de submarinos disponibles (Opcion S)");
        }
        
        boolean flag=true;
        while (flag){
            System.out.println("ELIJA UNA OPCION");
            res=r.nextLine();
            res=res.toUpperCase();
            if (!res.equals("P") && !res.equals("B") && !res.equals("S")){
                System.out.println("Opciones disponibles: ");
                if (portaaviones > 0) {
                    System.out.println(" P ");
                }
                if (buque > 0) {
                    System.out.println(" B ");
                }
                if (submarino > 0) {
                    System.out.println(" S ");
                }
            
            
            }
            if (res.equals("P")){
                if (portaaviones>0){
                    acertoAtaque=portaavionTemp.usarPoder(jugador1, jugadorEnemigo);
                    flag=false;
                }else{
                    System.out.println("No tiene portaaviones con poderes habilitados");
                }
                  
            }
            if (res.equals("B")){
                if (buque > 0){
                    acertoAtaque=buqueTemp.usarPoder(jugador1, jugadorEnemigo);
                    flag=false;
                }else{
                    System.out.println("No tiene buques con poderes habilitados");
                }
                
            }
            if (res.equals("S")){
                if (submarino>0){
                    acertoAtaque=submarinoTemp.usarPoder(jugador1, jugadorEnemigo);
                    flag=false;
                }else{
                    System.out.println("No tiene submarinos con poderes habilitados");
                }
            }
        }
        return acertoAtaque;
    }
}
