import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        MazeFileHandler fileHandler = new MazeFileHandler("./puzzle");
        Path mazeFile = fileHandler.getRandomMazeFile();

        if (mazeFile != null) {
            try {
                MapData map = MapData.parseMazeFile(mazeFile);
                System.out.println("Start Location: (" + map.getStart()[0] + ", " + map.getStart()[1] + ")");
                System.out.println("Finish Location: (" + map.getFinish()[0] + ", " + map.getFinish()[1] + ")");
                System.out.println("Map Contents:");
                for (int i = 0; i < map.getHeight(); i++) {
                    for (int j = 0; j < map.getWidth(); j++) {
                        System.out.print(map.getCell(i, j) + " ");
                    }
                    System.out.println();
                }

                PuzzleSolver.findAndPrintShortestPath(map);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No maze files found in the directory.");
        }
    }
}
