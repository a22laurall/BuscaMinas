package buscaminas;

import java.util.Random;

/*
 * Esta clase representa el juego del buscaminas.
 * Un jugador debe encotrar las minas en un tablero 10x10.
 */
public class BuscaMinas {
    private int[][] tableroMinas = new int[10][10];
    private char[][] tableroVisible = new char[10][10];
    static int celdasDescubiertas;

    /**
     * Obtiene el tablero visible actual para el jugador.
     * 
     * @return El tablero visible.
     */
    public char[][] getTableroVisible() {
        return tableroVisible;
    }

    /**
     * Obtiene el tablero donde se almacena la posición de las minas.
     * @return El tablero de minas.
     */
    public int[][] getTableroMinas() {
        return tableroMinas;
    }

    /**
     * Constructor de la clase BuscaMinas. 
     * Inicializa el tablero de minas y el tablero visible.
     */
    public BuscaMinas() {
        for (int i = 0; i < tableroMinas.length; i++) {
            for (int j = 0; j < tableroMinas[i].length; j++) {
                tableroMinas[i][j] = 0;
                tableroVisible[i][j] = 'T';
            }
        }

    }

    /**
     * Asigna las minas a una posición aleatoria válida del tablero. 
     *  
     */
    public void generarMinas() {
        Random rndm = new Random();
        for (int i = 0; i <= 9; i++) {
            int fila = rndm.nextInt(9);
            int col = rndm.nextInt(9);
            if (tableroMinas[fila][col] != 9) {
                tableroMinas[fila][col] = 9;
                establecerNumMinas(fila, col);
            }
        }
    }

    /**
     * Establece el número de minas adyacentes en las casillas vecinas a la posición de la mina dada.
     * 
     * @param fila La fila de la posición de la mina en el tablero.
     * @param col La columna de la posición de la mina en el tablero.
     */
    public void establecerNumMinas(int fila, int col) {
        for (int i = Math.max(0, fila - 1); i < Math.min(fila + 2, tableroMinas.length ); i++) {
            for (int j = Math.max(0, col - 1); j < Math.min(col + 2, tableroMinas.length); j++) {
                if (tableroMinas[i][j] != 9) {
                    tableroMinas[i][j]++;
                }
            }
        }
    }

    /**
     * Destapa una celda del tablero.
     * Si la celda contiene una mina, el juego termina.
     * 
     * @param fila La fila de la celda a destapar.
     * @param col La columna de la celda a destapar.
     * @return Verdadero si la celda no contiene una mina, falso si contiene una mina.
     */
    public Boolean destaparCelda(int fila, int col) {

        if (tableroMinas[fila][col] == 9) {
            return false;
        } else {
            destaparCeldasAdyacentes(fila, col);
        }

        return true;
    }

    /**
     * Destapa las celdas adyacentes a la posición dada.
     * 
     * @param fila La fila de la posición.
     * @param col La columna de la posición.
     * @return Verdadero si se destapan celdas adyacentes, falso si no.
     */
    public boolean destaparCeldasAdyacentes(int fila, int col) {
        if (fila < 0 || fila > tableroMinas.length-1 || col < 0 || col > tableroMinas.length-1){
            return false;
        }

        if (tableroMinas[fila][col] == 9) {
            return false;
        }

        if (tableroVisible[fila][col] != 'T') {
            return false;
        }



        tableroVisible[fila][col] = String.valueOf(tableroMinas[fila][col]).charAt(0);
        celdasDescubiertas++;

        

        if (tableroMinas[fila][col] == 0) {
            for (int i = Math.max(fila - 1, 0); i < Math.min(fila + 2, tableroVisible.length); i++) {
                for (int j = Math.max(col - 1, 0); j < Math.min(col + 2, tableroVisible[i].length); j++) {
                        destaparCeldasAdyacentes(i, j);
                }
            }
        }
        

        return true;
    }

    /**
     * Comprueba si se han descubierto todas las celdas que no contienen minas.
     * 
     * @return Verdadero si se han descubierto todas las celdas sin minas, falso si no.
     */
    public Boolean victoria(){
        if (celdasDescubiertas == 90) {
            return true;
        }
        return false;
    }
   

}
