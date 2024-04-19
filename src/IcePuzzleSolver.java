import java.util.*;

class IcePuzzleSolver {

    public static void findAndPrintShortestPath(MapData map) {
        int[] start = map.getStart();
        int[] finish = map.getFinish();
        char[][] grid = map.getGrid();
        int width = map.getWidth();
        int height = map.getHeight();

        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);

        Map<int[], int[]> parentMap = new HashMap<>();
        parentMap.put(start, null);

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Possible movements: up, down, left, right

        List<int[]> validPath = new ArrayList<>(); // Store the valid path

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            if (Arrays.equals(current, finish)) {
                // If the finish square is found, reconstruct the path and store it
                validPath.add(current);
                int[] temp = current;
                while (parentMap.get(temp) != null) {
                    temp = parentMap.get(temp);
                    validPath.add(temp);
                }
                break; // Exit the loop as we found the finish square
            }

            for (int[] direction : directions) {
                int newX = current[0] + direction[0];
                int newY = current[1] + direction[1];
                int[] next = {newX, newY};

                if (isValidMove(next, grid, width, height) && !parentMap.containsKey(next)) {
                    queue.add(next);
                    parentMap.put(next, current);
                }
            }
        }

        // If a valid path is found, print it
        if (!validPath.isEmpty()) {
            printPath(validPath, start, finish);
        } else {
            // If no valid path is found, print "No path found."
            System.out.println("No path found.");
        }
    }

    private static boolean isValidMove(int[] cell, char[][] grid, int width, int height) {
        int x = cell[0];
        int y = cell[1];

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

    private static void printPath(List<int[]> path, int[] start, int[] finish) {
        System.out.println();
        for (int i = path.size() - 1; i >= 0; i--) { // Start from the second to last element
            int[] current = path.get(i);
            String action;
            if (i == path.size()-1) {
                action = "Start at";
            } else {
                action = "Move " + getDirection(path.get(i), path.get(i + 1));
            }
            System.out.println((path.size() - i) + ". " + action + " to (" + current[0] + "," + current[1] + ")");
        }

        System.out.println("done!");
    }



    private static String getDirection(int[] prev, int[] current) {
        if (current[0] > prev[0]) return "down";
        if (current[0] < prev[0]) return "up";
        if (current[1] > prev[1]) return "right";
        if (current[1] < prev[1]) return "left";
        return"";
    }
}