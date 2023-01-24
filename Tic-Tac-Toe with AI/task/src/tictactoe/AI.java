package tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class AI extends Player {

    private String level;

    enum levels {
        EASY, MEDIUM, HARD
    }

    public AI(String type, char sign, String level) {
        super(type, sign);
        this.level = level;
    }

    // get level
    public String getLevel() {
        return level;
    }

    // AI easy level
    public String EasyLevelAI(TicTacToe f) {
        char[][] field = f.getTicTacToe();
        List<String> positions = new ArrayList<>();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[i][j] == '_') {
                    positions.add(i + "" + j);
                }
            }
        }
        Random nr = new Random();
        return positions.get(nr.nextInt(positions.size()));
    }

    // AI medium level
    public String MediumLevelAI(TicTacToe f) {
        char[][] field = f.getTicTacToe();
        char opponentSign = sign == 'X' ? 'O' : 'X';

        // first logic to apply
        // check if there are win positions, if yes,
        // go get them
        List<String> winPositions = new ArrayList<>();
        List<String> blockPositions = new ArrayList<>();

        for (int i = 0; i < field.length; i++) {
            int found = 0;
            String savedPos = null;
            for (int j = 0; j < field.length; j++) {
                if (field[i][j] == sign) {
                    found++;
                } else if (field[i][j] == '_') {
                    savedPos = i + "" + j;
                }
            }
            if (found == 2 && !Objects.equals(savedPos, null)) {
                    winPositions.add(savedPos.charAt(0) + "" + savedPos.charAt(1));
            }
        }
        if (winPositions.size() > 1) {
            Random nr = new Random();
            return winPositions.get(nr.nextInt(winPositions.size()));
        } else if (winPositions.size() == 1) {
            return winPositions.get(0);
        }

        // second logic to apply
        // check if the AI can block move, if yes,
        // block the mothafucka

        // check to block by line
        for (int i = 0; i < field.length; i++) {
            int found = 0;
            String savedPos = null;
            for (int j = 0; j < field.length; j++) {
                if (field[i][j] == opponentSign) {
                    found++;
                } else if (field[i][j] == '_') {
                    savedPos = i + "" + j;
                }
            }
            if (found == 2 && !Objects.equals(savedPos, null)) {
                blockPositions.add(savedPos.charAt(0) + "" + savedPos.charAt(1));
            }
        }

        // parse the columns
        int k = 0;
        String savedPos = null;
        while(k < field.length) {
            int found = 0;
            for (int j = 0; j < field.length; j++) {
                if (field[k][j] == opponentSign) {
                    found++;
                } else if (field[k][j] == '_') {
                    savedPos = k + "" + j;
                }
            }
            if (found == 2 && !Objects.equals(savedPos, null)) {
                blockPositions.add(savedPos.charAt(0) + "" + savedPos.charAt(1));
                break;
            }
            k++;
        }
        List<String[]> diagonals = new ArrayList<>();

        diagonals.add(new String[]{"00", "11", "22"});
        diagonals.add(new String[]{"02", "11", "20"});

        for (String[] s : diagonals) {
            int found = 0;
            int l = 0;
            int c = 0;
            savedPos = null;
            for (String p : s) {
                l = Integer.parseInt(String.valueOf(p.charAt(0)));
                c = Integer.parseInt(String.valueOf(p.charAt(1)));
                if (field[l][c] == opponentSign) {
                    found++;
                } else if (field[l][c] == '_') {
                    savedPos = l + "" + c;
                }
            }
            if (found == 2 && !Objects.equals(savedPos, null)) {
                blockPositions.add(savedPos.charAt(0) + "" + savedPos.charAt(1));
            }
        }

        if (blockPositions.size() > 1) {
            Random nr = new Random();
            return blockPositions.get(nr.nextInt(blockPositions.size()));
        } else if (blockPositions.size() == 1) {
            return blockPositions.get(0);
        } else {
            return EasyLevelAI(f);
        }
    }
}
