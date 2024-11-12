import java.util.Arrays;

public class PositionChecker {
    public static void main(String[] args) {
        // Initialise chessboard position
        char[][] position = new char[8][8];
        for (char[] row : position) {
            Arrays.fill(row, '?');
        }
    }
}