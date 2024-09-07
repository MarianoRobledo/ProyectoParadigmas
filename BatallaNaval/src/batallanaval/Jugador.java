/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batallanaval;

import java.util.*;
import piezas.Nave;
import piezas.Portaaviones;
import tablero.Tablero;

/**
 *
 * @author Mariano
 */
public class Jugador {

    Scanner r = new Scanner(System.in);

    private String nick;
    private Tablero tablero;

    public Jugador(String nick, Tablero tablero) {
        this.nick = nick;
        this.tablero = tablero;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
    
    
    
    public void atacar() {

    }

    public void colocarNave() {
        Iterator<Nave> it = tablero.getNaves().iterator();
        int columna, tamanio;
        String fila;
        tamanio = tablero.getMapa().length;

        String[] filas = filas(tamanio);

        while (it.hasNext()) {
            boolean flag = true;
            Nave nave = it.next();
            while (flag) {
                tablero.verTableroDePiezas();
                System.out.println("");
                r.reset();
                System.out.println("Se procede a colocar una pieza de tipo " + nave.getClass().getSimpleName());
                fila = darFila(filas, tamanio);

                columna = darColumna(tamanio);

                if (movimiento(fila, columna, nave, filas)) {
                    System.out.println("Se coloco con exito la pieza en la fila " + fila + " y columna " + (columna+1));
                    flag = false;
                    r.reset();
                }
            }
        }
    }

    private void ponerNaves(Nave nave, int size) {

    }

    private boolean esValido(String i, int j, String[] filas) {// valida si la casilla es en donde se coloca esta libr
        return (this.tablero.getMapa()[Arrays.asList(filas).indexOf(i.toUpperCase())][j].isAgua() && this.tablero.getMapa()[Arrays.asList(filas).indexOf(i.toUpperCase())][j].isBarcoAlLado() == false && this.tablero.getMapa()[Arrays.asList(filas).indexOf(i.toUpperCase())][j].isIsla() == false);
    }

    private boolean esValidoDerecha(String i, int j, int size, int tamanio, String[] filas) {//valida si todo el intervalo es apto
        boolean flag = true;
        if ((j + size - 1) > tamanio - 1) {// si esta fuera de rango no se puede colocar esa pieza
            flag = false;
        } else {//comprueba si toda la trayectoria esta libre
            for (int k = 0; k < size ; k++) {
                if ((this.tablero.getMapa()[Arrays.asList(filas).indexOf(i.toUpperCase())][j + k].isAgua() == false) || (this.tablero.getMapa()[Arrays.asList(filas).indexOf(i.toUpperCase())][j + k].isIsla() == true) || (this.tablero.getMapa()[Arrays.asList(filas).indexOf(i.toUpperCase())][j + k].isBarcoAlLado() == true)) {
                    flag = false;
                }
            }
        }
        return flag;
    }

    private boolean esValidoAbajo(String i, int j, int size, int tamanio, String[] filas) {//valida si todo el intervalo es apto
        boolean flag = true;
        if ((Arrays.asList(filas).indexOf(i.toUpperCase()) + size - 1) > tamanio - 1) {// si esta fuera de rango no se puede colocar esa pieza
            flag = false;
        } else {//comprueba si toda la trayectoria esta libre
            for (int k = 0; k < size ; k++) {
                if ((this.tablero.getMapa()[Arrays.asList(filas).indexOf(i.toUpperCase()) + k][j].isAgua() == false) || (this.tablero.getMapa()[Arrays.asList(filas).indexOf(i.toUpperCase()) + k][j].isIsla() == true) || (this.tablero.getMapa()[Arrays.asList(filas).indexOf(i.toUpperCase()) + k][j].isBarcoAlLado() == true)) {
                    flag = false;
                }
            }
        }
        return flag;
    }
  
    //Modificacion del metodo, ahora es publico
    public String[] filas(int size) {//devuelve el array de filas 
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

    //Modificacion del metodo, ahora es publico
    public String darFila(String[] filas, int size) {// devuelve el  valor de la fila1
        String res = "";
        r.reset();
        boolean flag = true;
        while (flag) {
            try {
                System.out.println("Seleccionar una fila entre " + Arrays.toString(filas));
                res = r.nextLine();
                if (Arrays.asList(filas).contains(res.toUpperCase())) {
                    flag = false;
                    r.reset();
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Dar solo letras");
                System.out.println("");
                r.reset();

            }

        }
        return res.toUpperCase();
    }

    //Modificacion del metodo, ahora es publico
    public int darColumna(int size) {//devuelve el valor de la columna
        int columna = 0;
        boolean flag = true;

        while (flag) {
            try {
                System.out.println("Dar un valor de columna entre 1 y " + size);
                columna = r.nextInt();
                if (columna >= 1 && columna <= size) {
                    flag = false;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Dar un nùmero valido");
                System.out.println("");
            } finally {
                r.reset();
            }
        }
        return columna - 1;
    }

    private boolean movimiento(String fila, int columna, Nave nave, String[] filas) {//logica para colocar naves 
        boolean flag = true;
        boolean respuesta = false;
        int res;

        while (flag) {
            try {
                if (esValido(fila, columna, filas)) {
                    if (esValidoDerecha(fila, columna, nave.getVida(), filas.length, filas) && esValidoAbajo(fila, columna, nave.getVida(), filas.length, filas)) {
                        System.out.println("Seleccione una opcion\n"
                                + "1: Colocar a la derecha\n"
                                + "2: Colocar para abajo\n"
                                + "3: Cambiar de fila y columna");
                        res = r.nextInt();
                        if (res == 1) {
                            movimientoDerecha(fila, columna, nave, filas);
                            respuesta = true;
                            flag = false;
                            r.reset();
                        } else if (res == 2) {
                            movimientoAbajo(fila, columna, nave, filas);
                            respuesta = true;
                            flag = false;
                            r.reset();
                        } else if (res == 3) {
                            flag = false;
                            respuesta = false;
                            r.reset();
                        } else {
                            System.out.println("Dar un numero valido");
                            System.out.println("");
                            r.reset();
                        }
                    } else if (esValidoDerecha(fila, columna, nave.getVida(), filas.length, filas)) {
                        System.out.println("Seleccione una opcion\n"
                                + "1: Colocar a la derecha\n"
                                + "2: Cambiar de fila y columna");
                        res = r.nextInt();
                        if (res == 1) {
                            movimientoDerecha(fila, columna, nave, filas);
                            respuesta = true;
                            flag = false;
                            r.reset();
                        } else if (res == 2) {
                            flag = false;
                            respuesta = false;
                            r.reset();
                        } else {
                            System.out.println("Dar un numero valido");
                            System.out.println("");
                            r.reset();
                        }
                    } else if (esValidoAbajo(fila, columna, nave.getVida(), filas.length, filas)) {
                        System.out.println("Seleccione una opcion\n"
                                + "1: Colocar para abajo\n"
                                + "2: Cambiar de fila y columna");
                        res = r.nextInt();
                        if (res == 1) {
                            movimientoAbajo(fila, columna, nave, filas);
                            respuesta = true;
                            flag = false;
                            r.reset();
                        } else if (res == 2) {
                            flag = false;
                            respuesta = false;
                            r.reset();
                        } else {
                            System.out.println("Dar un numero valido");
                            System.out.println("");
                            r.reset();
                        }
                    } else {
                        System.out.println("No se puede colocar la pieza de ninguna forma.\n"
                                + "Vuelva a cargar una posicion valida.\n"
                                + "(Recuerde usar el mapa)");
                        System.out.println("");
                        flag = false;
                        respuesta = false;
                    }
                } else {
                    System.out.println("No se puede colocar la pieza de ninguna forma.\n"
                            + "Vuelva a cargar una posicion valida.\n"
                            + "(Recuerde usar el mapa)");
                    System.out.println("");
                    flag = false;
                    respuesta = false;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Error de seleccion");
                System.out.println("");
                r.nextLine();
                r.reset();

            }

        }
        return respuesta;
    }

    private void movimientoDerecha(String fila, int columna, Nave nave, String[] filas) {
        for (int i = 0; i < nave.getVida(); i++) {
            tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase())][columna + i].setNave(nave);
            tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase())][columna + i].setAgua(false);
            if (nave.getVida() == 1) {
                if (Arrays.asList(filas).indexOf(fila.toUpperCase()) >= 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) - 1][columna + i].setBarcoAlLado(true);//elemento de arriba de la pos inicial
                }
                if (Arrays.asList(filas).indexOf(fila.toUpperCase()) < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + 1][columna + i].setBarcoAlLado(true);//elemento abajo de la pos inicial
                }
                if (columna >= 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase())][columna + i - 1].setBarcoAlLado(true);//elemento izq de la pos inicial 
                }
                if (columna + i < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase())][columna + i + 1].setBarcoAlLado(true);//elemento der de la pos inicial
                }
                if (Arrays.asList(filas).indexOf(fila.toUpperCase()) >= 1 && columna >= 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) - 1][columna + i - 1].setBarcoAlLado(true);//elemento en diagonal izq superior de la pos inicial 
                }
                if (Arrays.asList(filas).indexOf(fila.toUpperCase()) >= 1 && columna + i < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) - 1][columna + i + 1].setBarcoAlLado(true);//elemento en diagonal der superior de la pos inicial
                }
                if (Arrays.asList(filas).indexOf(fila.toUpperCase()) < filas.length - 1 && columna >= 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + 1][columna + i - 1].setBarcoAlLado(true);//diagonal inferior izq de la pos inicial 
                }
                if (Arrays.asList(filas).indexOf(fila.toUpperCase()) < filas.length - 1 && columna + i < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + 1][columna + i + 1].setBarcoAlLado(true);//elemento en diagonal der inferior de la pos inicial
                }

            } else if (i < nave.getVida() - 1 && i == 0) {
                if (Arrays.asList(filas).indexOf(fila.toUpperCase()) >= 1) {//Primer posicion
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) - 1][columna + i].setBarcoAlLado(true);//elemento de arriba de la pos inicial
                }

                if (Arrays.asList(filas).indexOf(fila.toUpperCase()) < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + 1][columna + i].setBarcoAlLado(true);//elemento abajo de la pos inicial
                }
                if (Arrays.asList(filas).indexOf(fila.toUpperCase()) >= 1 && columna >= 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) - 1][columna + i - 1].setBarcoAlLado(true);//elemento en diagonal izq superior de la pos inicial 
                }
                if (Arrays.asList(filas).indexOf(fila.toUpperCase()) < filas.length - 1 && columna >= 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + 1][columna + i - 1].setBarcoAlLado(true);//diagonal inferior izq de la pos inicial 
                }
                if (columna >= 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase())][columna + i - 1].setBarcoAlLado(true);//elemento izq de la pos inicial                    
                }

            } else if (i < nave.getVida() - 1) {//posiciones del medio
                if (Arrays.asList(filas).indexOf(fila.toUpperCase()) >= 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) - 1][columna + i].setBarcoAlLado(true);//elemento de arriba de la pos inicial
                }
                if (Arrays.asList(filas).indexOf(fila.toUpperCase()) < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + 1][columna + i].setBarcoAlLado(true);//elemento abajo de la pos inicial
                }
            } else {//ultima posicion
                if (Arrays.asList(filas).indexOf(fila.toUpperCase()) >= 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) - 1][columna + i].setBarcoAlLado(true);//elemento de arriba de la pos inicial
                }
                if (Arrays.asList(filas).indexOf(fila.toUpperCase()) < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + 1][columna + i].setBarcoAlLado(true);//elemento abajo de la pos inicial
                }
                if (Arrays.asList(filas).indexOf(fila.toUpperCase()) >= 1 && columna + i < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) - 1][columna + i + 1].setBarcoAlLado(true);//elemento en diagonal der superior de la pos inicial
                }
                if (Arrays.asList(filas).indexOf(fila.toUpperCase()) < filas.length - 1 && columna + i < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + 1][columna + i + 1].setBarcoAlLado(true);//elemento en diagonal der inferior de la pos inicial
                }
                if (columna + i < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase())][columna + i + 1].setBarcoAlLado(true);//elemento der de la pos inicial
                }
            }

        }

    }

    private void movimientoAbajo(String fila, int columna, Nave nave, String[] filas) {
        for (int i = 0; i < nave.getVida(); i++) {
            tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + i][columna].setNave(nave);
            tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + i][columna].setAgua(false);
            if (nave.getVida() == 1) {
                if (columna >= 1) { //Primer posicion
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + i][columna - 1].setBarcoAlLado(true);//elemento a la izq de la pos inicial
                }
                if (columna < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + i][columna + 1].setBarcoAlLado(true);//elemento a la der de la pos inicial
                }
                if (columna >= 1 && Arrays.asList(filas).indexOf(fila.toUpperCase()) >= 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + i - 1][columna - 1].setBarcoAlLado(true);//elemento en diagonal superior izq de la pos inicial 
                }
                if (columna >= 1 && columna < filas.length - 1 && Arrays.asList(filas).indexOf(fila.toUpperCase()) >= 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + i - 1][columna + 1].setBarcoAlLado(true);//diagonal superior der de la pos inicial   
                }
                if (Arrays.asList(filas).indexOf(fila.toUpperCase()) >= 1 && Arrays.asList(filas).indexOf(fila.toUpperCase()) >= 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + i - 1][columna].setBarcoAlLado(true);//elemento arriba de la pos inicial
                }
                if (columna >= 1 && Arrays.asList(filas).indexOf(fila.toUpperCase()) < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + i + 1][columna - 1].setBarcoAlLado(true);//elemento en diagonal inferior izq de la pos inicial
                }
                if (columna < filas.length - 1 && Arrays.asList(filas).indexOf(fila.toUpperCase()) < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + i + 1][columna + 1].setBarcoAlLado(true);//diagonal inferior der de la pos inicial
                }
                if (Arrays.asList(filas).indexOf(fila.toUpperCase()) < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + i + 1][columna].setBarcoAlLado(true);//elemento abajo de la pos inicial

                }
            } else if (i < nave.getVida() - 1 && i == 0) {
                if (columna >= 1) { //Primer posicion
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + i][columna - 1].setBarcoAlLado(true);//elemento a la izq de la pos inicial
                }
                if (columna < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + i][columna + 1].setBarcoAlLado(true);//elemento a la der de la pos inicial
                }
                if (columna >= 1 && Arrays.asList(filas).indexOf(fila.toUpperCase()) >= 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + i - 1][columna - 1].setBarcoAlLado(true);//elemento en diagonal superior izq de la pos inicial 
                }
                if (columna >= 1 && columna < filas.length - 1 && Arrays.asList(filas).indexOf(fila.toUpperCase()) >= 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + i - 1][columna + 1].setBarcoAlLado(true);//diagonal superior der de la pos inicial   
                }
                if (Arrays.asList(filas).indexOf(fila.toUpperCase()) >= 1 && Arrays.asList(filas).indexOf(fila.toUpperCase()) >= 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + i - 1][columna].setBarcoAlLado(true);//elemento arriba de la pos inicial
                }

            } else if (i < nave.getVida() - 1) {//posiciones del medio
                if (columna >= 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + i][columna - 1].setBarcoAlLado(true);//elemento a la izq de la pos inicial
                }
                if (columna < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + i][columna + 1].setBarcoAlLado(true);//elemento a la der de la pos inicial
                }
            } else {//ultima posicion
                if (columna >= 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + i][columna - 1].setBarcoAlLado(true);//elemento a la izq de la pos inicial
                }
                if (columna < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + i][columna + 1].setBarcoAlLado(true);//elemento a la der de la pos inicial
                }
                if (columna >= 1 && (((int)Arrays.asList(filas).indexOf(fila.toUpperCase()) + i)) < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + i + 1][columna - 1].setBarcoAlLado(true);//elemento en diagonal inferior izq de la pos inicial
                }
                if (columna < filas.length - 1 && (((int)Arrays.asList(filas).indexOf(fila.toUpperCase()) + i)) < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + i + 1][columna + 1].setBarcoAlLado(true);//diagonal inferior der de la pos inicial
                }
                if ((((int)Arrays.asList(filas).indexOf(fila.toUpperCase()) + i)) < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + i + 1][columna].setBarcoAlLado(true);//elemento abajo de la pos inicial
                }
            }
        }
    }

}
