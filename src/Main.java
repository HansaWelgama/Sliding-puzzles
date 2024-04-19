import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MazeFileHandler fileHandler = new MazeFileHandler("./puzzle");
        Path mazeFile = fileHandler.getRandomMazeFile();

        if (mazeFile != null) {
            try {
                MapData map = parseMazeFile(mazeFile);
                System.out.println("Start Location: (" + map.getStart()[0] + ", " + map.getStart()[1] + ")");
                System.out.println("Finish Location: (" + map.getFinish()[0] + ", " + map.getFinish()[1] + ")");
                System.out.println("Map Contents:");
                for (int i = 0; i < map.getHeight(); i++) {
                    for (int j = 0; j < map.getWidth(); j++) {
                        System.out.print(map.getCell(i, j) + " ");
                    }
                    System.out.println();
                }

                IcePuzzleSolver.findAndPrintShortestPath(map);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No maze files found in the directory.");
        }
    }

    public static MapData parseMazeFile(Path mazeFile) throws IOException {
        List<String> lines = Files.readAllLines(mazeFile);
        int height = lines.size();
        int width = lines.get(0).length();
        char[][] grid = new char[height][width];
        int[] start = new int[2];
        int[] finish = new int[2];

        for (int i = 0; i < height; i++) {
            String line = lines.get(i);
            for (int j = 0; j < width; j++) {
                char symbol = line.charAt(j);
                grid[i][j] = symbol;
                if (symbol == 'S') {
                    start[0] = i;
                    start[1] = j;
                } else if (symbol == 'F') {
                    finish[0] = i;
                    finish[1] = j;
                }
            }
        }

        return new MapData(width, height, grid, start, finish);
    }
}
