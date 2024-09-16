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
 * La clase Main contiene el método principal que ejecuta el juego de
 * Batalla Naval.
 *
 * Este programa permite a dos jugadores participar en una partida de Batalla
 * Naval, donde cada jugador coloca sus barcos en un tablero y luego se turnan
 * para atacar al adversario. El juego incluye opciones para iniciar una
 * partida, configurar los parámetros del juego, obtener información sobre el
 * juego y salir del programa.
 */
public class Main {

    /**
     * El método principal que arranca el juego de Batalla Naval.
     *
     * Este método gestiona el menú principal del juego, permite a los jugadores
     * configurar el juego, iniciar una partida y proporcionar información sobre
     * el juego. Controla el flujo del juego, incluyendo la colocación de
     * barcos, los turnos de ataque y la determinación del ganador.
     *
     * @param args Los argumentos de línea de comandos (no utilizados en este
     * programa).
     */
    public static void main(String[] args) {
        Scanner r = new Scanner(System.in);
        String res, opc;
        BatallaNaval b1 = new BatallaNaval();
        opc = "";
        System.out.println("                    BATALLA NAVAL");
        while (!opc.equals("4")) {
            
            System.out.println("BIENVENIDO AL MENU DE BATALLA NAVAL");
            System.out.println("1: Iniciar Juego");
            System.out.println("2: Configuracion");
            System.out.println("3: Informacion Del Juego");
            System.out.println("4: Salir");
            opc = r.nextLine();
            if (opc.equals("1")) { //TODA LA LOGICA DE EJECUCION DEL JUEGO

                String fila;
                int selector, columna;
                boolean flag = true;
                boolean juego = true;
                boolean valor;
                int turnos = 0;
                String[] filas;

                Tablero t1 = new Tablero(b1.getTamaño(), b1.getPortaaviones(), b1.getBuques(), b1.getSubmarinos(), b1.getCruceros(), b1.getLanchas());
                Tablero t2 = new Tablero(b1.getTamaño(), b1.getPortaaviones(), b1.getBuques(), b1.getSubmarinos(), b1.getCruceros(), b1.getLanchas());
                System.out.println("");
                System.out.println("");
                System.out.println("Dar el nombre del primer jugador:");
                res = r.nextLine();
                Jugador j1 = new Jugador(res, t1);
                r.reset();
                System.out.println("");
                System.out.println("");
                System.out.println("Dar el nombre del segundo jugador:");
                res = r.nextLine();
                Jugador j2 = new Jugador(res, t2);

                b1.setJugador1(j1);
                b1.setJugador2(j2);
                muchoEspacio();
                System.out.println("");
                System.out.println(j1.getNick() + " coloque las naves en el tablero");
                System.out.println("");
                System.out.println("----------------------------------------------------");
                j1.colocarNave();
                System.out.println("");
                muchoEspacio();
                System.out.println("----------------------------------------------------");
                System.out.println("");
                System.out.println(j2.getNick() + " coloque las naves en el tablero");
                System.out.println("");
                j2.colocarNave();
                muchoEspacio();
                
                filas = b1.getJugador1().filas(b1.getTamaño());

                System.out.println("");
                System.out.println("----------------------------------------------------");
                System.out.println("Comienza el juego");
                System.out.println("----------------------------------------------------");

                boolean ataqueDisponible;
                boolean tiroFinal = false; //CASO ESPECIAL GANE EL JUGADOR 1 Y TENGA SU ULTIMO TIRO EL JUGADOR 2
                while (juego) {
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    System.out.println("----------------------------------------------------");
                    System.out.println("Turno nº: " + ++turnos);
                    System.out.println("Turno del jugador: " + b1.getJugador1().getNick());
                    //System.out.println("Cantidad de aciertos " + j1.getTiros());
                    System.out.println("----------------------------------------------------");
                    flag = true;
                    //TURNO DEL JUGADOR 1
                    while (flag) {
                        res = "";
                        ataqueDisponible = false;
                        selector = verificadorDeNumero();
                        if (selector == 1) {

                            if (poderDisponible(j1)) {
                                while (!res.equals("1") && !res.equals("2")) {
                                    System.out.println("1: Utilizar poder\n"
                                            + "2: Ataque");
                                    res = r.nextLine();
                                    if (res.equals("1")) {
                                        ataqueDisponible = true;
                                        flag = mostrarPoderes(j1, j2);
                                        if (!quedanBarcosASalvo(j2)) {
                                            System.out.println("¡Has destruido todas las naves enemigas!");
                                            flag = false;
                                            juego = false;
                                            if (turnos == b1.getTurnos() - 1) {
                                                tiroFinal = true;
                                            }

                                        }
                                    } else if (!res.equals("1") && !res.equals("2")) {
                                        System.out.println("Ingrese un numero valido");
                                    }
                                }
                                
                            }
                            while (!ataqueDisponible) {
                                System.out.println("");
                                System.out.println("Dar las posicion a atacar");
                                System.out.println("");

                                fila = b1.getJugador1().darFila(filas, b1.getTamaño());
                                columna = b1.getJugador1().darColumna(b1.getTamaño());
                                //verifica si la posicion elegida fue atacada
                                ataqueDisponible = esAtaqueValido(b1.getJugador2(), fila, columna, filas);
                                //si la posicion esta permitida, realizar el ataque
                                if (ataqueDisponible) {
                                    valor = (b1.ataque(j2, fila, columna, filas));
                                    if (valor) {
                                        j1.incrementarTiros();
                                        //verifica si el tiro dado destruyo toda la flota enemiga
                                        if (!quedanBarcosASalvo(j2)) {
                                            System.out.println("¡Has destruido todas las naves enemigas!");
                                            flag = false;
                                            juego = false;
                                            if (turnos == b1.getTurnos() - 1) {
                                                tiroFinal = true;
                                            }
                                        }

                                    } else {
                                        flag = false;
                                    }
                                } else {
                                    System.out.println("");
                                    System.out.println("No es posible atacar esa posicion porque ya ha sido atacada, elija otra");
                                        System.out.println("");
                                }

                            }
                        } else if (selector == 3) {
                            b1.getJugador1().getTablero().barcosASalvo();
                        } else if (selector == 4) {
                            b1.getJugador1().getTablero().barcosHundidos();
                        } else {
                            b1.getJugador1().getTablero().verTableroDePiezasEnJuego();
                            System.out.println("");
                            b1.getJugador1().getTablero().verTableroDeTiros(b1.getJugador2().getTablero());
                            System.out.println("");

                        }
                    }
                    muchoEspacio();
                    if (juego == true || tiroFinal == true) { //Si el jugador 1 aun no destruyo todas las naves
                        flag = true;
                        System.out.println("");
                        System.out.println("");
                        System.out.println("");
                        System.out.println("");
                        System.out.println("----------------------------------------------------");
                        System.out.println("Turno nº: " + ++turnos);
                        System.out.println("Turno del jugador: " + b1.getJugador2().getNick());
                        //System.out.println("Cantidad de aciertos " + j2.getTiros());
                        System.out.println("----------------------------------------------------");
                        if (tiroFinal == true) {
                            System.out.println("");
                            System.out.println("EL JUGADOR " + j1.getNick() + " HA DESTRUIDO TODAS LAS NAVES DE " + j2.getNick());
                            System.out.println("ESTA SERA LA ULTIMA JUGADA DEL JUGADOR " + j2.getNick());
                            System.out.println("");
                        }
                    }

                    //TURNO DEL JUGADOR 2
                    while (flag) {
                        res = "";
                        ataqueDisponible = false;
                        selector = verificadorDeNumero();
                        if (selector == 1) {
                            if (poderDisponible(j2)) {
                                while (!res.equals("1") && !res.equals("2")) {
                                    System.out.println("1: Utilizar poder\n"
                                            + "2: Ataque");
                                    res = r.nextLine();
                                    if (res.equals("1")) {
                                        ataqueDisponible = true;
                                        flag = mostrarPoderes(j2, j1);
                                        if (!quedanBarcosASalvo(j1)) {
                                            System.out.println("¡Has destruido todas las naves enemigas!");
                                            flag = false;
                                            juego = false;
                                        }
                                    } else if (!res.equals("1") && !res.equals("2")) {
                                        System.out.println("");
                                        System.out.println("Ingrese un numero valido");
                                        System.out.println("");
                                    }
                                }

                            }
                            while (!ataqueDisponible) {
                                System.out.println("");
                                System.out.println("Dar las posicion a atacar");
                                System.out.println("");

                                fila = b1.getJugador2().darFila(filas, b1.getTamaño());
                                columna = b1.getJugador2().darColumna(b1.getTamaño());
                                //verifica si la posicion elegida fue atacada
                                ataqueDisponible = esAtaqueValido(b1.getJugador1(), fila, columna, filas);
                                //si la posicion esta permitida, realizar el ataque
                                if (ataqueDisponible) {
                                    valor = (b1.ataque(j1, fila, columna, filas));
                                    if (valor) {
                                        j2.incrementarTiros();
                                        //verifica si el tiro dado destruyo toda la flota enemiga
                                        if (!quedanBarcosASalvo(j1)) {
                                            System.out.println("¡Has destruido todas las naves enemigas!");
                                            flag = false;
                                            juego = false;
                                        }
                                    } else {
                                        flag = false;
                                    }
                                } else {
                                    System.out.println("No es posible atacar esa posicion porque ya ha sido atacada, elija otra");
                                }
                            }
                        } else if (selector == 3) {
                            b1.getJugador2().getTablero().barcosASalvo();
                        } else if (selector == 4) {
                            b1.getJugador2().getTablero().barcosHundidos();
                        } else {
                            b1.getJugador2().getTablero().verTableroDePiezasEnJuego();
                            System.out.println("");
                            b1.getJugador2().getTablero().verTableroDeTiros(b1.getJugador1().getTablero());
                            System.out.println("");

                        }
                    }
                    muchoEspacio();
                    if (turnos >= b1.getTurnos() && juego == true) {

                        System.out.println("----------------------------------------------------");
                        System.out.println("               SE ACABARON LOS TURNOS");
                        System.out.println("----------------------------------------------------");
                        juego = false;
                    }

                }
                if (j1.getTiros() > j2.getTiros()) {
                    System.out.println("");
                    System.out.println("Gano " + j1.getNick());
                    System.out.println("");
                } else if (j2.getTiros() > j1.getTiros()) {
                    System.out.println("");
                    System.out.println("Gano " + j2.getNick());
                    System.out.println("");
                } else {
                    System.out.println("");
                    System.out.println("Empate");
                    System.out.println("");
                }

            } else if (opc.equals("2")) { //OPCION PARA PODER CONFIGURAR LOS PARAMETROS
                System.out.println("");
                System.out.println("");
                b1.settearParametros();
                System.out.println("");
                System.out.println("");
            } else if (opc.equals("3")) { //INFORMACION QUE SE REQUIERA PARA ENTENDER EL JUEGO
                System.out.println("");
                System.out.println("");
                System.out.println("El juego consta de dos jugadores en donde cada jugador tiene dos mapas, uno donde están las naves propias y \n"
                        + "donde se marcan los tiros que el enemigo realiza sobre las naves propias y el otro tablero donde se marcan los tiros propios, ya \n"
                        + "sean al agua/isla o naves enemigas.\n"
                        + "Las fichas que se pueden colocar en el tablero son barcos que tienen distintos atributos como vida o poderes. Los \n"
                        + "poderes de cada barco van a poder ser ocupados solo 1 vez. Los distintos barcos son: \n"
                        + "\n"
                        + "* Portaviones, que ocupa 5 casillas del mapa, tiene el poder de tirar tres tiros consecutivos de forma vertical o \n"
                        + "horizontal con respecto a una posición dada.  \n"
                        + "\n"
                        + "* Buque, que ocupa 4 casillas del mapa, tiene el poder de tirar dos tiros consecutivos de forma vertical o horizontal \n"
                        + "con respecto a una posición dada. \n"
                        + "\n"
                        + "* Submarino, que ocupa 3 casillas del mapa, tiene el poder de un sonar, revela la información del mapa enemigo que hay \n"
                        + "circundante a una posición de 2 x 2 con respecto a una posición dada.  \n"
                        + "\n"
                        + "* Crucero, que ocupa 2 casillas y no tiene poder. \n"
                        + "\n"
                        + "* Lancha, que ocupa 1 casilla y no tiene poder. \n"
                        + "\n"
                        + "Cada tablero según su tamaño tendrá asociado una cantidad determinada de turnos y tres opciones predeterminadas de \n"
                        + "disposición de barcos, el tablero de 5x5 tiene 10 turnos, el tablero de 10x10 tiene 16 turnos y el tablero 15x15 tiene \n"
                        + "20 turnos. Los turnos no son modificables, son fijos al tamaño del tablero. \n"
                        + "Si se inicia el juego sin realizar ninguna configuración, el juego empezará por predeterminado con un tamaño 10x10 con una \n"
                        + "disposicion de barcos de 1 Portaviones, 1 Buque, 1 Submarino, 1 Crucero y 1 Lancha.\n"
                        + "\n"
                        + "Al momento de realizar la colocacion de barcos, las opciones serán de poder colocarlo hacia abajo o hacia la derecha\n"
                        + "de la posición seleccionada y no se podrá colocar un barco pegado a otro ya colocado\n"
                        + "\n"
                        + "Al momento de atacar, en caso de haber un barco que tenga poder especial y no haya sido ocupado, el usuario podrá \n"
                        + "seleccionar entre usar ese poder o realizar un ataque normal, en caso de que se hayan ocupado todos los barcos con \n"
                        + "poderes se procederá a realizar un ataque normal. El ataque normal consta de un solo disparo en una posición \n"
                        + "seleccionada. \n"
                        + "Si el jugador acertó el disparo, este va a poder volver a disparar, en caso contrario, el turno será para el oponente. \n"
                        + "\n"
                        + "El juego puede terminar de tres maneras: \n"
                        + "\n"
                        + "* El jugador, que hunda todos los barcos del oponente antes del último turno, gana \n"
                        + "\n"
                        + "* Si el jugador que inició, en su último tiro, hunde el último barco de su adversario, este va a tener la posibilidad \n"
                        + "de un último turno para poder empatar. \n"
                        + "\n"
                        + "* Al acabarse los turnos, el jugador que más aciertos a barcos enemigos tenga, gana; en caso de que tengan la misma \n"
                        + "cantidad hay un empate entre los jugadores. ");
                System.out.println("");
                System.out.println("");
                System.out.println("LEYENDA DEL MAPA PROPIO DE NAVES:\n"
                        + "[ ] representa Agua en el mapa\n"
                        + "[I] representa parte de una Isla en el mapa\n"
                        + "[N] representa una parte de una Nave Propia\n"
                        + "[X] representa un tiro que cayó sobre una Nave Propia\n"
                        + "[-] representa que hay un barco al lado de esta casilla\n");
                System.out.println("");
                System.out.println("LEYENDA DEL MAPA DE TIROS:\n"
                        + "[ ] representa una zona desconocida del mapa enemigo\n"
                        + "[I] representa parte de una Isla en el mapa\n"
                        + "[N] representa una parte de una Nave Enemiga\n"
                        + "[O] representa un tiro que cayó en Agua o Isla\n"
                        + "[X] representa un tiro que cayó sobre una Nave Enemiga\n");
                System.out.println("");
                System.out.println("");
                System.out.println("");
            } else if (!opc.equals("4")) {
                System.out.println("");
                System.out.println("Ingrese un número válido");
                System.out.println("");
            }
        }

    }

    /**
     * Muestra un menú interactivo para que el jugador elija una acción durante
     * su turno.
     *
     * El jugador puede elegir entre atacar, ver los mapas, ver barcos a salvo o
     * ver barcos hundidos. Este método gestiona la entrada del usuario,
     * asegurando que se ingrese un número válido para cada opción.
     *
     * @return Un entero que representa la opción seleccionada por el jugador
     * (1: Atacar, 2: Ver Mapas, 3: Ver Barcos a Salvo, 4: Ver Barcos Hundidos).
     */
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
            try {
                res = r.nextInt();
                r.nextLine();
                if (res == 1 || res == 2 || res == 3 || res == 4) {
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println("Dar un número válido");
                r.nextLine();
            } finally {
                r.reset();
            }
        }
        return res;
    }

    /**
     * Verifica si la posición elegida para atacar ha sido atacada
     * anteriormente.
     *
     * Este método valida si la casilla especificada en el tablero del jugador
     * ha sido atacada previamente.
     *
     * @param jugador El jugador cuyo tablero se está verificando.
     * @param i La fila del tablero en la que se encuentra la casilla a
     * verificar.
     * @param j La columna del tablero en la que se encuentra la casilla a
     * verificar.
     * @param filas Un array de cadenas que representa las filas del tablero.
     * @return {@code true} si la casilla no ha sido atacada anteriormente,
     * {@code false} en caso contrario.
     */
    //FUNCION QUE VERIFICA SI LA POSICION ELEGIDA PARA ATACAR FUE ATACADA ANTERIORMENTE
    private static boolean esAtaqueValido(Jugador jugador, String i, int j, String[] filas) {// valida si la casilla ha sido atacada
        return (jugador.getTablero().getMapa()[Arrays.asList(filas).indexOf(i.toUpperCase())][j].isHit() == false);
    }

    /**
     * Verifica si queda algún barco a salvo en el tablero del jugador.
     *
     * Este método comprueba si hay algún barco en el tablero del jugador que
     * aún tenga vida, es decir, que no haya sido hundido.
     *
     * @param jugador El jugador cuyo tablero se está verificando.
     * @return true si al menos un barco en el tablero tiene vida,
     *  false en caso contrario.
     */
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

    /**
     * Verifica si existe algún poder disponible para usar en el tablero del
     * jugador.
     *
     * Este método comprueba si hay algún barco en el tablero del jugador que
     * tenga un poder especial habilitado.
     *
     * @param jugador El jugador cuyo tablero se está verificando.
     * @return true si al menos un barco en el tablero tiene un poder
     * especial habilitado, false en caso contrario.
     */
    //FUNCION QUE VERIFICA SI EXISTE ALGUN PODER PARA USAR
    private static boolean poderDisponible(Jugador jugador) {
        boolean hayBarcosASalvo = false;

        // Obtener la lista de naves del tablero del jugadorEnemigo
        List<Nave> naves = jugador.getTablero().getNaves();

        // Iterar sobre la lista de naves
        for (Nave nave : naves) {
            // Comprobar si la nave tiene vida (es decir, está a salvo)
            if (nave.isPoder() == true) {
                hayBarcosASalvo = true;
                break; // No es necesario continuar si ya encontramos un barco a salvo
            }
        }

        return hayBarcosASalvo;
    }

    /**
     * Ejecuta los poderes disponibles del jugador y permite al jugador
     * seleccionar cuál usar.
     *
     * Este método muestra las opciones de poderes disponibles para el jugador y
     * ejecuta el poder seleccionado. El jugador puede elegir entre varios tipos
     * de poderes dependiendo de las naves que tenga disponibles.
     *
     * @param jugador1 El jugador que está realizando el ataque.
     * @param jugadorEnemigo El jugador al que se le va a atacar.
     * @return true si el ataque con poder fue exitoso,  false en
     * caso contrario.
     */
    //FUNCION QUE SE ENCARGA DE EJECUTAR LOS PODERES
    public static boolean mostrarPoderes(Jugador jugador1, Jugador jugadorEnemigo) {
        boolean acertoAtaque = false;
        Iterator<Nave> it = jugador1.getTablero().getNaves().iterator();
        int portaaviones = 0;
        int buque = 0;
        int submarino = 0;
        Nave portaavionTemp = null;
        Nave buqueTemp = null;
        Nave submarinoTemp = null;
        Scanner r = new Scanner(System.in);
        String res;

        while (it.hasNext()) {
            Nave nave = it.next();
            if (nave.getClass().getSimpleName().equals("Portaaviones") && nave.isPoder() == true) {
                portaaviones++;
                portaavionTemp = nave;
            } else if (nave.getClass().getSimpleName().equals("Buque") && nave.isPoder() == true) {
                buque++;
                buqueTemp = nave;
            } else if (nave.getClass().getSimpleName().equals("Submarino") && nave.isPoder() == true) {
                submarino++;
                submarinoTemp = nave;
            }

        }
        System.out.println("");
        if (portaaviones > 0) {
            System.out.println("Tienes " + portaaviones + " poderes de portaaviones disponibles (Opcion P)");
        }
        if (buque > 0) {
            System.out.println("Tienes " + buque + " poderes de buques disponibles (Opcion B)");
        }
        if (submarino > 0) {
            System.out.println("Tienes " + submarino + " poderes de submarinos disponibles (Opcion S)");
        }

        boolean flag = true;
        while (flag) {
            System.out.println("ELIJA UNA OPCION");
            res = r.nextLine();
            res = res.toUpperCase();
            if (!res.equals("P") && !res.equals("B") && !res.equals("S")) {
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
                System.out.println("");
            }
            if (res.equals("P")) {
                if (portaaviones > 0) {
                    //acertoAtaque = portaavionTemp.usarPoder(jugador1, jugadorEnemigo);
                    acertoAtaque=  activarPoder(portaavionTemp, jugador1, jugadorEnemigo);
                    flag = false;
                } else {
                    System.out.println("No tiene portaaviones con poderes habilitados");
                }

            }
            if (res.equals("B")) {
                if (buque > 0) {
                    //acertoAtaque = buqueTemp.usarPoder(jugador1, jugadorEnemigo);
                    acertoAtaque=  activarPoder(buqueTemp, jugador1, jugadorEnemigo);
                    flag = false;
                } else {
                    System.out.println("No tiene buques con poderes habilitados");
                }

            }
            if (res.equals("S")) {
                if (submarino > 0) {
                    //acertoAtaque = submarinoTemp.usarPoder(jugador1, jugadorEnemigo);
                    acertoAtaque=  activarPoder(submarinoTemp, jugador1, jugadorEnemigo);
                    flag = false;
                } else {
                    System.out.println("No tiene submarinos con poderes habilitados");
                }
            }
            System.out.println("");
        }
        return acertoAtaque;
    }
    
    /**
     * Metodo polimorfico que activa el poder del objeto barco que llegue en la variable nave
     * @param nave un objeto polimorfico de nave
     * @param jugador1 jugador que utilizo el poder
     * @param jugadorEnemigo jugador sobre el que se realiza el poder
     * @return True si el poder impacto sobre una nave, False en caso contrario
     */
    public static boolean activarPoder(Nave nave, Jugador jugador1, Jugador jugadorEnemigo){
        return nave.usarPoder(jugador1,jugadorEnemigo);
    }
    
    /**
     * Genera muchos saltos de linea
     */
    public static void muchoEspacio(){
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        
    }
}
