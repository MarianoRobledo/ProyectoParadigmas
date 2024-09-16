/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tablero;

import batallanaval.IMuestraTablero;
import java.util.*;
import piezas.*;

/**
 * Representa un tablero de juego para el juego de batalla naval.
 * Este tablero contiene un mapa de casillas y una lista de naves.
 * Permite visualizar el tablero, insertar islas, marcar ataques y más.
 * 
 * @author Mariano y Emiliano
 */
public class Tablero implements IMuestraTablero{

    private Casilla[][] mapa;
    private List<Nave> naves;


    public Tablero() {
    }
    /**
     * Crea un tablero con un tamaño especificado y una cantidad determinada de cada tipo de nave.
     * @param tamaño el tamaño del tablero (lado del cuadrado)
     * @param portaavion Cantidad de Portaaviones
     * @param buque Cantidad de Buques
     * @param submarino Cantidad de Submarinos
     * @param crucero Cantidad de Cruceros
     * @param lancha Cantidad de Lanchas
     */
    public Tablero(int tamaño, int portaavion, int buque, int submarino, int crucero, int lancha) {
        this.mapa = new Casilla[tamaño][tamaño];
        this.naves = new ArrayList<Nave>();
        //Inserta en cada posicion de la matriz el objeto Casilla
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                this.mapa[i][j] = new Casilla();
            }
        }

        insertarIslas();
        
        //Inserta la cantidad de portaaviones a la lista de Naves
        if (portaavion > 0) {
            for (int i = 0; i < portaavion; i++) {
                this.naves.add(new Portaaviones());
            }
        }
        //Inserta la cantidad de buques a la lista de Naves
        if (buque > 0) {
            for (int i = 0; i < buque; i++) {
                this.naves.add(new Buque());

            }
        }
        //Inserta la cantidad de submarinos a la lista de Naves
        if (submarino > 0) {
            for (int i = 0; i < submarino; i++) {

                this.naves.add(new Submarino());
            }
        }
        //Inserta la cantidad de cruceros a la lista de Naves
        if (crucero > 0) {
            for (int i = 0; i < crucero; i++) {
                this.naves.add(new Crucero());
            }
        }
        //Inserta la cantidad de lanchas a la lista de Naves
        if (lancha > 0) {
            for (int i = 0; i < lancha; i++) {
                this.naves.add(new Lancha());
            }
        }

    }
    /**
     * Muestra el tablero de piezas en el formato actual que se utiliza a la hora de colocar las Naves.
     * El tablero se visualiza con las islas, barcos y agua representados por diferentes símbolos.
     */
    public void verTableroDePiezas() { 
        
        String[] filas;
        int tamanio = this.mapa.length;
        for (int k = 0; k <= tamanio; k++){
            if (k==0){
                System.out.print("   ");
            }else{
                if (k<=10){
                    System.out.print(" " + k + " ");
                }else{
                    System.out.print(k + " ");
                }
                
            }
        }
        System.out.println("");
        filas=filas(this.mapa.length);
        
        for (int i = 0; i < tamanio; i++) {
            System.out.print(" " + filas[i] + " ");
            //Recorro mi tablero para que muestre las posiciones de los barcos e islas
            for (int j = 0; j < tamanio; j++) { 
                if (this.mapa[i][j].isIsla()) { 
                    //Si isla es True imprimo una parte de Isla
                    System.out.print("[I]");
                } else if (this.mapa[i][j].isBarcoAlLado()) {
                    //Si la casilla contiene un barco a su lado imprimo un espacio donde no se puede colocar barcos
                    System.out.print("[-]");
                } else if (this.mapa[i][j].isAgua()) { 
                    //Si agua es True imprimo un espacio vacio del mapa
                    System.out.print("[ ]");
                } else {
                    //Si hay una nave imprimo una parte de la nave
                    System.out.print("[N]");
                }
            }
            System.out.println("");
        }
    }
    
    /**
     * Muestra el tablero de naves propias del jugador.
     * El tablero se visualiza con las islas, barcos, tiros impactados sobre los barcos propios y agua representados por diferentes símbolos.
     */
    public void verTableroDePiezasEnJuego() { 
        System.out.println("----------------------------------------------------");
        System.out.println("               MAPA NAVES PROPIAS");
        System.out.println("----------------------------------------------------");
        String[] filas;
        int tamanio = this.mapa.length;
        for (int k = 0; k <= tamanio; k++){
            if (k==0){
                System.out.print("   ");
            }else{
                if (k<=10){
                    System.out.print(" " + k + " ");
                }else{
                    System.out.print(k + " ");
                }
            }
        }
        System.out.println("");
        filas=filas(this.mapa.length);
        for (int i = 0; i < tamanio; i++) {
            System.out.print(" " + filas[i] + " ");
            for (int j = 0; j < tamanio; j++) { //Recorro mi tablero para que muestre las posiciones de los barcos e islas
                if (this.mapa[i][j].isIsla()) { //Si isla es True
                    System.out.print("[I]");
                } else if (this.mapa[i][j].isAgua()) { //Si agua es True
                    System.out.print("[ ]");
                } else if (this.mapa[i][j].isHit()) {
                    System.out.print("[X]");//Si fue golpeado es True
                } else {//Si hay una nave
                    System.out.print("[N]");
                }
            }
            System.out.println("");
        }
    }
    
    /**
     * Muestra el tablero de tiros, indicando los tiros impactados sobre el tablero enemigo
     * @param tableroEnemigo El tablero enemigo sobre el cual han impactado los tiros
     */
    public void verTableroDeTiros(Tablero tableroEnemigo) {
        System.out.println("----------------------------------------------------");
        System.out.println("               MAPA DE TIROS");
        System.out.println("----------------------------------------------------");
        String[] filas;
        int tamanio = this.mapa.length;
        for (int k = 0; k <= tamanio; k++){
            if (k==0){
                System.out.print("   ");
            }else{
                if (k<=10){
                    System.out.print(" " + k + " ");
                }else{
                    System.out.print(k + " ");
                }
            }
        }
        System.out.println("");
        filas=filas(this.mapa.length);
        for (int i = 0; i < tamanio; i++) {
            System.out.print(" " + filas[i] + " ");
            for (int j = 0; j < tamanio; j++) { //Recorro el tablero del enemigo para ver donde he dado tiros
                if (tableroEnemigo.getMapa()[i][j].isHit()) { //Si hit es True
                    if (tableroEnemigo.getMapa()[i][j].isAgua()) { //Si agua es True
                        System.out.print("[O]");
                    } else if (tableroEnemigo.getMapa()[i][j].isIsla()) { //Si isla es True
                        System.out.print("[O]");
                    } else { //Si hay una nave
                        System.out.print("[X]");
                    }
                }else if(tableroEnemigo.getMapa()[i][j].isSonar()){
                    if (tableroEnemigo.getMapa()[i][j].isAgua()) { //Si agua es True
                        System.out.print("[ ]");
                    } else if (tableroEnemigo.getMapa()[i][j].isIsla()) { //Si isla es True
                        System.out.print("[I]");
                    } else { //Si hay una nave
                        System.out.print("[N]");
                    }
                } else { //Si no se disparo en esa casilla
                    System.out.print("[ ]");
                }
            }
            System.out.println("");
        }
    }
    
    /**
     * Marca el ataque realizado en el tablero enemigo y reduce la vida de la nave en caso de haber impactado en una.
     * @param i posicion de fila del tablero enemigo
     * @param j posicion de columna del tablero enemigo
     * @param tableroEnemigo tablero sobre el que se realiza el ataque
     * @return True si la casilla impactada contiene un objeto Nave, False en caso contrario
     */
    public boolean marcarAtaque(int i, int j, Tablero tableroEnemigo) {
        tableroEnemigo.getMapa()[i][j].setHit(true); //Ingreso a la casilla elegida en el tablero enemigo y marco hit = true
        if (tableroEnemigo.getMapa()[i][j].getNave() != null) {
            tableroEnemigo.getMapa()[i][j].getNave().recibirDaño(); //Ingreso a la referencia de la nave de esa casilla y utilizo RecibirDaño()
            if (tableroEnemigo.getMapa()[i][j].getNave().isPoder() && tableroEnemigo.getMapa()[i][j].getNave().getVida()==0){
                tableroEnemigo.getMapa()[i][j].getNave().setPoder(false);
            }
        }else{
            System.out.println("");
            System.out.println("!Agua!");
            System.out.println("");
        }
        return tableroEnemigo.getMapa()[i][j].getNave() != null;
    }
    
    /**
     * Inserta de forma aleatoria islas  de tamaño 2x2 con una cantidad predeterminada de islas segun el tamaño del tablero
     */
    public void insertarIslas() {
        int cantIslas;
        Random random = new Random();
        if (this.mapa.length == 5) {
            cantIslas = 1;
        } else if (this.mapa.length == 10) {
            cantIslas = 2;
        } else {
            cantIslas = 3;
        }
        for (int i = 0; i < cantIslas; i++) {
            boolean flag = false;
            while (flag == false) {
                int x = random.nextInt(this.mapa.length);
                int y = random.nextInt(this.mapa.length);
                if (espacioDisponible(x, y)) {
                    flag = true;
                    if (x == this.mapa.length - 1) {
                        if (y == this.mapa.length - 1) {
                            this.mapa[x][y].setIsla(true);
                            this.mapa[x - 1][y].setIsla(true);
                            this.mapa[x][y - 1].setIsla(true);
                            this.mapa[x - 1][y - 1].setIsla(true);
                        } else {
                            this.mapa[x][y].setIsla(true);
                            this.mapa[x - 1][y].setIsla(true);
                            this.mapa[x][y + 1].setIsla(true);
                            this.mapa[x - 1][y + 1].setIsla(true);
                        }
                    } else {
                        if (y == this.mapa.length - 1) {
                            this.mapa[x][y].setIsla(true);
                            this.mapa[x + 1][y].setIsla(true);
                            this.mapa[x][y - 1].setIsla(true);
                            this.mapa[x + 1][y - 1].setIsla(true);
                        } else {
                            this.mapa[x][y].setIsla(true);
                            this.mapa[x + 1][y].setIsla(true);
                            this.mapa[x][y + 1].setIsla(true);
                            this.mapa[x + 1][y + 1].setIsla(true);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Verifica si hay espacio disponible para insertar una isla en la posición dada.
     * 
     * @param x La posicion de fila de la casilla.
     * @param y La posicion de columna de la casilla.
     * @return True si el espacio está disponible, False en caso contrario.
     */
    public boolean espacioDisponible(int x, int y) {
        if (x == this.mapa.length - 1) {
            if (y == this.mapa.length - 1) {
                if (this.mapa[x][y].isIsla() == true) {
                    return false;
                } else if (this.mapa[x - 1][y].isIsla() == true) {
                    return false;
                } else if (this.mapa[x][y - 1].isIsla() == true) {
                    return false;
                } else if (this.mapa[x - 1][y - 1].isIsla() == true) {
                    return false;
                }
            } else {
                if (this.mapa[x][y].isIsla() == true) {
                    return false;
                } else if (this.mapa[x - 1][y].isIsla() == true) {
                    return false;
                } else if (this.mapa[x][y + 1].isIsla() == true) {
                    return false;
                } else if (this.mapa[x - 1][y + 1].isIsla() == true) {
                    return false;
                }
            }
        } else {
            if (y == this.mapa.length - 1) {
                if (this.mapa[x][y].isIsla() == true) {
                    return false;
                } else if (this.mapa[x + 1][y].isIsla() == true) {
                    return false;
                } else if (this.mapa[x][y - 1].isIsla() == true) {
                    return false;
                } else if (this.mapa[x + 1][y - 1].isIsla() == true) {
                    return false;
                }
            } else {
                if (this.mapa[x][y].isIsla() == true) {
                    return false;
                } else if (this.mapa[x + 1][y].isIsla() == true) {
                    return false;
                } else if (this.mapa[x][y + 1].isIsla() == true) {
                    return false;
                } else if (this.mapa[x + 1][y + 1].isIsla() == true) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Muestra la cantidad de naves aun en el tablero, agrupados por los diferentes tipos de naves
     */
    public void barcosASalvo() {
        Iterator<Nave> it = this.naves.iterator();
        int portaaviones = 0;
        int buque = 0;
        int submarino = 0;
        int crucero = 0;
        int lancha = 0;

        while (it.hasNext()) {
            Nave nave = it.next();
            if (nave.getClass().getSimpleName().equals("Portaaviones") && nave.getVida() > 0) {
                portaaviones++;
            } else if (nave.getClass().getSimpleName().equals("Buque") && nave.getVida() > 0) {
                buque++;
            } else if (nave.getClass().getSimpleName().equals("Submarino") && nave.getVida() > 0) {
                submarino++;
            } else if (nave.getClass().getSimpleName().equals("Crucero") && nave.getVida() > 0) {
                crucero++;
            } else if (nave.getClass().getSimpleName().equals("Lancha") && nave.getVida() > 0) {
                lancha++;
            }
         
        }
        System.out.println("");
        System.out.println("");
        if (portaaviones > 0) {
            System.out.println("Tienes " + portaaviones + " portaaviones");
        }
        if (buque > 0) {
            System.out.println("Tienes " + buque + " buques");
        }
        if (submarino > 0) {
            System.out.println("Tienes " + submarino + " submarinos");
        }
        if (crucero > 0) {
            System.out.println("Tienes " + crucero + " cruceros");
        }
        if (lancha > 0) {
            System.out.println("Tienes " + lancha + " lanchas");
        }
        System.out.println("");
        System.out.println("");
    }

    /**
     * Muestra la cantidad de naves hundidas, agrupados por los diferentes tipos de naves
     */
    public void barcosHundidos() {
        Iterator<Nave> it = this.naves.iterator();
        int portaaviones = 0;
        int buque = 0;
        int submarino = 0;
        int crucero = 0;
        int lancha = 0;

        while (it.hasNext()) {
            Nave nave = it.next();
            if (nave.getClass().getSimpleName().equals("Portaaviones") && nave.getVida() == 0) {
                portaaviones++;
            } else if (nave.getClass().getSimpleName().equals("Buque") && nave.getVida() == 0) {
                buque++;
            } else if (nave.getClass().getSimpleName().equals("Submarino") && nave.getVida() == 0) {
                submarino++;
            } else if (nave.getClass().getSimpleName().equals("Crucero") && nave.getVida() == 0) {
                crucero++;
            } else if (nave.getClass().getSimpleName().equals("Lancha") && nave.getVida() == 0) {
                lancha++;
            }

        }
        System.out.println("");
        System.out.println("");
        if (portaaviones > 0) {
            System.out.println("Tienes " + portaaviones + " portaaviones hundidos");
        }
        if (buque > 0) {
            System.out.println("Tienes " + buque + " buques hundidos");
        }
        if (submarino > 0) {
            System.out.println("Tienes " + submarino + " submarinos hundidos");
        }
        if (crucero > 0) {
            System.out.println("Tienes " + crucero + " cruceros hundidos");
        }
        if (lancha > 0) {
            System.out.println("Tienes " + lancha + " lanchas hundidos");
        }
        if (portaaviones == 0 && buque == 0 && submarino == 0 && crucero == 0 && lancha == 0) {
            System.out.println("No tiene barcos hundidos");
        }
        System.out.println("");
        System.out.println("");
    }

    /**
     * Obtiene la lista de Naves en el tablero
     * 
     * @return Lista de naves
     */
    public List<Nave> getNaves() {
        return naves;
    }
    
    /**
     * Establece la lista de naves en el tablero
     * 
     * @param naves Lista de naves a establecer
     */
    public void setNaves(List<Nave> naves) {
        this.naves = naves;
    }
    
    /**
     * Establece el mapa del tablero
     * 
     * @param mapa Nuevo mapa del tablero
     */
    public void setMapa(Casilla[][] mapa) {
        this.mapa = mapa;
    }
    
    /**
     * Obtiene el mapa del tablero
     * 
     * @return Mapa del tablero
     */
    public Casilla[][] getMapa() {
        return mapa;
    }

    /**
     * Establece si una casilla es una isla en la posición dada.
     * 
     * @param i La posicion de fila de la casilla.
     * @param j La posicion de columna de la casilla.
     * @param t True para establecer la casilla como isla, False para agua.
     */
    public void setCasillaIsla(int i, int j, boolean t) {
        this.mapa[i][j].setAgua(false);
        this.mapa[i][j].setIsla(t);
    }
    
    /**
     * Devuelve el array de filas para el tablero.
     * 
     * @param size El tamaño del tablero.
     * @return Un array de cadenas representando las filas del tablero.
     */
    public String[] filas(int size){//devuelve el array de filas 
        String[] fila;
        switch (size) {
            case 5: {
                fila = new String[]{"A", "B", "C", "D", "E"};
                break;
            }
            case 10: {
                fila = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
                break;
            }
            default: {
                fila = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"};
                break;
            }
        }

        return fila;
    }

}
