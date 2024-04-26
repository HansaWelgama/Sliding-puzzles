/*
 * Task 1,2,3,4
 * Name: H.H.Welgama
 * IIT Number: 20221109
 * UOW Number: w1998723
 * I confirm that I understand what plagiarism / collusion /
 * contract cheating is and have read and understood
 * the section on Assessment Offences in the Essential Information for Students.
 * The work that I have submitted is entirely my own.
 * Any work from other authors is duly referenced and acknowledged.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Class for solving sliding puzzles using breadth-first search algorithm.
 */
public class SlidingPuzzle {

    private String[][] puzzle;
    private CustomQueue queue;
    private List<Node> allNodes;
    private int rows;
    private int columns;
    private static int dimension = 0;
    int[] startCoordinates;
    private int[] finishCoordinates;

    // filePath The path to the file containing the puzzle.
    // folderName The name of the folder containing the puzzle file.
    public SlidingPuzzle(String filePath, String folderName) {
        if (folderName.equals("puzzle")) {
            dimension++;
        }
        readFile(filePath);

        puzzle = new String[dimension][dimension];
        queue = new CustomQueue(20);
        allNodes = new ArrayList<>();

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file).useDelimiter("");

            int i = 0;
            int j = 0;

            while (scanner.hasNext()) {
                String currentChar = scanner.next();

                if (currentChar.equals("S")) {
                    startCoordinates = new int[]{i, j};
                }

                if (currentChar.equals("F")) {
                    finishCoordinates = new int[]{i, j};
                }

                if (j < dimension) {
                    puzzle[i][j] = currentChar;
                    j++;
                } else {
                    i++;
                    j = 0;
                    puzzle[i][j] = currentChar;
                }
            }
        } catch (Exception e2) {
            System.out.println("An error occurred while initializing puzzle.");
        }

        columns = puzzle[0].length;
        rows = puzzle.length;
    }


    public void breadthFirstSearch() {//Method to perform breadth-first search to solve the puzzle.
        Node startNode = new Node(startCoordinates);
        queue.enqueue(startNode);
        allNodes.add(startNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.dequeue();

            if (Arrays.equals(currentNode.coordinates, finishCoordinates)) {
                getPath(currentNode);
                System.out.println("Done!");
                break;
            } else {
                getAdjacentNodes(currentNode);
                queue.enqueueAll(currentNode);
            }
        }
    }



    public void getPath(Node node) {//Method to retrieve the path to the solution.node The final node containing the solution.
        Stack<String> steps = new Stack<>();

        while (node != null) {
            if (node.previousNode != null) {
                int[] coordinates = {node.coordinates[1] + 1, node.coordinates[0] + 1};
                steps.push(node.previousDirection + " to " + Arrays.toString(coordinates));
            } else {
                int[] coordinates = {startCoordinates[1] + 1, startCoordinates[0] + 1};
                steps.push("Start at " + Arrays.toString(coordinates));
            }
            node = node.previousNode;
        }

        int stepCount = 1;
        while (!steps.isEmpty()) {
            System.out.println(stepCount + ". " + steps.pop());
            stepCount++;
        }
    }


    public boolean ifNodeExists(Node toFind) {
        for (Node node : allNodes) {
            if (Arrays.equals(toFind.coordinates, node.coordinates)) {
                return true;
            }
        }
        return false;
    }

    public void addNewNode(Node node, int[] coordinates, String previousDirection) {
        Node temp = new Node(coordinates, previousDirection, node);

        if (!ifNodeExists(temp)) {
            node.addNextNode(temp);
            allNodes.add(temp);
        }
    }
    public void getAdjacentNodes(Node currentNode) {
        int y = currentNode.coordinates[0];
        int x = currentNode.coordinates[1];

        int moveLeft = x;
        int moveRight = x;
        int moveUp = y;
        int moveDown = y;

        while (moveLeft >= 0) {
            if (puzzle[y][moveLeft].equals("F")) {
                int[] coordinates = {y, moveLeft};
                addNewNode(currentNode, coordinates, "move left");
            }

            if (puzzle[y][moveLeft].equals("0")) {
                int[] coordinates = {y, moveLeft + 1};
                if (moveLeft + 1 != x) {
                    addNewNode(currentNode, coordinates, "move left");
                }
                break;
            } else if (moveLeft == 0) {
                int[] coordinates = {y, moveLeft};
                if (moveLeft != x) {
                    addNewNode(currentNode, coordinates, "move left");
                }
                break;
            }
            moveLeft--;
        }

        while (moveUp >= 0) {
            if (puzzle[moveUp][x].equals("F")) {
                int[] coordinates = {moveUp, x};
                addNewNode(currentNode, coordinates, "move up");
            }

            if (puzzle[moveUp][x].equals("0")) {
                int[] coordinates = {moveUp + 1, x};
                if (moveUp + 1 != y) {
                    addNewNode(currentNode, coordinates, "move up");
                }
                break;
            } else if (moveUp == 0) {
                int[] coordinates = {moveUp, x};
                if (moveUp != y) {
                    addNewNode(currentNode, coordinates, "move up");
                }
                break;
            }
            moveUp--;
        }

        while (moveRight < columns) {
            if (puzzle[y][moveRight].equals("F")) {
                int[] coordinates = {y, moveRight};
                addNewNode(currentNode, coordinates, "move right");
            }

            if (puzzle[y][moveRight].equals("0")) {
                int[] coordinates = {y, moveRight - 1};
                if (moveRight - 1 != x) {
                    addNewNode(currentNode, coordinates, "move right");
                }
                break;
            } else if (moveRight == columns - 1) {
                int[] coordinates = {y, moveRight};
                if (moveRight != x) {
                    addNewNode(currentNode, coordinates, "move right");
                }
                break;
            }
            moveRight++;
        }

        while (moveDown < rows) {
            if (puzzle[moveDown][x].equals("F")) {
                int[] coordinates = {moveDown, x};
                addNewNode(currentNode, coordinates, "move down");
            }

            if (puzzle[moveDown][x].equals("0")) {
                int[] coordinates = {moveDown - 1, x};
                if (moveDown - 1 != y) {
                    addNewNode(currentNode, coordinates, "move down");
                }
                break;
            } else if (moveDown == rows - 1) {
                int[] coordinates = {moveDown, x};
                if (moveDown != y) {
                    addNewNode(currentNode, coordinates, "move down");
                }
                break;
            }
            moveDown++;
        }
    }

    public void readFile(String file) {
        String line;
        List<String> list = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
                dimension++;
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("An error occurred when reading the file");
        }
    }
}
