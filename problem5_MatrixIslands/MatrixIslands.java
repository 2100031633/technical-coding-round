//MatrixIslands.java
import java.util.Scanner;

public class MatrixIslands {

    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1}; // 8 directions
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    // Function to perform DFS and mark visited cells
    static void dfs(int[][] grid, boolean[][] visited, int i, int j, int m, int n) {
        visited[i][j] = true;

        for (int dir = 0; dir < 8; dir++) {
            int ni = i + dx[dir];
            int nj = j + dy[dir];

            if (ni >= 0 && nj >= 0 && ni < m && nj < n && grid[ni][nj] == 1 && !visited[ni][nj]) {
                dfs(grid, visited, ni, nj, m, n);
            }
        }
    }

    // Function to count islands
    static int countIslands(int[][] grid, int m, int n) {
        boolean[][] visited = new boolean[m][n];
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    dfs(grid, visited, i, j, m, n);
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input dimensions
        System.out.print("Input: ");
        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] grid = new int[m][n];

        // Input matrix
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();

        int islandCount = countIslands(grid, m, n);

        System.out.println("Output: " + islandCount);
        sc.close();
    }
}
