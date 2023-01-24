package tictactoe;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        boolean start = false;
        Scanner scanner = new Scanner(System.in);
        int[] pos = new int[2];
        Player human;
        AI playerAI;
        TicTacToeAI bestMove;
        List<Player> lst = new ArrayList<>();
        int count = 0;
        char sign = 0;
        boolean exit = false;

        while (true) {
            if (exit) {
                break;
            }
            if (!start) {
                String startCmd = scanner.nextLine();
                String[] splitStr = startCmd.split(" ");
                if (splitStr[0].equalsIgnoreCase("exit")) {
                    exit = true;
                    continue;
                }
                if (splitStr.length < 3) {
                    System.out.println("Bad parameters!");
                    continue;
                }
                ticTacToe.showTicTacToe();

                int pCount = 0;
                for (int i = 1; i < splitStr.length; i++) {
                    switch (splitStr[i].toUpperCase()) {
                        case "USER" -> {
                            human = ticTacToe.createPlayer(splitStr[i].toUpperCase(), pCount % 2 == 0 ? 'X' : 'O');
                            lst.add(human);
                            pCount++;
                        }
                        case "EASY" -> {
                            playerAI = ticTacToe.createPlayer(splitStr[i].toUpperCase(), pCount % 2 == 0 ? 'X' : 'O', AI.levels.EASY.name());
                            lst.add(playerAI);
                            pCount++;
                        }
                        case "MEDIUM" -> {
                            playerAI = ticTacToe.createPlayer(splitStr[i].toUpperCase(), pCount % 2 == 0 ? 'X' : 'O', AI.levels.MEDIUM.name());
                            lst.add(playerAI);
                            pCount++;
                        }
                        case "HARD" -> {
                            playerAI = ticTacToe.createPlayer(splitStr[i].toUpperCase(), pCount % 2 == 0 ? 'X' : 'O', AI.levels.HARD.name());
                            lst.add(playerAI);
                            pCount++;
                        }
                    }
                }
                start = true;
            } else {
                if (lst.get(count).getType().equals(Player.playerType.USER.name())) {
                    System.out.print("Enter the coordinates: ");
                    String positions = scanner.nextLine();
                    try {
                        checkValidPositions(positions, ticTacToe, pos);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    sign = lst.get(count).getSign();
                } else {
                    AI p = (AI) lst.get(count);
                    String positions = null;
                    System.out.println("Making move level \""+ p.getLevel().toLowerCase() +"\"");
                    switch (p.getLevel()) {
                        case "EASY" -> positions = p.EasyLevelAI(ticTacToe);
                        case "MEDIUM" -> positions = p.MediumLevelAI(ticTacToe);
                        case "HARD" -> {
                            bestMove = TicTacToeAI.findBestMove(ticTacToe.getTicTacToe(), p.getSign(), p.getSign() == 'X' ? 'O' : 'X');
                            positions = bestMove.row + "" + bestMove.col;
                        }
                    }
                    assert positions != null;
                    pos[0] = Integer.parseInt(String.valueOf(positions.charAt(0)));
                    pos[1] = Integer.parseInt(String.valueOf(positions.charAt(1)));
                    sign = p.getSign();
                }
                ticTacToe.drawSign(sign, pos[0], pos[1]);
                ticTacToe.showTicTacToe();
                int num = checkStatusGame(ticTacToe, sign);
                if (num == 1) {
                    System.out.println(sign + " wins");
                    break;
                } else if (num == 0) {
                    System.out.println("Game not finished");
                    break;
                } else if (num == 2) {
                    System.out.println("Draw");
                    break;
                }
                if (count == 1) {
                    count = 0;
                } else {
                    count++;
                }
            }
        }
    }

    // check status of game, draw, not finished, win;
    static int checkStatusGame(TicTacToe obj, char sign) {
        char[][] field = obj.getTicTacToe();
        boolean win = false;
        int noMoreMoves = 0;
        int total = 0;

        // parse the field lines
        for (char[] chars : field) {
            int nrLines = 0;
            int other = 0;
            for (int j = 0; j < field.length; j++) {
                if (chars[j] == sign) {
                    nrLines++;
                } else if (chars[j] != '_') {
                    other++;
                }
                if (chars[j] != '_') {
                    total++;
                }
            }
            if (nrLines == 3) {
                win = true;
            } else if (nrLines > 0 && other > 0) {
                noMoreMoves++;
            }
        }

        // parse the columns
        for (int i = 0; i < 3; i++) {
            int nrLines = 0;
            for (char[] chars : field) {
                if (chars[i] == sign) {
                    nrLines++;
                }
                if (nrLines == 3) {
                    win = true;
                }
            }
        }
        // parse the diagonals
        if (field[0][0] == sign && field[1][1] == sign && field[2][2] == sign) {
            win = true;
        } else if (field[0][2] == sign && field[1][1] == sign && field[2][0] == sign) {
            win = true;
        } else if ((field[0][0] != sign || field[1][1] != sign || field[2][2] != sign) && (field[0][0] != '_' || field[1][1] != '_' || field[2][2] != '_'))  {
            noMoreMoves++;
        }
        if ((field[0][2] != sign || field[1][1] != sign || field[2][0] != sign) && (field[0][2] != '_' || field[1][1] != '_' || field[2][0] != '_'))  {
            noMoreMoves++;
        }
        if (win) {
            return 1;
        } else if (noMoreMoves == 8) {
            return 0;
        } else if (total == 9) {
            return 2;
        } else {
         return 3;
        }
    }

    // check valid positions
    static void checkValidPositions(String str, TicTacToe obj, int[] pos) throws Exception {
        String[] splitString = str.split(" ");
        if (!splitString[0].matches("\\d+") || !splitString[1].matches("\\d+")) {
            throw new Exception("You should enter numbers!");
        }
        int lineN = Integer.parseInt(splitString[0]);
        int columnN = Integer.parseInt(splitString[1]);
        if (lineN > 3 || columnN > 3 || lineN < 1 || columnN < 1) {
            throw new Exception("Coordinates should be from 1 to 3!");
        }
        lineN = Integer.parseInt(splitString[0]) - 1;
        columnN = Integer.parseInt(splitString[1]) - 1;
        char[][] field = obj.getTicTacToe();
        if (field[lineN][columnN] == 'X' || field[lineN][columnN] == 'O') {
            throw new Exception("This cell is occupied! Choose another one!");
        }
        pos[0] = lineN;
        pos[1] = columnN;
    }
}
