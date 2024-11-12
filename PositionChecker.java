import java.util.Arrays;

public class PositionChecker {
    public static char[][] initialiseBoard() {
        char[][] position = new char[8][8];
        for (char[] row : position) {
            Arrays.fill(row, '?');
        }

        return position;
    }
    
    public static void printBoard(char[][] position) {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 8; ++j) {
                System.out.print(" -");
            }

            System.out.println();

            if (i < 8) {
                for (int k = 0; k < 8; ++k) {
                    System.out.print("|" + position[i][k]);
                }

                System.out.println("|");
            }
        }
    }
    
    public static void main(String[] args) {
        // Initialise chessboard position
        char[][] position = initialiseBoard();

        printBoard(position);
    }
}