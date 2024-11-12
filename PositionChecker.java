import java.util.Arrays;
import java.util.Scanner;

public class PositionChecker {
    public static boolean checkCharAgainstList(char c, int[] charList) {
        int c1 = (int)c;
                
        for (int i1 : charList) {
            if (c1 == i1) {
                return true;
            }

            // Checks lower-case letters, too, if upper case letters were provided.
            if ((i1 >= 65) && (i1 <= 90) && (c1 == i1 + 32)) {
                return true;
            }
        }
        
        return false;
    }
    
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
        System.out.println("Enter the chosen piece name as below (upper-/lowercase for white/black), followed by the square - P/p, N/n, B/b, R/r, Q/q, K/k.");
        System.out.println("Alternatively, use X/x to clear the square, or Q/q to quit.");
    }

    public static boolean validateUserInput(String userInput) {
        // Quitting is the sole exception to the below rule
        if ((userInput.length() != 3) && (!userInput.equalsIgnoreCase("Q"))) {
            System.out.println("Error: Your input must be 3 characters long");
            return false;
        }

        // Check that the 1st character is P/p, N/n, B/b, R/r, Q/q, K/k or X/x
        if (!checkCharAgainstList(userInput.charAt(0), new int[]{80, 78, 66, 82, 81, 75, 88})) {
            System.out.println("Error: 1st character must be P/p, N/n, B/b, R/r, Q/q, K/k or X/x");
            return false;
        }

        // Check that the 2nd character is a-h
        if (!checkCharAgainstList(userInput.charAt(1), new int[]{97, 98, 99, 100, 101, 102, 103, 104})) {
            System.out.println("Error: 2nd character must be a-h");
            return false;
        }

        // Check that the 3rd character is 1-8
        if (!checkCharAgainstList(userInput.charAt(2), new int[]{49, 50, 51, 52, 53, 54, 55, 56})) {
            System.out.println("Error: 3rd character must be 1-8");
            return false;
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        // Initialise chessboard position
        char[][] position = initialiseBoard();

        Scanner scnr = new Scanner(System.in);
        String userInput = "";

        while (!userInput.equalsIgnoreCase("Q")) {
            printBoard(position);
            
            printPrompts();
            userInput = scnr.nextLine();
            boolean valid = validateUserInput(userInput);;
            
            while (!valid) {
                printPrompts();
                userInput = scnr.nextLine();
                valid = validateUserInput(userInput);
            }
        }

        scnr.close();
    }
}