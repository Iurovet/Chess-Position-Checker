import java.util.Arrays;
import java.util.Scanner;

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
                System.out.print("--");
            }

            System.out.println("-");

            if (i < 8) {
                for (int k = 0; k < 8; ++k) {
                    System.out.print("|" + position[i][k]);
                }

                System.out.println("|");
            }
        }
    }

    public static void printPrompts() {
        System.out.println("Enter the chosen piece name as below (upper-/lowercase for white/black), followed by the square - P/p, K/k, B/b, R/r, Q/q, K/k.");
        System.out.println("Alternatively, use X/x to clear the square, or Q/q to quit.");
    }
    
    public static void main(String[] args) {
        // Initialise chessboard position
        char[][] position = initialiseBoard();

        Scanner scnr = new Scanner(System.in);
        String userInput = "";

        while (!userInput.equalsIgnoreCase("q")) {
            printBoard(position);
            printPrompts();
            userInput = scnr.nextLine();
        }

        scnr.close();
    }
}