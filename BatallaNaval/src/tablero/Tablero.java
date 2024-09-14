/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tablero;

import batallanaval.IMuestraTablero;
import batallanaval.Jugador;
import java.util.*;
import piezas.*;

/**
 *
 * @author Mariano
 */
public class Tablero implements IMuestraTablero{

    private Casilla[][] mapa;
    private int cantidadNave;

    private List<Nave> naves;
    private int barcosHundidos;


    public Tablero() {
    }

    public Tablero(int tamaño, int portaavion, int buque, int submarino, int crucero, int lancha) {
        this.mapa = new Casilla[tamaño][tamaño];
        this.naves = new ArrayList<Nave>();
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                this.mapa[i][j] = new Casilla();
            }
        }

        insertarIslas();

        if (portaavion > 0) {
            for (int i = 0; i < portaavion; i++) {
                this.naves.add(new Portaaviones());
            }
        }

        if (buque > 0) {
            for (int i = 0; i < buque; i++) {
                this.naves.add(new Buque());

            }
        }

        if (submarino > 0) {
            for (int i = 0; i < submarino; i++) {

                this.naves.add(new Submarino());
            }
        }

        if (crucero > 0) {
            for (int i = 0; i < crucero; i++) {
                this.naves.add(new Crucero());
            }
        }

        if (lancha > 0) {
            for (int i = 0; i < lancha; i++) {
                this.naves.add(new Lancha());
            }
        }

    }

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
            for (int j = 0; j < tamanio; j++) { //Recorro mi tablero para que muestre las posiciones de los barcos e islas
                if (this.mapa[i][j].isIsla()) { //Si isla es True
                    System.out.print("[I]");
                } else if (this.mapa[i][j].isBarcoAlLado()) { //Si la casilla contiene un barco a su lado
                    System.out.print("[-]");
                } else if (this.mapa[i][j].isAgua()) { //Si agua es True
                    System.out.print("[ ]");
                } else {//Si hay una nave
                    System.out.print("[N]");
                }
            }
            System.out.println("");
        }
    }

    public void verTableroDePiezasEnJuego() { 
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

    public void verTableroDeTiros(Tablero tableroEnemigo) {
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
                        System.out.print("[D]");
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

    public boolean marcarAtaque(int i, int j, Tablero tableroEnemigo) {
        tableroEnemigo.getMapa()[i][j].setHit(true); //Ingreso a la casilla elegida en el tablero enemigo y marco hit = true
        if (tableroEnemigo.getMapa()[i][j].getNave() != null) {
            tableroEnemigo.getMapa()[i][j].getNave().RecibirDaño(); //Ingreso a la referencia de la nave de esa casilla y utilizo RecibirDaño()
        }else{
            System.out.println("");
            System.out.println("!Agua!");
            System.out.println("");
        }
        return tableroEnemigo.getMapa()[i][j].getNave() != null;
    }

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
    }


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
    }


    public List<Nave> getNaves() {
        return naves;
    }

    public void setNaves(List<Nave> naves) {
        this.naves = naves;
    }

    public void setMapa(Casilla[][] mapa) {
        this.mapa = mapa;
    }

    public Casilla[][] getMapa() {
        return mapa;
    }


    public void setCasillaIsla(int i, int j, boolean t) {
        this.mapa[i][j].setAgua(false);
        this.mapa[i][j].setIsla(t);
    }
    
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
