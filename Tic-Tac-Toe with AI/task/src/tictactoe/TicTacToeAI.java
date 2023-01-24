package tictactoe;

public class TicTacToeAI  {
    int row;
    int col;

    // AI evaluate to get score.
    static int evaluate(char[][] b, char player, char opponent) {

        // Checking for Rows for X or O victory.
        for (char[] chars : b) {
            if (chars[0] == chars[1] && chars[1] == chars[2]) {
                if (chars[0] == player)
                    return +10;
                else if (chars[0] == opponent)
                    return -10;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < b.length; col++) {
            if (b[0][col] == b[1][col] && b[1][col] == b[2][col]) {
                if (b[0][col] == player)
                    return +10;
                else if (b[0][col] == opponent)
                    return -10;
            }
        }

        // Checking for Diagonals for X or O victory.
        if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
            if (b[0][0] == player)
                return +10;
            else if (b[0][0] == opponent)
                return -10;
        }
        if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
            if (b[0][2] == player)
                return +10;
            else if (b[0][2] == opponent)
                return -10;
        }

        // Else if none of them have won then return 0
        return 0;
    }

    // This function returns true if there are moves
    // remaining on the board. It returns false if
    // there are no moves left to play.
    static boolean isMovesLeft(char[][] b) {
        for (char[] chars : b)
            for (int j = 0; j < 3; j++)
                if (chars[j] == '_')
                    return true;
        return false;
    }

    // This is the minimax function. It considers all
    // the possible ways the game can go and returns
    // the value of the board
    static int minimax(char[][] board, int depth, boolean isMax, char player, char opponent) {
        int score = evaluate(board, player, opponent);

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10)
            return score;

        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10)
            return score;

        // If there are no more moves and
        // no winner then it is a tie
        if (!isMovesLeft(board))
            return 0;

        // If this maximizer's move
        if (isMax) {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == '_') {
                        // Make the move
                        board[i][j] = player;

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(board,
                                depth + 1, !isMax, player, opponent));

                        // Undo the move
                        board[i][j] = '_';
                    }
                }
            }
            return best;
        } else {
            // If this minimizer's move
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == '_') {
                        // Make the move
                        board[i][j] = opponent;

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best, minimax(board,
                                depth + 1, !isMax, player, opponent));

                        // Undo the move
                        board[i][j] = '_';
                    }
                }
            }
            return best;
        }
    }

    // This will return the best possible
    // move for the player
    static TicTacToeAI findBestMove(char[][] board, char player, char opponent) {
        int bestVal = -1000;
        TicTacToeAI bestMove = new TicTacToeAI();
        bestMove.row = -1;
        bestMove.col = -1;

        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if cell is empty
                if (board[i][j] == '_') {
                    // Make the move
                    board[i][j] = player;

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(board, 0, false, player, opponent);

                    // Undo the move
                    board[i][j] = '_';

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }
}