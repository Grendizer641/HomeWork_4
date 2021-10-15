package HomeWork_4;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private static final char DOT_EMPTY = '•';
    private static final char DOT_X = 'X';
    private static final char DOT_0 = '0';

    private static char[][] map;
    private static final int SIZE = 3; // размер карты, вариативное поле
    private static final int DOT_TO_WIN = 3; //Колличество символов для победы, вариативное поле

    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    private static int row;
    private static int col;
    private static int x;
    private static int y;

    private static int countRowWin = 0;
    private static int countColumWin = 0;
    private static int countDiagonalLeftTopWin = 0;
    private static int countDiagonalLeftBottomWin = 0;

    public static void main(String[] args) {
        initMap();
        printMap();
        startGame();
    }

    private static void startGame() {
        while (true) {

            humanTurn();
            printMap();
            if (isWin(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isDraw()) {
                System.out.println("Ничья");
                break;
            }


            aiTurn();
            printMap();
            if (isWin(DOT_0)) {
                System.out.println("Победил компьютер");
                break;
            }
            if (isDraw()) {
                System.out.println("Ничья");
                break;
            }
        }
    }


    private static boolean isWin(char symbol) {
        //Check rows
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == symbol) {
                    countRowWin++;
                    if (countRowWin == DOT_TO_WIN) {
                        return true;
                    }
                }
            }
            countRowWin = 0;
        }
        //Check columns
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[j][i] == symbol) {
                    countColumWin++;
                    if (countColumWin == DOT_TO_WIN) {
                        return true;
                    }
                }
            }
            countColumWin = 0;
        }
        //Check diagonals
        for (int i = 0; i < SIZE; i++) {
            for (int j = i; j == i; j++) {
                if (map[i][j] == symbol) {
                    countDiagonalLeftTopWin++;
                    if (countDiagonalLeftTopWin == DOT_TO_WIN) {
                        return true;
                    }
                }
            }
        }
        countDiagonalLeftTopWin = 0;

        for (int i = SIZE - 1; i >= 0; i--) {
            for (int j = SIZE - 1 - i; j == SIZE - 1 - i; j++) {
                if (map[i][j] == symbol) {
                    countDiagonalLeftBottomWin++;
                    if (countDiagonalLeftBottomWin == DOT_TO_WIN) {
                        return true;
                    }
                }
            }
        }
        countDiagonalLeftBottomWin = 0;

        return false;
    }

    public static boolean isDraw() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void aiTurn() {
        if (isAiMoveRandom()) {
            do {
                x = random.nextInt(SIZE); // Вот тут я не совсем понял, почему нельзя использовать существующие
                y = random.nextInt(SIZE); // переменные row и col. Иначе ломается. random.nextInt так работает?
            } while (!(map[x][y] == DOT_EMPTY));

            map[x][y] = DOT_0;
        } else {
            outer: for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (map[i][j] == DOT_EMPTY) {
                        map[i][j] = DOT_X;
                        if (isWin(DOT_X)) {
                            map[i][j] = DOT_0;
                            break outer;
                        } else {
                            map[i][j] = DOT_EMPTY;
                        }
                    }
                }

            }
        }
    }

    private static boolean isAiMoveRandom() { // Проверяем, рандомно ли будет ходить АИ
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    map[i][j] = DOT_X;
                    if (isWin(DOT_X)) {
                        map[i][j] = DOT_EMPTY;
                        return false;
                    } else {
                        map[i][j] = DOT_EMPTY;
                    }
                }
            }
        }
        return true;
    }

    private static void humanTurn() {
        System.out.println("Введите значение координат поля в формате X Y");

        do {
            checkInteger();
            row = scanner.nextInt() - 1;
            checkInteger();
            col = scanner.nextInt() - 1;
        } while (!isCellValid(row, col));
        map[row][col] = DOT_X;
    }

    private static void checkInteger() {
        while (!scanner.hasNextInt()) {
            System.out.println("Введите целочисленные значения");
            scanner.next();
        }
    }

    private static boolean isCellValid(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            System.out.println("Вводимые значения должны быть в пределах от 1 до " + SIZE);
            return false;
        }
        if (map[row][col] == DOT_EMPTY) {
            return true;
        } else {
            System.out.println("Поле уже занято");
            return false;
        }
    }

    private static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    private static void printMap() {
        for (int i = 0; i <= SIZE; i++) { //Номер столбца
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " "); // Номер строки
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
