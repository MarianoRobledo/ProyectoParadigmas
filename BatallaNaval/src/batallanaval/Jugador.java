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

    
    
    
    public void atacar() {
        
    }

    public void colocarNave() {
        Iterator<Nave> it = tablero.getNaves().iterator();
        boolean flag = true;
        int columna, tamanio, res;
        String fila;
        tamanio = tablero.getMapa().length;

        String[] filas = filas(tamanio);

        while (it.hasNext()) {
            //tablero.verTableroDePiezas(tamanio);
            //System.out.println("");
            if (it.next().getClass().getSimpleName().equals("Portaaviones")) {
                System.out.println("Se procede a colocar una pieza de tipo portaavion");
                fila = darFila(filas, tamanio);
                columna = darColumna(tamanio);

                if (movimiento(fila, columna, it.next(), filas)) {
                    System.out.println("Se coloco con exito la pieza en la fila " + fila + " y columna " + columna);
                    flag = false;
                }
            }
            
            if (it.next().getClass().getSimpleName().equals("Buque")) {
                System.out.println("Se procede a colocar una pieza de tipo buque");
                fila = darFila(filas, tamanio);
                columna = darColumna(tamanio);

                if (movimiento(fila, columna, it.next(), filas)) {
                    System.out.println("Se coloco con exito la pieza en la fila " + fila + " y columna " + columna);
                    flag = false;
                }
            }
            
            if (it.next().getClass().getSimpleName().equals("Submarino")) {
                System.out.println("Se procede a colocar una pieza de tipo submarino");
                fila = darFila(filas, tamanio);
                columna = darColumna(tamanio);

                if (movimiento(fila, columna, it.next(), filas)) {
                    System.out.println("Se coloco con exito la pieza en la fila " + fila + " y columna " + columna);
                    flag = false;
                }
            }
            
            if (it.next().getClass().getSimpleName().equals("Crucero")) {
                System.out.println("Se procede a colocar una pieza de tipo crucero");
                fila = darFila(filas, tamanio);
                columna = darColumna(tamanio);

                if (movimiento(fila, columna, it.next(), filas)) {
                    System.out.println("Se coloco con exito la pieza en la fila " + fila + " y columna " + columna);
                    flag = false;
                }
            }
            
            if (it.next().getClass().getSimpleName().equals("Lancha")) {
                System.out.println("Se procede a colocar una pieza de tipo lancha");
                fila = darFila(filas, tamanio);
                columna = darColumna(tamanio);

                if (movimiento(fila, columna, it.next(), filas)) {
                    System.out.println("Se coloco con exito la pieza en la fila " + fila + " y columna " + columna);
                    flag = false;
                }
            }

        }

    }

    private void ponerNaves(Nave nave, int size) {

    }

    private boolean esValido(String i, int j, String[] filas) {// valida si la casilla es en donde se coloca esta libre
        return (this.tablero.getMapa()[Arrays.asList(filas).indexOf(i)][j].isAgua() && this.tablero.getMapa()[Arrays.asList(filas).indexOf(i)][j].isBarcoAlLado() == false);
    }

    private boolean esValidoDerecha(String i, int j, int size, int tamanio, String[] filas) {//valida si todo el intervalo es apto
        boolean flag = true;
        if ((j + size) > tamanio - 1) {// si esta fuera de rango no se puede colocar esa pieza
            flag = false;
        } else {//comprueba si toda la trayectoria esta libre
            for (int k = 0; k < size; k++) {
                if ((this.tablero.getMapa()[Arrays.asList(filas).indexOf(i)][j + k].isAgua() == false) || (this.tablero.getMapa()[Arrays.asList(filas).indexOf(i)][j + k].isIsla() == true) || (this.tablero.getMapa()[Arrays.asList(filas).indexOf(i)][j + k].isBarcoAlLado() == true)) {
                    flag = false;
                }
            }
        }
        return flag;
    }

    private boolean esValidoAbajo(String i, int j, int size, int tamanio, String[] filas) {//valida si todo el intervalo es apto
        boolean flag = true;
        if ((Arrays.asList(filas).indexOf(i) + size) > tamanio - 1) {// si esta fuera de rango no se puede colocar esa pieza
            flag = false;
        } else {//comprueba si toda la trayectoria esta libre
            for (int k = 0; k < size; k++) {
                if ((this.tablero.getMapa()[Arrays.asList(filas).indexOf(i) + k][j].isAgua() == false) || (this.tablero.getMapa()[Arrays.asList(filas).indexOf(i) + k][j].isIsla() == true) || (this.tablero.getMapa()[Arrays.asList(filas).indexOf(i) + k][j].isBarcoAlLado() == false)) {
                    flag = false;
                }
            }
        }
        return flag;
    }

    private String[] filas(int size) {//devuelve el array de filas 
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

    private String darFila(String[] filas, int size) {// devuelve el  valor de la fila
        String res = "";
        boolean flag = true;
        while (flag) {
            try {
                System.out.println("Seleccionar una fila entre " + Arrays.toString(filas));
                res = r.nextLine();
                if (Arrays.asList(filas).contains(res.toUpperCase())) {
                    flag = false;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Dar solo letras");
                System.out.println("");
            }
        }
        return res;
    }

    private int darColumna(int size) {//devuelve el valor de la columna
        int columna = 0;
        boolean flag = true;

        while (flag) {
            try {
                System.out.println("Dar un valor entre 1 y " + size);
                columna = r.nextInt();
                r.nextInt();
                if (columna >= 1 && columna <= size) {
                    flag = false;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Dar un nùmero valido");
                System.out.println("");
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
                        r.nextInt();
                        if (res == 1) {
                            movimientoDerecha(fila, columna, nave, filas);
                            respuesta = true;
                            flag = false;
                        } else if (res == 2) {
                            movimientoAbajo(fila, columna, nave, filas);
                            respuesta = true;
                            flag = false;
                        } else if (res == 3) {
                            flag = false;
                        } else {
                            System.out.println("Dar un numero valido");
                            System.out.println("");
                        }
                    } else if (esValidoDerecha(fila, columna, nave.getVida(), filas.length, filas)) {
                        System.out.println("Seleccione una opcion\n"
                                + "1: Colocar a la derecha\n"
                                + "2: Cambiar de fila y columna");
                        res = r.nextInt();
                        r.nextInt();
                        if (res == 1) {
                            movimientoDerecha(fila, columna, nave, filas);
                            respuesta = true;
                            flag = false;
                        } else if (res == 2) {
                            flag = false;
                        } else {
                            System.out.println("Dar un numero valido");
                            System.out.println("");
                        }
                    } else {
                        System.out.println("Seleccione una opcion\n"
                                + "1: Colocar para abajo\n"
                                + "2: Cambiar de fila y columna");
                        res = r.nextInt();
                        r.nextInt();
                        if (res == 1) {
                            movimientoAbajo(fila, columna, nave, filas);
                            respuesta = true;
                            flag = false;
                        } else if (res == 2) {
                            flag = false;
                        } else {
                            System.out.println("Dar un numero valido");
                            System.out.println("");
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Dar un numero valido");
                System.out.println("");
            }

        }
        return respuesta;
    }

    private void movimientoDerecha(String fila, int columna, Nave nave, String[] filas) {
        for (int i = 0; i < nave.getVida(); i++) {
            tablero.getMapa()[Arrays.asList(filas).indexOf(fila)][columna + i].setNave(nave);
            tablero.getMapa()[Arrays.asList(filas).indexOf(fila)][columna + i].setAgua(false);
            if (nave.getVida() == 1) {
                if (Arrays.asList(filas).indexOf(fila) >= 1) {//Primer posicion
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila) - 1][columna + i].setBarcoAlLado(true);//elemento de arriba de la pos inicial
                }

                if (Arrays.asList(filas).indexOf(fila) < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila) + 1][columna + i].setBarcoAlLado(true);//elemento abajo de la pos inicial
                }
                if (columna >= 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila) - 1][columna + i - 1].setBarcoAlLado(true);//elemento en diagonal superior de la pos inicial
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila)][columna + i - 1].setBarcoAlLado(true);//elemento atras de la pos inicial
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila) + 1][columna + i - 1].setBarcoAlLado(true);//diagonal inferior de la pos inicial
                }
                if (columna < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila)][columna + i + 1].setBarcoAlLado(true);//elemento atras de la pos inicial
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila) + 1][columna + i + 1].setBarcoAlLado(true);//diagonal inferior de la pos inicial
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila) + 1][columna + i].setBarcoAlLado(true);//elemento abajo de la pos inicial
                }

            } else if (i < nave.getVida() - 1 && i == 0) {
                if (Arrays.asList(filas).indexOf(fila) >= 1) {//Primer posicion
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila) - 1][columna + i].setBarcoAlLado(true);//elemento de arriba de la pos inicial
                }

                if (Arrays.asList(filas).indexOf(fila) < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila) + 1][columna + i].setBarcoAlLado(true);//elemento abajo de la pos inicial
                }
                if (columna >= 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila) - 1][columna + i - 1].setBarcoAlLado(true);//elemento en diagonal superior de la pos inicial
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila)][columna + i - 1].setBarcoAlLado(true);//elemento atras de la pos inicial
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila) + 1][columna + i - 1].setBarcoAlLado(true);//diagonal inferior de la pos inicial
                }

            } else if (i < nave.getVida() - 1) {//posiciones del medio
                if (Arrays.asList(filas).indexOf(fila) >= 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila) - 1][columna + i].setBarcoAlLado(true);//elemento de arriba de la pos inicial
                }
                if (Arrays.asList(filas).indexOf(fila) < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila) + 1][columna + i].setBarcoAlLado(true);//elemento abajo de la pos inicial
                }
            } else {//ultima posicion
                if (Arrays.asList(filas).indexOf(fila) >= 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila) - 1][columna + i].setBarcoAlLado(true);//elemento de arriba de la pos inicial
                }
                if (Arrays.asList(filas).indexOf(fila) < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila) - 1][columna + i + 1].setBarcoAlLado(true);//elemento en diagonal superior de la pos inicial
                }
                if (columna < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila)][columna + i + 1].setBarcoAlLado(true);//elemento atras de la pos inicial
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila) + 1][columna + i + 1].setBarcoAlLado(true);//diagonal inferior de la pos inicial
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila) + 1][columna + i].setBarcoAlLado(true);//elemento abajo de la pos inicial
                }
            }
        }

    }

    private void movimientoAbajo(String fila, int columna, Nave nave, String[] filas) {
        for (int i = 0; i < nave.getVida(); i++) {
            tablero.getMapa()[Arrays.asList(filas).indexOf(fila) + i][columna].setNave(nave);
            tablero.getMapa()[Arrays.asList(filas).indexOf(fila) + i][columna].setAgua(false);
            if (i < nave.getVida() - 1 && i == 0) {
                if (Arrays.asList(filas).indexOf(fila) >= 1) {//Primer posicion
                    if (columna >= 1) {
                        tablero.getMapa()[Arrays.asList(filas).indexOf(fila) + i][columna - 1].setBarcoAlLado(true);//elemento a la izq de la pos inicial
                    }
                    if (columna < filas.length - 1) {
                        tablero.getMapa()[Arrays.asList(filas).indexOf(fila) + i][columna + 1].setBarcoAlLado(true);//elemento a la der de la pos inicial
                    }
                    if (Arrays.asList(filas).indexOf(fila) > 0) {
                        tablero.getMapa()[Arrays.asList(filas).indexOf(fila) + i - 1][columna - 1].setBarcoAlLado(true);//elemento en diagonal superior izq de la pos inicial
                        tablero.getMapa()[Arrays.asList(filas).indexOf(fila) + i - 1][columna].setBarcoAlLado(true);//elemento arriba de la pos inicial
                        tablero.getMapa()[Arrays.asList(filas).indexOf(fila) + i - 1][columna + 1].setBarcoAlLado(true);//diagonal superior der de la pos inicial
                    }
                }
            } else if (i < nave.getVida() - 1) {//posiciones del medio
                if (columna >= 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila) + i][columna - 1].setBarcoAlLado(true);//elemento a la izq de la pos inicial
                }
                if (columna < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila) + i][columna + 1].setBarcoAlLado(true);//elemento a la der de la pos inicial
                }
            } else {//ultima posicion
                if (columna >= 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila) + i][columna - 1].setBarcoAlLado(true);//elemento a la izq de la pos inicial
                }
                if (columna < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila) + i][columna + 1].setBarcoAlLado(true);//elemento a la der de la pos inicial
                }
                if (Arrays.asList(filas).indexOf(fila) < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila) + i + 1][columna - 1].setBarcoAlLado(true);//elemento en diagonal inferior izq de la pos inicial
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila) + i + 1][columna].setBarcoAlLado(true);//elemento abajo de la pos inicial
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila) + i + 1][columna + 1].setBarcoAlLado(true);//diagonal inferior der de la pos inicial
                }
            }
        }
    }
}
