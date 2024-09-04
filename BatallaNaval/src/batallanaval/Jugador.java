/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batallanaval;

import java.util.Arrays;
import java.util.Scanner;
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

    public void Atacar() {

    }

    public void colocarNave() {
        boolean flag = true;
        int columna, tamanio, res;
        String fila;
        tamanio = tablero.getMapa().length;

        String[] filas = filas(tamanio);

        if (tablero.getPortaviones().length > 0) {
            Portaaviones[] portaviones = tablero.getPortaviones();
            for (int i = 0; i < portaviones.length; i++) {
                while (flag) {
                    tablero.verTableroDePiezas(tamanio);
                    System.out.println("");

                    fila = darFila(filas, tamanio);
                    columna = darColumna(tamanio);

                    try {
                        if (esValido(fila, columna, filas)) {
                            if (esValidoDerecha(fila, columna, portaviones[i].getMAX_VIDA(), tamanio, filas) && esValidoAbajo(fila, columna, portaviones[i].getMAX_VIDA(), tamanio, filas)) {

                                if (movimiento()) {
                                    System.out.println("Se coloco con exito la pieza en la fila " + fila + " y columna " + columna);
                                }
                            }
                        } else {
                            if (esValidoDerecha(fila, columna, portaviones[i].getMAX_VIDA(), tamanio, filas)) {
                                System.out.println("Seleccione una opcion\n"
                                        + "1: Colocar a la derecha\n"
                                        + "2: Cambiar de fila y columna");
                            } else {
                                if (esValidoAbajo(fila, columna, portaviones[i].getMAX_VIDA(), tamanio, filas)) {
                                    System.out.println("Seleccione una opcion\n"
                                            + "1: Colocar para abajo\n"
                                            + "2: Cambiar de fila y columna");
                                } else {
                                    System.out.println("La fila y columna seleccionada no es valida, ver el mapa y volver a intentar");
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("Dar un numero correcto");
                    }

                }
            }
        }
    }

    private void ponerNaves(Nave nave, int size) {

    }

    private boolean esValido(String i, int j, String[] filas) {
        return this.tablero.getMapa()[Arrays.asList(filas).indexOf(i)][j].isAgua();
    }

    private boolean esValidoDerecha(String i, int j, int size, int tamanio, String[] filas) {
        boolean flag = true;
        if ((j + size) > tamanio - 1) {// si esta fuera de rango no se puede colocar esa pieza
            flag = false;
        } else {//comprueba si toda la trayectoria esta libre
            for (int k = 0; k < size; k++) {
                if ((this.tablero.getMapa()[Arrays.asList(filas).indexOf(i)][j + k].isAgua() == false) || (this.tablero.getMapa()[Arrays.asList(filas).indexOf(i)][j + k].isIsla() == true)) {
                    flag = false;
                }
            }
        }
        return flag;
    }

    private boolean esValidoAbajo(String i, int j, int size, int tamanio, String[] filas) {
        boolean flag = true;
        if ((Arrays.asList(filas).indexOf(i) + size) > tamanio - 1) {// si esta fuera de rango no se puede colocar esa pieza
            flag = false;
        } else {//comprueba si toda la trayectoria esta libre
            for (int k = 0; k < size; k++) {
                if ((this.tablero.getMapa()[Arrays.asList(filas).indexOf(i) + k][j].isAgua() == false) || (this.tablero.getMapa()[Arrays.asList(filas).indexOf(i) + k][j].isIsla() == true)) {
                    flag = false;
                }
            }
        }
        return flag;
    }

    private String[] filas(int size) {
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

    private String darFila(String[] filas, int size) {
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

    private int darColumna(int size) {
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

    private boolean movimiento() {
        boolean flag = true;
        boolean respuesta = false;
        int res;

        while (flag) {
            try {
                System.out.println("Seleccione una opcion\n"
                        + "1: Colocar a la derecha\n"
                        + "2: Colocar para abajo\n"
                        + "3: Cambiar de fila y columna");
                res = r.nextInt();
                r.nextInt();
                if (res == 1) {
                    movimientoDerecha();
                    respuesta = true;
                    flag = false;
                } else if (res == 2) {
                    movimientoAbajo();
                    respuesta = true;
                    flag = false;
                } else if (res == 3) {
                    flag = false;
                } else {
                    System.out.println("Dar un numero valido");
                }

            } catch (Exception e) {
            }

        }
        return respuesta;
    }

    private void movimientoDerecha() {

    }

    private void movimientoAbajo() {

    }
}
