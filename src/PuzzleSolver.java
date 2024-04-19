import java.util.*;
public class PuzzleSolver {
    public static void findAndPrintShortestPath(MapData map) {
        HeapSpaceSolver start = new HeapSpaceSolver(map.getStart()[0], map.getStart()[1]);
        HeapSpaceSolver finish = new HeapSpaceSolver(map.getFinish()[0], map.getFinish()[1]);
        char[][] grid = map.getGrid();
        int width = map.getWidth();
        int height = map.getHeight();

        Queue<HeapSpaceSolver> queue = new LinkedList<>();
        queue.add(start);

        Map<HeapSpaceSolver, HeapSpaceSolver> parentMap = new HashMap<>();
        parentMap.put(start, null);

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Possible movements: up, down, left, right

        List<HeapSpaceSolver> validPath = new ArrayList<>(); // Store the valid path

        while (!queue.isEmpty()) {
            HeapSpaceSolver current = queue.poll();
            if (current.equals(finish)) {
                // If the finish square is found, reconstruct the path and store it
                validPath.add(current);
                HeapSpaceSolver temp = current;
                while (parentMap.get(temp) != null) {
                    temp = parentMap.get(temp);
                    validPath.add(temp);
                }
                break; // Exit the loop as we found the finish square
            }

            for (int[] direction : directions) {
                int newX = current.x + direction[0];
                int newY = current.y + direction[1];
                HeapSpaceSolver next = new HeapSpaceSolver(newX, newY);

                if (isValidMove(next, grid, width, height) && !parentMap.containsKey(next)) {
                    queue.add(next);
                    parentMap.put(next, current);
                }
            }
        }

        // If a valid path is found, print it
        if (!validPath.isEmpty()) {
            printPath(validPath, start, finish, parentMap);
        } else {
            // If no valid path is found, print "No path found."
            System.out.println("No path found.");
        }
    }

    private static boolean isValidMove(HeapSpaceSolver cell, char[][] grid, int width, int height) {
        int x = cell.x;
        int y = cell.y;

        // Check if the cell is within the grid boundaries
        if (x < 0 || x >= height || y < 0 || y >= width) {
            return false; // Cell is out of bounds
        }

        // Check if the cell is an obstacle
        if (grid[x][y] == '0') {
            return false; // Cell is an obstacle
        }

        // Allow movement through empty spaces ('.'), start ('S'), and finish ('F') positions
        return true;
    }

    private static void printPath(List<HeapSpaceSolver> validPath, HeapSpaceSolver start, HeapSpaceSolver finish, Map<HeapSpaceSolver, HeapSpaceSolver> parentMap) {
        System.out.println();
        for (int i = validPath.size() - 1; i >= 0; i--) {
            HeapSpaceSolver current = validPath.get(i);
            String action;
            if (current.equals(start)) {
                action = "Start at";
            } else {
                action = "Move " + getDirection(parentMap.get(current), current);
            }
            System.out.println((validPath.size() - i) + ". " + action + " to (" + current.x + "," + current.y + ")");
        }
        System.out.println("done!");
    }

    private static String getDirection(HeapSpaceSolver prev, HeapSpaceSolver current) {
        if (prev == null) {
            // If prev is null, assume a default direction, for example, "up".
            return "up";
        }
        if (current.x > prev.x) return "down";
        if (current.x < prev.x) return "up";
        if (current.y > prev.y) return "right";
        if (current.y < prev.y) return "left";
        return "";
    }
}
