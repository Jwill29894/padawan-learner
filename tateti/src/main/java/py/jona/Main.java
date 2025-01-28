package py.jona;

import java.util.Scanner;

public class Main{
    static final String RESET = "\033[0m";

    // Colors
    static final String RED = "\033[31m";
    static final String BLUE = "\033[34m";
    static final String GREEN = "\033[92m";
    static final String DEFAULT = "\033[39m";

    // Text attributes
    static final String BOLD = "\033[1m";
    static final String BLINK = "\033[5m";

    // Cursor control
    static final String CURSOR_HOME = "\033[H";

    //Clean
    static final String CLEAN_SCREEN = "\033[2J";
    static final String CLEAN_LINE = "\033[2K";

    public static void main(String[] args) throws Exception {
        // Reset all attributes


        System.out.println(CLEAN_SCREEN + CURSOR_HOME);
        Scanner scanner = new Scanner(System.in);
        String [][] board = new String[][] {
            {" ", " ", " "},
            {" ", " ", " "},
            {" ", " ", " "}
        };
        printBoard(board);

        //play
        while (checkWin(board) == null) {
            System.out.println("Type the row: ");
            int i = scanner.nextInt();
            System.out.println("Type the column: ");
            int j = scanner.nextInt();
            board[i][j] = "X";
            String itsVoid = board[i][j];
            if (!validMove(i, j, itsVoid)) {
                continue;
            }
            printBoard(board);
            if (checkWin(board) != null) {
                continue;
            }
            System.out.println("Type the row: ");
            i = scanner.nextInt();
            System.out.println("Type the column: ");
            j = scanner.nextInt();
            board[i][j] = "O";
            itsVoid = board[i][j];
            if (!validMove(i, j, itsVoid)) {
                continue;
            }
            printBoard(board);
        }
        System.out.println("Finish");
    }

    static void prettyPrintln(String str, String ... properties) {
        prettyPrint(str+"\n", properties);
    }
    static void prettyPrint(String str, String ... properties) {
        System.out.print(String.join("", properties) + str + DEFAULT);
    }
    static String stylePiece(String piece) {
        return (piece.equals(" ") ? " " : piece.equals("X") ? RED+BOLD+piece : BLUE+BOLD+piece) + DEFAULT;
    }

    public static void printBoard(String[][] board) {
        System.out.println(DEFAULT + CLEAN_SCREEN + CURSOR_HOME);
        System.out.println("     |     |     ");
        System.out.println(" 0.0 | 1.0 | 2.0 ");
        System.out.println("-----|-----|-----");
        System.out.println(" 0.1 | 1.1 | 2.1");
        System.out.println("-----|-----|-----");
        System.out.println(" 0.2 | 1.2 | 2.2 ");
        System.out.println("     |     |     ");
        System.out.println();
        System.out.println("     |     |     ");
        System.out.println("  " + stylePiece(board[0][0]) + "  |  " + stylePiece(board[1][0]) + "  |  " + stylePiece(board[2][0]));
        System.out.println("-----|-----|-----");
        System.out.println("  " + stylePiece(board[0][1]) + "  |  " + stylePiece(board[1][1]) + "  |  " + stylePiece(board[2][1]));
        System.out.println("-----|-----|-----");
        System.out.println("  " + stylePiece(board[0][2]) + "  |  " + stylePiece(board[1][2]) + "  |  " + stylePiece(board[2][2]));
        System.out.println("     |     |     " );

        String whoWon = checkWin(board);
        if (whoWon != null) {
            System.out.println(GREEN + BLINK + "Win: " + DEFAULT + stylePiece(whoWon));
        }
    }

    public static boolean validMove (int i, int j, String board) {
        if (i > 3) return false;
        if (j > 3) return false;
        return !board.equals(" ");
    }

    public static String checkWin(String[][] player) {
        /*
                 |     |
             0.0 | 1.0 | 2.0
            -----|-----|-----
             0.1 | 1.1 | 2.1"
            -----|-----|-----
             0.2 | 1.2 | 2.2
                 |     |
         */
        //horizontal
        if ((!player[0][0].equals(" ")) && (player[0][0].equals(player[1][0])) && (player[0][0].equals(player[2][0]))) return player[0][0];
        if ((!player[0][1].equals(" ")) && (player[0][1].equals(player[1][1])) && (player[0][1].equals(player[2][1]))) return player[0][1];
        if ((!player[0][2].equals(" ")) && (player[0][2].equals(player[1][2])) && (player[0][2].equals(player[2][2]))) return player[0][2];
        //vertical
        if ((!player[0][0].equals(" ")) && (player[0][0].equals(player[0][1])) && (player[0][0].equals(player[0][2]))) return player[0][0];
        if ((!player[1][0].equals(" ")) && (player[1][0].equals(player[1][1])) && (player[1][0].equals(player[1][2]))) return player[1][0];
        if ((!player[2][0].equals(" ")) && (player[2][0].equals(player[2][1])) && (player[2][0].equals(player[2][2]))) return player[2][0];
        //diagonal
        if ((!player[0][0].equals(" ")) && (player[0][0].equals(player[1][1])) && (player[0][0].equals(player[2][2]))) return player[0][0];
        if ((!player[2][0].equals(" ")) && (player[2][0].equals(player[1][1])) && (player[2][0].equals(player[0][2]))) return player[2][0];
        return null;
    }
}
