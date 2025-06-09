//KnightsAndPortals.java
import java.util.*;

public class KnightsAndPortals{
    static class State {
        int x, y, steps;
        boolean teleported; // Tracks teleport usage

        State(int x, int y, int steps, boolean teleported) {
            this.x = x;
            this.y = y;
            this.steps = steps;
            this.teleported = teleported;
        }
    }

    public static int findShortestPath(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(new State(0, 0, 0, false));
        visited.add("0,0,false");

        while (!queue.isEmpty()) {
            State current = queue.poll();

            // If target is reached, return steps
            if (current.x == rows - 1 && current.y == cols - 1) return current.steps;

            // Regular movement
            for (int[] dir : directions) {
                int nx = current.x + dir[0], ny = current.y + dir[1];

                if (nx >= 0 && ny >= 0 && nx < rows && ny < cols && grid[nx][ny] == 0) {
                    String stateKey = nx + "," + ny + "," + current.teleported;
                    if (!visited.contains(stateKey)) {
                        visited.add(stateKey);
                        queue.add(new State(nx, ny, current.steps + 1, current.teleported));
                    }
                }
            }
        }

        return -1; // No valid path found
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Grid input
        System.out.print("Input: ");
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        int[][] grid = new int[rows][cols];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                grid[i][j] = scanner.nextInt();

        int result = findShortestPath(grid);
        System.out.println(result == -1 ? "No path found" : "Output: " + result);
        scanner.close();
    }
}
