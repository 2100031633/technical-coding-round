// SudokuValidator.java
import java.util.*;

public class SudokuValidator {
    // Check if standard Sudoku rules and custom zones are valid
    public static boolean isValidSudoku(char[][] board, List<List<int[]>> customZones) {
        Set<String> seen = new HashSet<>();

        // Standard Sudoku validation
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') continue;
                if (!seen.add(c + " in row " + i) ||
                    !seen.add(c + " in col " + j) ||
                    !seen.add(c + " in block " + i/3 + "-" + j/3))
                    return false;
            }
        }

        // Custom zones validation
        for (List<int[]> zone : customZones) {
            Set<Character> zoneSet = new HashSet<>();
            for (int[] cell : zone) {
                int x = cell[0], y = cell[1];
                char val = board[x][y];
                if (val == '.') continue;
                if (!zoneSet.add(val)) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input: ");
        char[][] board = new char[9][9];
        for (int i = 0; i < 9; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        // Sample custom zone: diagonal
        List<List<int[]>> customZones = new ArrayList<>();
        List<int[]> diagonal = new ArrayList<>();
        for (int i = 0; i < 9; i++) diagonal.add(new int[]{i, i});
        customZones.add(diagonal);

        boolean result = isValidSudoku(board, customZones);
        System.out.println("Output: ");
        System.out.println(result);
    }
}
