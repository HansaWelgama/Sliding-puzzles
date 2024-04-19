import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MapData {

    private int width;
    private int height;
    private char[][] grid;
    private int[] start; // Location of the start square
    private int[] finish; // Location of the finish square


    public MapData(int width, int height, char[][] grid, int[] start, int[] finish) {
        this.width = width;
        this.height = height;
        this.grid = grid;
        this.start = start;
        this.finish = finish;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char getCell(int row, int col) {
        return grid[row][col];
    }

    public char[][] getGrid() {
        return grid;
    }

    public int[] getStart() {
        return start;
    }

    public int[] getFinish() {
        return finish;
    }
    public static MapData parseMazeFile(Path mazeFile) throws IOException {
        List<String> lines = Files.readAllLines(mazeFile);
        int height = lines.size();
        if (height == 0) {
            throw new IllegalArgumentException("Empty maze file.");
        }

        // Find the maximum length among all lines
        int maxWidth = lines.stream().mapToInt(String::length).max().orElse(0);

        // Pad or truncate each line to match the maximum length
        List<String> paddedLines = new ArrayList<>();
        for (String line : lines) {
            if (line.length() < maxWidth) {
                // Pad with spaces if the line is shorter than the maximum length
                paddedLines.add(String.format("%-" + maxWidth + "s", line));
            } else if (line.length() > maxWidth) {
                // Truncate if the line is longer than the maximum length
                paddedLines.add(line.substring(0, maxWidth));
            } else {
                paddedLines.add(line);
            }
        }

        // Update the original lines list with the padded/truncated lines
        lines = paddedLines;

        // Validate that all lines have the same length
        int width = lines.get(0).length();
        for (String line : lines) {
            if (line.length() != width) {
                throw new IllegalArgumentException("Invalid maze file: lines have different lengths.");
            }
        }

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
