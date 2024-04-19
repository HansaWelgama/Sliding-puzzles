import java.util.Arrays;
import java.util.Random;
import java.util.PriorityQueue;

public class MapGenerator {
    public static MapData generateRandomMap(int width, int height, double obstacleProbability) {
        char[][] grid = new char[height][width];
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

        // Perform Dijkstra's algorithm to mark reachable points
        boolean[][] visited = new boolean[height][width];
        dijkstra(random.nextInt(height), random.nextInt(width), grid, visited);

        // Find a random reachable point for the start
        int[] start = {random.nextInt(height), random.nextInt(width)};
        while (!visited[start[0]][start[1]]) {
            start[0] = random.nextInt(height);
            start[1] = random.nextInt(width);
        }

        // Find a random reachable point for the finish
        int[] finish = {random.nextInt(height), random.nextInt(width)};
        while (!visited[finish[0]][finish[1]]) {
            finish[0] = random.nextInt(height);
            finish[1] = random.nextInt(width);
        }

        grid[start[0]][start[1]] = 'S';
        grid[finish[0]][finish[1]] = 'F';

        return new MapData(width, height, grid, start, finish);
    }

    private static void dijkstra(int startX, int startY, char[][] grid, boolean[][] visited) {
        int height = grid.length;
        int width = grid[0].length;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.offer(new int[]{startX, startY, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int x = current[0];
            int y = current[1];
            int dist = current[2];

            if (visited[x][y]) {
                continue;
            }

            visited[x][y] = true;

            if (grid[x][y] == '0') {
                continue; // Ignore obstacles
            }

            // Explore neighbors
            int[] dx = {-1, 1, 0, 0};
            int[] dy = {0, 0, -1, 1};
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && nx < height && ny >= 0 && ny < width && !visited[nx][ny]) {
                    int newDist = dist + 1; // Assuming unit cost for each move
                    pq.offer(new int[]{nx, ny, newDist});
                }
            }
        }
    }
}
