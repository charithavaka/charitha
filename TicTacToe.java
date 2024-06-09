import java.util.Scanner;

public class TicTacToe {
    private static final char EMPTY = ' ';
    private static final char X = 'X';
    private static final char O = 'O';

    private static char[][] board = new char[3][3];
    private static boolean playerXTurn = true; // Player X starts first

    public static void main(String[] args) {
        initializeBoard();
        printBoard();

        Scanner scanner = new Scanner(System.in);

        while (!isGameOver()) {
            if (playerXTurn) {
                System.out.println("Player X's turn:");
                int[] move = getPlayerMove(scanner);
                makeMove(X, move[0], move[1]);
            } else {
                System.out.println("Player O's turn:");
                int[] move = getAIMove();
                makeMove(O, move[0], move[1]);
            }

            printBoard();
            playerXTurn = !playerXTurn; // Switch turns
        }

        scanner.close();
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private static void printBoard() {
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[] getPlayerMove(Scanner scanner) {
        int[] move = new int[2];
        System.out.print("Enter row (0-2): ");
        move[0] = scanner.nextInt();
        System.out.print("Enter column (0-2): ");
        move[1] = scanner.nextInt();
        return move;
    }

    private static int[] getAIMove() {
        int[] move = minimax(O);
        return new int[]{move[1], move[2]};
    }

    private static int[] minimax(char player) {
        int bestScore = (player == O) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int[] bestMove = new int[3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    board[i][j] = player;
                    int score = minimaxHelper(player == O ? X : O);
                    board[i][j] = EMPTY;

                    if ((player == O && score > bestScore) || (player == X && score < bestScore)) {
                        bestScore = score;
                        bestMove[0] = score;
                        bestMove[1] = i;
                        bestMove[2] = j;
                    }
                }
            }
        }

        return bestMove;
    }

    private static int minimaxHelper(char player) {
        if (isGameOver()) {
            return evaluate();
        }

        int bestScore = (player == O) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    board[i][j] = player;
                    int score = minimaxHelper(player == O ? X : O);
                    board[i][j] = EMPTY;

                    if (player == O) {
                        bestScore = Math.max(score, bestScore);
                    } else {
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
        }

        return bestScore;
    }

    private static boolean isGameOver() {
        return evaluate() != 0 || isBoardFull();
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int evaluate() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                if (board[i][0] == X) {
                    return -1; // Player X wins
                } else if (board[i][0] == O) {
                    return 1; // Player O wins
                }
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                if (board[0][i] == X) {
                    return -1; // Player X wins
                } else if (board[0][i] == O) {
                    return 1; // Player O wins
                }
            }
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == X) {
                return -1; // Player X wins
            } else if (board[0][0] == O) {
                return 1; // Player O wins
            }
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == X) {
                return -1; // Player X wins
            } else if (board[0][2] == O) {
                return 1; // Player O wins
            }
        }
        return 0; // Game is a draw
    }

    private static void makeMove(char player, int row, int col) {
        board[row][col] = player;
    }
}
