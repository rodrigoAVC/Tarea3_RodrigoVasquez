package tarea3_rodrigovasquez;

import java.util.Scanner;

public class Tarea3_RodrigoVasquez {

    public static void main(String[] args) {
        Scanner midna = new Scanner(System.in);
        boolean correctFormat = true;
        String[][] tablero = new String[10][10];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if ((i == 1 && j == 1) || (i == 1 && j == 5) || (i == 2 && j == 1) || (i == 2 && j == 5) || (i == 3 && j == 1)
                        || (i == 3 && j == 5) || (i == 4 && j == 1) || (i == 4 && j == 5) || (i == 5 && j == 1) || (i == 5 && j == 5)
                        || (i == 5 && j == 2) || (i == 5 && j == 3) || (i == 5 && j == 4)) {
                    tablero[i][j] = "[X]";
                } else if ((i == 4 && j == 2) || (i == 4 && j == 3) || (i == 4 && j == 4)) {
                    tablero[i][j] = "[C]";
                } else if (i == 7 && j == 7) {
                    tablero[i][j] = "[R]";
                } else if (i == 8 && j == 5) {
                    tablero[i][j] = "[O]";
                } else {
                    tablero[i][j] = "[ ]";
                }
            }
        }
        System.out.println("----------- < B I E N V E N I D O S > -----------");
        System.out.println("Instrucciones: Ingrese una cadena de caracteres\nque representaran las acciones que hara el robot");
        System.out.println("U: Arriba          D: Abajo\nL: Izquierda       R: Derecha\nG: Recoger Caja    S: Poner Caja");
        System.out.println("-------------------------------------------------");
        boolean fin = false;
        int filas = 7;
        int column = 7;
        boolean caja1 = false;
        boolean caja2 = false;
        boolean caja3 = false;
        do {
            imprimirMatriz(tablero);
            System.out.println("-------------------------------------------------");
            System.out.print("Ingrese la cadena de comandos: ");
            String entrada = midna.next();
            String[] tokens = entrada.replace(" ", "").split(",");
            Scanner tokens2 = new Scanner(entrada.replace(" ", ""));
            tokens2.useDelimiter(",");
            int cont = 0;
            for (int i = 0; i < tokens.length; i++) {
                char check = tokens[i].charAt(0);
                int charNum = (int) check;
                if (tokens[i].length() >= 2) {
                    correctFormat = false;
                }
                if ((charNum == 85 || charNum == 117) || (charNum == 68 || charNum == 100) || (charNum == 76 || charNum == 108)
                        || (charNum == 82 || charNum == 114) || (charNum == 71 || charNum == 103) || (charNum == 83 || charNum == 115)) {
                } else {
                    correctFormat = false;
                    break;
                }
            }
            System.out.println(tokens[0]);
            if (correctFormat) {
                movimiento(tokens2, tokens, cont, tablero, filas, column, caja1, caja2, caja3);
            } else {
                System.out.println("Comando(s) Invalido(s)");
            }
        } while (fin == false);
    }

    public static String movimiento(Scanner tokens2, String[] tokens, int cont, String[][] tablero, int filas, int column, boolean caja1, boolean caja2, boolean caja3) {
        if (tokens2.hasNext()) {
            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero[0].length; j++) {
                    if (tablero[i][j].contains("R")) {
                        filas = i;
                        column = j;
                    }
                }
            }
            switch (tokens[cont].charAt(0)) {
                case 'u':
                case 'U':
                    if ((filas == 0) || (filas == 6 && column == 1) || (filas == 6 && column == 2) || (filas == 6
                            && column == 3) || (filas == 6 && column == 4) || (filas == 6 && column == 5)) {
                        System.out.println("No se puede mover para arriba");
                    } else {
                        tablero[filas][column] = "[ ]";
                        tablero[filas - 1][column] = "[R]";
                    }
                    break;
                case 'd':
                case 'D':
                    if ((filas == 9) || (filas == 0 && column == 1) || (filas == 0 && column == 5)) {
                        System.out.println("No se puede mover para abajo");
                    } else if ((filas == 3 && column == 2 && caja1 == false) || (filas == 3 && column == 3 && caja2) || (filas == 3 && column == 4 && caja3) || 
                            (caja1 == true && filas == 4 && column == 2) || (caja2 == true && filas == 4 && column == 3) || (caja3 == true && filas == 4 && column == 4)) {
                        System.out.println("No se puede mover para abajo");
                    } else {
                        tablero[filas][column] = "[ ]";
                        tablero[filas + 1][column] = "[R]";
                    }
                    break;
                case 'l':
                case 'L':
                    if ((column == 0) || (filas == 1 && column == 6) || (filas == 2 && column == 6) || (filas == 3 && column == 6)|| (filas == 4 && column == 6) 
                            || (filas == 5 && column == 6)) {
                        System.out.println("No se puede mover a la izquierda");
                    } else if ((filas == 3 && column == 2 && caja1 == false) || (filas == 3 && column == 3 && caja2) || (filas == 3 && column == 4 && caja3)) {
                        System.out.println("No se puede mover para abajo");
                    } else {
                        tablero[filas][column] = "[ ]";
                        tablero[filas + 1][column] = "[R]";
                    }
                    tablero[filas][column] = "[ ]";
                    tablero[filas][column - 1] = "[R]";
                    break;
                case 'r':
                case 'R':
                    tablero[filas][column] = "[ ]";
                    tablero[filas][column + 1] = "[R]";
                    break;
                case 'g':
                case 'G':
                    tablero[filas][column] = "[ ]";
                    tablero[filas][column] = "[R]";
                    break;
                case 's':
                case 'S':
                    tablero[filas][column] = "[ ]";
                    tablero[filas][column] = "[R]";
                    break;

            }
        } else {

        }
    }

    public static void imprimirMatriz(String[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                System.out.print(tablero[i][j]);
            }
            System.out.println("");
        }
    }
}
