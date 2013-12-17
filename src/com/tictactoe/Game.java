package com.tictactoe;

import java.util.Scanner;

public class Game {

    private final int WIDTH = 3;
    private final int HEIGHT = 3;
    private final char X = 'X';
    private final char O = 'O';
    private final int GAMEMOVES = WIDTH * HEIGHT;
    private char[][] field;
    private char symbol = 'O';
    private int counter = 0;


    Game() {
        System.out.println("Игра началась. Вы играете крестиками.");
        this.field = new char[WIDTH][HEIGHT];
        for (int i = 0; i <= HEIGHT - 1; i++) {
            for (int j = 0; j <= WIDTH - 1; j++) {
                field[i][j] = ' ';
            }
        }
    }

    public void startGame() {
        getField();

        do {

            switchSymbol();
            setField();
            getField();

            if (this.counter > 3) {
                char someSymbol = checkVictory(this.symbol);

                if (someSymbol == 'X') {
                    System.out.println("Выиграли крестики. Мои поздравления.");
                    break;
                } else if (someSymbol == 'O') {
                    System.out.println("Выиграли нолики. Мои поздравления.");
                    break;
                }
            }

            this.counter++;

            if (this.counter == GAMEMOVES) {
                System.out.println("Ничья");
                break;
            }

        }
        while (this.counter <= GAMEMOVES);

    }


    private void setField() {
        System.out.println("Введите координаты.");
        Scanner sc = new Scanner(System.in);

        boolean inputNow = true;

        do {

            System.out.println("Введите по оси X");
            int x = sc.nextInt();
            System.out.println("Введите по оси Y");
            int y = sc.nextInt();


            if (checkInputValue(x) && checkInputValue(y)) {
                setFieldInner(x, y);
                inputNow = false;
            } else {
                System.out.println("Неправильный ввод чисел, повторите");
            }
        }
        while(inputNow);
    }

    private boolean checkInputValue(int value) {
        if (value >= 1 && value <= 3) {
          return true;
        }

        return false;
    }

    private void getField() {
        for (int i = 0; i <= HEIGHT - 1; i++) {
            for (int j = 0; j <= WIDTH - 1; j++) {
                System.out.print("[" + field[i][j] + "]");
            }
            System.out.println();
        }
    }

    private void setFieldInner(int x, int y) {
        if (field[x - 1][y - 1] == ' ') {
            field[x - 1][y - 1] = this.symbol;
        } else {
            this.counter--;
            System.out.println("Ход невозможен. Сюда уже ходили.");
            if (this.symbol == 'X') {
                this.symbol = O;
            } else if (this.symbol == 'O') {
                this.symbol = X;
            }
        }
    }

    // проверка на выигрыш и отправка собщения какой символ победил
    private char checkVictory(char symbol) {
        if (gorizontalLineCheck(symbol) || verticalLineCheck(symbol) || dLineCheck(symbol)) {
            if (this.symbol == X) {
                return X;
            } else {
                return O;
            }
        }
        return 0;
    }

    private boolean gorizontalLineCheck(char symbol) {

        int counter = 0;

        for (int i = 0; i <= HEIGHT - 1; i++) {
            for (int j = 0; j <= WIDTH - 1; j++) {
                if (field[i][j] == symbol) {
                    counter++;
                }
            }
            if (counter == 3) {
                return true;
            } else {
                counter = 0;
            }

        }
        return false;

    }

    private boolean verticalLineCheck(char symbol) {
        int counter = 0;

        for (int i = 0; i <= HEIGHT - 1; i++) {
            for (int j = 0; j <= WIDTH - 1; j++) {
                if (field[j][i] == symbol) {
                    counter++;
                }
            }
            if (counter == 3) {
                return true;
            } else {
                counter = 0;
            }

        }
        return false;

    }


    private boolean dLineCheck(char symbol) {
        if (field[0][0] == symbol && field[1][1] == symbol && field[2][2] == symbol) {
            return true;
        }
        if (field[2][0] == symbol && field[1][1] == symbol && field[0][2] == symbol) {
            return true;
        }
        return false;
    }


    private void switchSymbol() {
        if (this.symbol == '?' || this.symbol == 'X') {
            this.symbol = O;
        } else {
            this.symbol = X;
        }
    }
}
