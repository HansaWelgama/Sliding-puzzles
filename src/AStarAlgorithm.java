import java.util.*;

public class AStarAlgorithm {
    private char[][] grid;
    private int[] start;
    private int[] end;
    private int rows, cols;
    private int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public AStarAlgorithm(char[][] grid, int[] start, int[] end) {
        this.grid = grid;
        this.start = start;
        this.end = end;
        this.rows = grid.length;
        this.cols = grid[0].length;
    }

    public List<HeapSpaceSolver> findPath() {
        PriorityQueue<HeapSpaceSolver> openSet = new PriorityQueue<>(Comparator.comparingInt(HeapSpaceSolver::getF));
        Set<HeapSpaceSolver> closedSet = new HashSet<>();

        HeapSpaceSolver startNode = new HeapSpaceSolver(start[0], start[1]);
        HeapSpaceSolver endNode = new HeapSpaceSolver(end[0], end[1]);

        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            HeapSpaceSolver current = openSet.poll();
            closedSet.add(current);

            if (current.equals(endNode)) {
                return reconstructPath(current);
            }

            for (int[] dir : directions) {
                int newX = current.x;
                int newY = current.y;

                while (isValid(newX + dir[0], newY + dir[1]) && grid[newX + dir[0]][newY + dir[1]] != '0') {
                    newX += dir[0];
                    newY += dir[1];
                }

                HeapSpaceSolver neighbor = new HeapSpaceSolver(newX, newY);

                if (closedSet.contains(neighbor)) {
                    continue;
                }

                int tentativeGScore = current.g + 1;

                if (!openSet.contains(neighbor) || tentativeGScore < neighbor.g) {
                    neighbor.parent = current;
                    neighbor.h = heuristic(neighbor, endNode);
                    neighbor.f = tentativeGScore + neighbor.h;

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        return null;
    }

    private List<HeapSpaceSolver> reconstructPath(HeapSpaceSolver current) {
        List<HeapSpaceSolver> path = new ArrayList<>();
        while (current != null) {
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path);
        return path;
    }

    private int heuristic(HeapSpaceSolver a, HeapSpaceSolver b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}