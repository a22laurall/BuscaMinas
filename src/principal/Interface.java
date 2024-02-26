package principal;

import buscaminas.BuscaMinas;
import java.util.Scanner;

public class Interface {
    BuscaMinas bm = new BuscaMinas();
    Scanner sc = new Scanner(System.in);

    Interface() {
        bm.generarMinas();

        mostrarTableroMinas(bm.getTableroMinas());
        mostrarTableroVisible(bm.getTableroVisible());

        int fila = elegirFilaCelda();
        int col = elegirColCelda();

        while (bm.destaparCelda(fila, col)) {
            mostrarTableroVisible(bm.getTableroVisible());
            fila = elegirFilaCelda();
            col = elegirColCelda();
            if (bm.victoria()) {
                System.out.println("Has ganado");
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Interface();
    }

    public void mostrarTableroVisible(char[][] tablero) {
        System.out.println("----tablero visible----");
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(" " + tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void mostrarTableroMinas(int[][] tablero) {
        System.out.println("----tablero incial con minas y valores----");
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(" " + tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int elegirFilaCelda() {
        System.out.println("Elige la mina que quieres descubrir");
        System.out.print("Fila: ");
        int fila = sc.nextInt();
        return fila;
    }

    public int elegirColCelda() {
        System.out.print("Columna: ");
        int col = sc.nextInt();
        return col;
    }


}
