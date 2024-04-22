import java.util.*;

public class PuzzleSolver {
    public static void findAndPrintShortestPath(MapData map) {
        char[][] grid = map.getGrid();
        int[] start = map.getStart();
        int[] finish = map.getFinish();

        AStarAlgorithm astar = new AStarAlgorithm(grid, start, finish);
        List<HeapSpaceSolver> path = astar.findPath();

        if (path != null) {
            String pathString = printPath(path, start, finish);
            System.out.println(pathString);
        } else {
            System.out.println("No path found.");
        }
    }

    private static String printPath(List<HeapSpaceSolver> path, int[] start, int[] finish) {
        StringBuilder pathBuilder = new StringBuilder();

        for (int i = 0; i < path.size(); i++) {
            HeapSpaceSolver node = path.get(i);
            String action;
            if (i == 0) {
                action = "Start at";
            } else if (i == path.size() - 1) {
                action = "Finish at";
            } else {
                action = "Move";
            }
            pathBuilder.append((i + 1) + ". " + action + " to (" + (node.y + 1) + "," + (node.x + 1) + ")\n");
        }
        pathBuilder.append("Done!");

        return pathBuilder.toString();
    }
}
