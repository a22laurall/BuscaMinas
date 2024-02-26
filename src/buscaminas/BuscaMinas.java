package buscaminas;

import java.util.Random;

public class BuscaMinas {
    private int[][] tableroMinas = new int[10][10];
    private char[][] tableroVisible = new char[10][10];
    int estadoCelda;
    static int celdasDescubiertas;

    public char[][] getTableroVisible() {
        return tableroVisible;
    }

    public int[][] getTableroMinas() {
        return tableroMinas;
    }

    public void setEstadoCelda(int fila, int col, int estadoCelda) {
        tableroVisible[fila][col] = String.valueOf(estadoCelda).charAt(0);
    }

    public int getEstadoCelda(int fila, int col) {
        return tableroVisible[fila][col];
    }

    public BuscaMinas() {
        for (int i = 0; i < tableroMinas.length; i++) {
            for (int j = 0; j < tableroMinas[i].length; j++) {
                tableroMinas[i][j] = 0;
                tableroVisible[i][j] = 'T';
            }
        }

    }

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

    public void establecerNumMinas(int fila, int col) {
        for (int i = Math.max(0, fila - 1); i < Math.min(fila + 2, tableroMinas.length - 1); i++) {
            for (int j = Math.max(0, col - 1); j < Math.min(col + 2, tableroMinas.length - 1); j++) {
                if (tableroMinas[i][j] != 9) {
                    tableroMinas[i][j]++;
                }
            }
        }
    }

    public Boolean destaparCelda(int fila, int col) {

        if (tableroMinas[fila][col] == 9) {
            return false;
        } else {
            destaparCeldasAdyacentes(fila, col);
        }

        return true;
    }

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

    public Boolean victoria(){
        if (celdasDescubiertas == 90) {
            return true;
        }
        return false;
    }
   

}
