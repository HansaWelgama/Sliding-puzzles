import java.util.Random;
import java.util.LinkedList;
import java.util.Queue;

public class MapGenerator {
    public static MapData generateRandomMap(int width, int height, double obstacleProbability) {
        char[][] grid = new char[height][width];
        boolean[][] visited = new boolean[height][width];
        Random random = new Random();

        // Generate the map with obstacles
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (random.nextDouble() < obstacleProbability) {
                    grid[i][j] = '0'; // obstacle
                } else {
                    grid[i][j] = '.'; // empty space
                }
            }
        }

        // Find a random reachable point for the start
        int[] start = getRandomReachablePoint(height, width, grid, visited, random);

        // Perform BFS to mark reachable points
        bfs(start, grid, visited);

        // Find a random reachable point for the finish
        int[] finish = getRandomReachablePoint(height, width, grid, visited, random);

        grid[start[0]][start[1]] = 'S';
        grid[finish[0]][finish[1]] = 'F';

        return new MapData(width, height, grid, start, finish);
    }

    private static int[] getRandomReachablePoint(int height, int width, char[][] grid, boolean[][] visited, Random random) {
        int[] point = {random.nextInt(height), random.nextInt(width)};
        while (grid[point[0]][point[1]] == '0' || visited[point[0]][point[1]]) {
            point[0] = random.nextInt(height);
            point[1] = random.nextInt(width);
        }
        return point;
    }

    private static void bfs(int[] start, char[][] grid, boolean[][] visited) {
        int height = grid.length;
        int width = grid[0].length;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Possible movements: up, down, left, right

        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int[] direction : directions) {
                int nx = x + direction[0];
                int ny = y + direction[1];

                if (nx >= 0 && nx < height && ny >= 0 && ny < width && grid[nx][ny] != '0' && !visited[nx][ny]) {
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }
}
