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

    public static boolean checkEmptyBoard(char[][] position) {
        for (char[] row : position) {
            for (char square : row) {
                if (square != '?') {
                    return false;
                }
            }
        }

        System.out.println("Board is empty");
        return true;
    }

    public static void checkNumbersOfPieces(char[][] position) {
        // Stores as a string the indices, without spaces. Bearing in mind that files are stored the wrong way around.
        int numWhitePawns = 0;
        int numBlackPawns = 0;
        int numWhiteKnights = 0;
        int numBlackKnights = 0;
        int numWhiteBishops = 0;
        int numBlackBishops = 0;
        int numWhiteRooks = 0;
        int numBlackRooks = 0;
        int numWhiteQueens = 0;
        int numBlackQueens = 0;
        int numWhiteKings = 0;
        int numBlackKings = 0;
        
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                switch(position[i][j]) {
                    case 'P':
                        numWhitePawns++;
                        break;
                    case 'p':
                        numBlackPawns++;
                        break;
                    case 'N':
                        numWhiteKnights++;
                        break;
                    case 'n':
                        numBlackKnights++;
                        break;
                    case 'B':
                        numWhiteBishops++;
                        break;
                    case 'b':
                        numBlackBishops++;
                        break;
                    case 'R':
                        numWhiteRooks++;
                        break;
                    case 'r':
                        numBlackRooks++;
                        break;
                    case 'Q':
                        numWhiteQueens++;
                        break;
                    case 'q':
                        numBlackQueens++;
                        break;
                    case 'K':
                        numWhiteKings++;
                        break;
                    case 'k':
                        numBlackKings++;
                        break;
                    default:
                        break;
                }
            }
        }

        if (numWhitePawns > 8) {
            System.out.println("Too many white pawns");
        }
        
        if (numBlackPawns > 8) {
            System.out.println("Too many black pawns");
        }

        if (numWhiteKnights > 10) {
            System.out.println("Too many white knights");
        }

        if (numBlackKnights > 10) {
            System.out.println("Too many black knights");
        }
        
        if (numWhiteBishops > 10) {
            System.out.println("Too many white bishops");
        }

        if (numBlackBishops > 10) {
            System.out.println("Too many black bishops");
        }

        if (numWhiteRooks > 10) {
            System.out.println("Too many white rooks");
        }

        if (numBlackRooks > 10) {
            System.out.println("Too many black rooks");
        }

        if (numWhiteQueens > 9) {
            System.out.println("Too many white queens");
        }

        if (numBlackQueens > 9) {
            System.out.println("Too many black queens");
        }

        if (numWhiteKings != 1) {
            System.out.println("White does not exactly have 1 king");
        }

        if (numBlackKings != 1) {
            System.out.println("Black does not exactly have 1 king");
        }

        // Not possible for either side to have their 7 minor/major starting pieces + more than 8 promoted pieces total
        if (numWhiteKnights + numWhiteBishops + numWhiteRooks + numWhiteQueens > 15) {
            System.out.println("Too many promoted white pieces (total)");
        }

        if (numBlackKnights + numBlackBishops + numBlackRooks + numBlackQueens > 15) {
            System.out.println("Too many promoted black pieces (total)");
        }
    }

    public static void checkKingPositions(char[][] position) {        
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                for (int k = 0; k < 8; ++k) {
                    for (int l = 0; l < 8; ++l) {
                        if ((Math.abs(k - i) > 1) || (Math.abs(l - j) > 1)) {// Only consider pairs of adjacent squares
                            continue;
                        }

                        if (((position[i][j] == 'K') && (position[i][j] == 'k')) ||
                            ((position[i][j] == 'k') && (position[i][j] == 'K'))) {
                            System.out.println("Kings are next to each other");
                            return;
                        }
                    }
                }        
            }
        }
    }
    
    public static void checkPawnPositions(char[][] position) {
        for (int j = 0; j < 8; ++j) {
            if ((position[7][j] == 'P')) {// Unpromoted white pawns
                System.out.println("There are unpromoted white pawn/s");
            }
            
            if ((position[0][j] == 'P')) {// White pawns on their back row
                System.out.println("There are white pawns on the back row");
            }
            
            if (position[7][j] == 'p') {// Black pawns on their back row
                System.out.println("There are black pawns on the back row");
            }

            if ((position[0][j] == 'p')) {// Unpromoted black pawns
                System.out.println("There are unpromoted black pawn/s");
            }
        }
    }

    public static void checkPositionValidity(char[][] position) {
        if (checkEmptyBoard(position)) {// If board is empty, nothing else to speak of.
            return;
        }
        
        checkNumbersOfPieces(position);
        checkKingPositions(position);
        checkPawnPositions(position);
    }
    
    public static char[][] initialiseBoard() {
        char[][] position = new char[8][8];
        for (char[] row : position) {
            Arrays.fill(row, '?');
        }

        return position;
    }
    
    public static void printBoard(char[][] position, boolean isWhiteView) {
        System.out.println("  " + (isWhiteView ? "a b c d e f g h" : "h g f e d c b a"));
        for (int i = 0; i < 9; ++i) {
            System.out.print(" ");
            for (int j = 0; j < 8; ++j) {
                System.out.print("--");
            }

            System.out.println("-");

            if (i < 8) {
                System.out.print(isWhiteView ? (8 - i) : (i + 1));
                for (int k = 0; k < 8; ++k) {
                    // Either the ranks or files are in reverse order, depending on white or black view, respectively.
                    System.out.print("|" + position[isWhiteView ? (7 - i) : i][isWhiteView ? k : (7 - k)]);
                }

                System.out.println("|" + (isWhiteView ? (i + 1) : (8 - i)));
            }
        }
        System.out.println("  " + (isWhiteView ? "a b c d e f g h" : "h g f e d c b a"));
    }

    public static void printPrompts() {
        System.out.println("Enter the chosen piece name as below (upper-/lowercase for white/black), followed by the square - P/p, N/n, B/b, R/r, Q/q, K/k.");
        System.out.println("To flip the board, type in flip or flip board, case insensitive");
        System.out.println("Alternatively, use X/x to clear the square, or Q/q to quit.");
    }

    public static char[][] updateBoard(char[][] position, String userInput) {
        int file = (int)(userInput.charAt(1)) - 97; // a is ASCII character 97, and uses index 0 in the inner array.
        int rank = (int)(userInput.charAt(2)) - 49; // 1 is ASCII character 49, and uses index 0 in the outer array.
        char newSymbol = ((userInput.charAt(0) == 'X') || userInput.charAt(0) == 'x') ? '?' : userInput.charAt(0);

        position[rank][file] = newSymbol;
        return position;
    }
    
    public static boolean validateUserInput(String userInput) {        
        // The 1st 2 scenarios are the only times that an input not of length 3 is allowed.
        if (userInput.equalsIgnoreCase("Q")) {
            return true;
        }

        if ((userInput.equalsIgnoreCase("Flip")) || (userInput.equalsIgnoreCase("Flip board"))) {
            return true;
        }

        if (userInput.length() != 3) {
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

        // Track board view
        boolean isWhiteView = true;

        Scanner scnr = new Scanner(System.in);
        String userInput = "";

        while (!userInput.equalsIgnoreCase("Q")) {
            printBoard(position, isWhiteView);
            
            printPrompts();
            userInput = scnr.nextLine();
            boolean valid = validateUserInput(userInput);
            
            while (!valid) {
                printPrompts();
                userInput = scnr.nextLine();
                valid = validateUserInput(userInput);
            }
            
            // Flip board if requested
            if (userInput.equalsIgnoreCase("Flip") || userInput.equalsIgnoreCase("Flip board")) {
                isWhiteView = !isWhiteView;
            }
            else {
                position = updateBoard(position, userInput);
            }

            checkPositionValidity(position);
        }

        scnr.close();
    }
}