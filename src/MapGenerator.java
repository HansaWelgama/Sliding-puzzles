import java.util.Random;

public class MapGenerator {
    public static MapData generateMap() {
        int width = 10;
        int height = 10;
        char[][] grid = new char[height][width];

        // Fill the grid with empty spaces
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = '.';
            }
        }

        // Place obstacles
        placeObstacles(grid);

        // Place start and finish
        int[] start = {0, 0};
        int[] finish = {height - 1, width - 1};
        grid[start[0]][start[1]] = 'S';
        grid[finish[0]][finish[1]] = 'F';

        return new MapData(width, height, grid, start, finish);
    }

    private static void placeObstacles(char[][] grid) {
        int width = grid[0].length;
        int height = grid.length;

        // Place obstacles in a random pattern
        Random random = new Random();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (random.nextDouble() < 0.2) { // Adjust obstacle probability as needed
                    grid[i][j] = '0';
                }
            }
        }
    }
}
