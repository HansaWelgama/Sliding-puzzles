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
}