/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batallanaval;

import java.util.*;
import piezas.Nave;
import tablero.Tablero;

/**
 * La clase Jugador representa a un jugador en el juego de Batalla Naval. Cada
 * jugador tiene un nombre de usuario (nick), un tablero donde se colocan sus
 * barcos, y lleva un registro del número de tiros realizados. Esta clase
 * también implementa la interfaz IMuestraTablero, que permite mostrar el
 * tablero del jugador.
 *
 * @author Mariano y Emiliano
 */
public class Jugador implements IMuestraTablero {

    Scanner r = new Scanner(System.in);

    private String nick;
    private Tablero tablero;
    private int tiros;

    /**
     * Constructor por defecto. Crea un jugador sin inicializar atributos.
     */
    public Jugador() {

    }

    /**
     * Constructor que inicializa el apodo del jugador y su tablero.
     *
     * @param nick El apodo del jugador.
     * @param tablero El tablero asociado al jugador.
     */
    public Jugador(String nick, Tablero tablero) {
        this.nick = nick;
        this.tablero = tablero;
        this.tiros = 0;
    }

    /**
     * Obtiene el apodo del jugador.
     *
     * @return El apodo (nick) del jugador.
     */
    public String getNick() {
        return nick;
    }

    /**
     * Establece el apodo del jugador.
     *
     * @param nick El nuevo apodo del jugador.
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * Obtiene el tablero asociado al jugador.
     *
     * @return El tablero del jugador.
     */
    public Tablero getTablero() {
        return tablero;
    }

    /**
     * Establece el tablero del jugador.
     *
     * @param tablero El nuevo tablero a asignar al jugador.
     */
    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    /**
     * Incrementa en uno el número de tiros realizados por el jugador.
     */
    public void incrementarTiros() {
        this.tiros++;
    }

    /**
     * Obtiene el número total de tiros realizados por el jugador.
     *
     * @return El número de tiros.
     */
    public int getTiros() {
        return tiros;
    }

    /**
     * Se itera sobre las naves disponibles del tablero
     * y se llama a la funcion colocarNave(), que se encarga
     * de colocar los barcos.
     */
    public void iterarNave() {
        Iterator<Nave> it = tablero.getNaves().iterator();
        while (it.hasNext()) {
            colocarNave(it.next());
        }

    }

    /**
     * Método que coloca las naves en el tablero. Se solicitan las coordenadas de fila y
     * columna donde la nave se debe colocar. El proceso de colocación de la
     * nave continúa hasta que la nave se coloque exitosamente en una posición
     * válida.
     * @param nave Podra se un objeto de la clase Portaaviones, Buque, Submarino, Crucero, Lancha.
     */
    public void colocarNave(Nave nave) {
        int columna, tamanio;
        String fila;
        tamanio = tablero.getMapa().length;
        String[] filas = filas(tamanio);
        boolean flag = true;

        while (flag) {
            tablero.verTableroDePiezas();
            System.out.println("");
            r.reset();
            System.out.println("Se procede a colocar una pieza de tipo " + nave.getClass().getSimpleName()+ ", de "+ nave.getVida() +" casillas.");
            System.out.println("");
            fila = darFila(filas, tamanio);
            columna = darColumna(tamanio);

            if (movimiento(fila, columna, nave, filas)) {
                System.out.println("Se coloco con exito la pieza en la fila " + fila + " y columna " + (columna + 1));
                flag = false;
                r.reset();
            }
        }

    }

    /**
     * Verifica si la posición es válida para colocar una nave.
     *
     * @param i la fila en la que se desea colocar la nave.
     * @param j la columna en la que se desea colocar la nave.
     * @param filas las filas del tablero.
     * @return true si la posición es válida, false en caso contrario.
     */
    private boolean esValido(String i, int j, String[] filas) {// valida si la casilla es en donde se coloca esta libr
        return (this.tablero.getMapa()[Arrays.asList(filas).indexOf(i.toUpperCase())][j].isAgua() && this.tablero.getMapa()[Arrays.asList(filas).indexOf(i.toUpperCase())][j].isBarcoAlLado() == false && this.tablero.getMapa()[Arrays.asList(filas).indexOf(i.toUpperCase())][j].isIsla() == false);
    }

    /**
     * Verifica si la trayectoria hacia la derecha es válida para colocar una
     * nave de tamaño determinado.
     *
     * @param i la fila inicial.
     * @param j la columna inicial.
     * @param size el tamaño de la nave.
     * @param tamanio el tamaño total del tablero.
     * @param filas las filas del tablero.
     * @return true si toda la trayectoria está libre, false en caso contrario.
     */
    private boolean esValidoDerecha(String i, int j, int size, int tamanio, String[] filas) {//valida si todo el intervalo es apto
        boolean flag = true;
        if ((j + size - 1) > tamanio - 1) {// si esta fuera de rango no se puede colocar esa pieza
            flag = false;
        } else {//comprueba si toda la trayectoria esta libre
            for (int k = 0; k < size; k++) {
                if ((this.tablero.getMapa()[Arrays.asList(filas).indexOf(i.toUpperCase())][j + k].isAgua() == false) || (this.tablero.getMapa()[Arrays.asList(filas).indexOf(i.toUpperCase())][j + k].isIsla() == true) || (this.tablero.getMapa()[Arrays.asList(filas).indexOf(i.toUpperCase())][j + k].isBarcoAlLado() == true)) {
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * Verifica si la trayectoria hacia abajo es válida para colocar una nave de
     * tamaño determinado.
     *
     * @param i la fila inicial.
     * @param j la columna inicial.
     * @param size el tamaño de la nave.
     * @param tamanio el tamaño total del tablero.
     * @param filas las filas del tablero.
     * @return true si toda la trayectoria está libre, false en caso contrario.
     */
    private boolean esValidoAbajo(String i, int j, int size, int tamanio, String[] filas) {//valida si todo el intervalo es apto
        boolean flag = true;
        if ((Arrays.asList(filas).indexOf(i.toUpperCase()) + size - 1) > tamanio - 1) {// si esta fuera de rango no se puede colocar esa pieza
            flag = false;
        } else {//comprueba si toda la trayectoria esta libre
            for (int k = 0; k < size; k++) {
                if ((this.tablero.getMapa()[Arrays.asList(filas).indexOf(i.toUpperCase()) + k][j].isAgua() == false) || (this.tablero.getMapa()[Arrays.asList(filas).indexOf(i.toUpperCase()) + k][j].isIsla() == true) || (this.tablero.getMapa()[Arrays.asList(filas).indexOf(i.toUpperCase()) + k][j].isBarcoAlLado() == true)) {
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * Devuelve un array de cadenas que representan las filas de un tablero. Las
     * filas varían en tamaño dependiendo del parámetro 'size'.
     *
     * @param size Tamaño del tablero
     * @return Un array de Strings que representan las filas del tablero.
     */
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

    /**
     * Permite al usuario seleccionar una fila válida desde un conjunto de
     * filas. Pide al usuario ingresar una letra de fila hasta que la entrada
     * sea válida.
     *
     * @param filas Un array de Strings que representan las filas del tablero.
     * @param size Tamaño del tablero.
     * @return Una cadena que representa la fila seleccionada por el usuario en
     * mayúsculas.
     */
    public String darFila(String[] filas, int size) {// devuelve el  valor de la fila1
        Scanner r = new Scanner(System.in);
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
                System.out.println("");
                System.out.println("Dar solo letras");
                System.out.println("");
                r.reset();

            }

        }
        return res.toUpperCase();
    }

    /**
     * Permite al usuario seleccionar una columna válida. Pide al usuario
     * ingresar un número de columna hasta que la entrada sea válida.
     *
     * @param size Tamaño del tablero (número máximo de columnas).
     * @return Un número entero que representa la columna seleccionada por el
     * usuario (índice base 0).
     */
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
                System.out.println("Dar un nùmero valido");
                System.out.println("");
            } finally {
                r.nextLine();
            }
        }
        return columna - 1;
    }

    /**
     * Menú que da posibilidad de colocar las naves. El usuario puede elegir
     * colocar la nave horizontalmente (derecha) o verticalmente (abajo), o
     * cambiar de fila y columna si la posición es inválida.
     *
     * @param fila La fila en la que se desea colocar la nave.
     * @param columna La columna en la que se desea colocar la nave.
     * @param nave La nave que se desea colocar.
     * @param filas Un array de Strings que representan las filas del tablero.
     * @return true si el movimiento es válido y la nave se ha colocado, false
     * de lo contrario.
     */
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
                        System.out.println("");
                        System.out.println("No se puede colocar la pieza de ninguna forma.\n"
                                + "Vuelva a cargar una posicion valida.\n"
                                + "(Recuerde usar el mapa)");
                        System.out.println("");
                        flag = false;
                        respuesta = false;
                    }
                } else {
                    System.out.println("");
                    System.out.println("No se puede colocar la pieza de ninguna forma.\n"
                            + "Vuelva a cargar una posicion valida.\n"
                            + "(Recuerde usar el mapa)");
                    System.out.println("");
                    flag = false;
                    respuesta = false;
                }

            } catch (Exception e) {
                System.out.println("");
                System.out.println("Error de seleccion");
                System.out.println("");
                r.nextLine();
                r.reset();

            }

        }
        return respuesta;
    }

    /**
     * Coloca una nave en el tablero de forma horizontal (hacia la derecha).
     * También marca las posiciones adyacentes a la nave como ocupadas para
     * evitar colisiones.
     *
     * @param fila La fila en la que se desea colocar la nave.
     * @param columna La columna inicial desde donde se colocará la nave.
     * @param nave La nave que se desea colocar.
     * @param filas Un array de Strings que representan las filas del tablero.
     */
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

    /**
     * Coloca una nave en el tablero de forma vertical (hacia abajo). También
     * marca las posiciones adyacentes a la nave como ocupadas para evitar
     * colisiones.
     *
     * @param fila La fila inicial desde donde se colocará la nave.
     * @param columna La columna en la que se desea colocar la nave.
     * @param nave La nave que se desea colocar.
     * @param filas Un array de Strings que representan las filas del tablero.
     */
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

                if (columna >= 1 && (((int) Arrays.asList(filas).indexOf(fila.toUpperCase()) + i)) < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + i + 1][columna - 1].setBarcoAlLado(true);//elemento en diagonal inferior izq de la pos inicial
                }
                if (columna < filas.length - 1 && (((int) Arrays.asList(filas).indexOf(fila.toUpperCase()) + i)) < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + i + 1][columna + 1].setBarcoAlLado(true);//diagonal inferior der de la pos inicial
                }
                if ((((int) Arrays.asList(filas).indexOf(fila.toUpperCase()) + i)) < filas.length - 1) {
                    tablero.getMapa()[Arrays.asList(filas).indexOf(fila.toUpperCase()) + i + 1][columna].setBarcoAlLado(true);//elemento abajo de la pos inicial

                }
            }
        }
    }

}
