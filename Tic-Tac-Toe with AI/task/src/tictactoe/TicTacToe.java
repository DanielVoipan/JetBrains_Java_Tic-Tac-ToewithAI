package tictactoe;

class TicTacToe {

    // number of lines for TicTacToe field
    private final int numberLines = 3;
    // number of columns for TicTacToe field
    private final int numberColumns = 3;

    private char[][] field;

    // constructor without parameters for TicTacToe Game
    public TicTacToe() {
        createTicTacToe();
    }

    // create empty TicTacToe field
    private void createTicTacToe() {
        field = new char[numberLines][numberColumns];
        for (int i = 0; i < numberLines; i++) {
            for (int j = 0; j < numberColumns; j++) {
                field[i][j] = '_';
            }
        }
    }

    // getter for TicTacToe field
    public char[][] getTicTacToe() {
        return field;
    }

    // draw sign
    public void drawSign(char c, int line, int column) {
        field[line][column] = c;
    }

    // show TicTacToe
    public void showTicTacToe () {
        System.out.println("---------");
        for (int i = 0; i < numberLines; i++) {
            for (int j = 0; j < numberColumns; j++) {
                if (j == 0) {
                    System.out.print("| " + field[i][j]);
                } else if (j == numberColumns - 1) {
                    System.out.print(field[i][j] + " |");
                } else {
                    System.out.print(" " + field[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("---------");
    }

    // create human player
    public Player createPlayer(String type, char c) {
        return new Player(type, c);
    }

    // create AI player
    public AI createPlayer(String type, char c, String level) {
        return new AI(type, c, level);
    }
}